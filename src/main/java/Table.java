package main.java;

import java.io.*;
import java.util.*;

public class Table implements Serializable {
    private Vector<String> tablePages; // Contain the unloaded pages
    private String tableName;
    private int tuplesSize; // number of columns
    private int maxSizePerPage;
    private Vector<Tuple> minValues;
    private Vector<Tuple> maxValues;
    private String clusteringKey;
    private int nextPageID;
    private String filepath;

    public Table(String tableName , int tuplesSize , int maxPageSize, String clusteringKey) throws IOException {
        this.tableName = tableName;
        this.tablePages = new Vector<String>();
        this.tuplesSize = tuplesSize;
        this.maxSizePerPage = maxPageSize;
        this.clusteringKey = clusteringKey;
        this.nextPageID = 0;
        this.minValues=new Vector<Tuple>();
        this.maxValues=new Vector<Tuple>();
        //"resources/data/tables/"+tableName+".class"
        this.filepath = "src/main/resources/data/tables/"+tableName+".class";
        this.saveIntoTableFilepath();
        //"resources/data/pages/"
//        FileManipulation.createDirectory("src/resources/data/pages/"+tableName);
    }

    public void saveIntoTableFilepath() throws IOException {
        //save the newly inserted tuple in the file itself
        FileManipulation.saveIntoFilepath(this,this.filepath);
    }

    public Vector<String> getTablePages() {
        return tablePages;
    }

    public String getTableName() {
        return tableName;
    }

    public int getTuplesSize() {
        return tuplesSize;
    }
    public int getMaxSizePerPage() {
        return maxSizePerPage;
    }

    public String getClusteringKey() {
        return clusteringKey;
    }

    public void setClusteringKey(String clusteringKey) {
        this.clusteringKey = clusteringKey;
    }
    public String getFilepath() {
        return filepath;
    }
    public boolean isTableEmpty() {
        return this.getTablePages().isEmpty();
    }

    public void insert(Hashtable<String,Object> htblColNameValue) throws IOException, ClassNotFoundException, DBAppException {
        if(!htblColNameValue.containsKey(clusteringKey)){
            throw new DBAppException("clustering key not found");
        }
        String clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
        Tuple wanted= new Tuple(htblColNameValue,clusteringKey,clusterKeyDataType);
        if(isTableEmpty()){
            Page page = createNewPage();
            page.insertIntoPage(htblColNameValue);
            updateMinMax(page,0);
            saveIntoTableFilepath();
            return;
        }

        int start =0;
        int end = this.tablePages.size()-1;
        Tuple min=minValues.get(start);
        Tuple max=maxValues.get(end);
        Page loadedPage;
        if(wanted.compareTo(min) < 0){//if tuple less than first tuple in table
            loadedPage = FileManipulation.loadPage(this.tablePages.get(0));
            Tuple shifted= loadedPage.insertIntoPage(htblColNameValue);
            updateMinMax(loadedPage,0);
            shift(1,shifted);
            saveIntoTableFilepath();
        }
        else if (wanted.compareTo(max) > 0) {//if tuple greater than biggest tuple in table
            loadedPage = FileManipulation.loadPage(this.tablePages.get(end));
            Tuple shifted= loadedPage.insertIntoPage(htblColNameValue);
            updateMinMax(loadedPage,end);
            shift(end+1,shifted);
            saveIntoTableFilepath();
        }
        else{
            while(start <= end){
                int mid = start + (end-start) / 2 ;
                min=minValues.get(mid);
                max=maxValues.get(mid);
                if(wanted.compareTo(min) > 0){
                    if(wanted.compareTo(max)<=0){
                        loadedPage = FileManipulation.loadPage(this.tablePages.get(mid));
                        Tuple shifted= loadedPage.insertIntoPage(htblColNameValue);
                        updateMinMax(loadedPage,mid);
                        shift(mid+1,shifted);
                        saveIntoTableFilepath();
                        return;
                    }else{
                        start=mid+1;
                    }
                }
                else if (wanted.compareTo(min) < 0){
                    Tuple maxOfPreviousPage = maxValues.get(mid-1);
                    if(wanted.compareTo(maxOfPreviousPage) > 0) {
                        loadedPage = FileManipulation.loadPage(this.tablePages.get(mid-1));
                        Tuple shifted = loadedPage.insertIntoPage(htblColNameValue);
                        updateMinMax(loadedPage,mid-1);
                        shift(mid,shifted);
                        saveIntoTableFilepath();
                        return;
                    }
                    else{
                        end=mid-1;
                    }
                }
                else {
                    //tuple = the min value
                      throw new DBAppException("the key already exists");
                }
            }
        }
        loadedPage = null;
        System.gc();
    }
    public void update(String strClusteringKeyValue,Hashtable<String,Object> htblColNameValue) throws Exception {
        if(isTableEmpty()){
            return;
        }
        String clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
        Object correctValType = Column.adjustDataType(strClusteringKeyValue,clusterKeyDataType);
        htblColNameValue.put(clusteringKey,correctValType);
        Tuple toBeUpdated = new Tuple(htblColNameValue,clusteringKey,clusterKeyDataType);
        int start =0;
        int end = this.getTablePages().size()-1;
        Tuple min=minValues.get(start);
        Tuple max=maxValues.get(end);
        if(toBeUpdated.compareTo(min) <0 || toBeUpdated.compareTo(max) >0){//if tuple less than first tuple in table
            return;
        } else {
            while(start <= end){
                int mid = (start + end) / 2 ;
                min=minValues.get(mid);
                max=maxValues.get(mid);
                if(toBeUpdated.compareTo(min) >0){
                    if(toBeUpdated.compareTo(max)<=0){
                        updateHelper(mid,strClusteringKeyValue,htblColNameValue,clusterKeyDataType);
                        saveIntoTableFilepath();
                        return;
                    }else{
                        start=mid+1;
                    }
                }
                else if (toBeUpdated.compareTo(min) < 0){
                    end=mid-1;
                }
                else {//if tuple == min
                    updateHelper(mid,strClusteringKeyValue,htblColNameValue,clusterKeyDataType);
                    saveIntoTableFilepath();
                    return;
                }
            }
        }
    }


    private void updateHelper(int mid, String strClusteringKeyValue, Hashtable<String, Object> htblColNameValue, String clusterKeyDataType) throws Exception {
        Page loadedPage = FileManipulation.loadPage(this.tablePages.get(mid));
        loadedPage.updatePage(strClusteringKeyValue,htblColNameValue,clusterKeyDataType);
        if(loadedPage.isPageEmpty()){
            FileManipulation.deleteEntireFile(loadedPage.getFilepath());
            tablePages.remove(mid);
            minValues.remove(mid);
            maxValues.remove(mid);
        }
        loadedPage = null;
        System.gc();
    }
    public void emptyTable() throws IOException {
        for(int i=0;i<this.tablePages.size();i++){
            String currPagePath = this.tablePages.get(i);
            FileManipulation.deleteEntireFile(currPagePath);
        }
        this.tablePages = new Vector<String>();
        this.nextPageID = 0;
        this.minValues=new Vector<Tuple>();
        this.maxValues=new Vector<Tuple>();
        FileManipulation.saveIntoFilepath(this,this.filepath);
    }
    public void delete(Hashtable<String,Object> htblColNameValue) throws DBAppException, IOException, ClassNotFoundException {
        if(isTableEmpty()){
//            throw new main.java.DBAppException("The table is empty");
            return;
        }
        if(htblColNameValue.size()==0){
            emptyTable();
            return;
        }
//        int start =0;
//        int end = this.getTablePages().size()-1;
//        Tuple min=minValues.get(start);
//        Tuple max=maxValues.get(end);
//        if(toBeDeleted.compareTo(min) <0){//if tuple less than first tuple in table
//            throw new main.java.DBAppException("tuple is not in the table");
//        } else if (toBeDeleted.compareTo(max) >0) {//if tuple more than biggest tuple in table
//            throw new main.java.DBAppException("tuple is not in the table");
//        } else{

        for(int i=0;i<this.getTablePages().size();i++){
            Page loadedPage = FileManipulation.loadPage(this.getTablePages().get(i));
            boolean isPageDeleted = loadedPage.deleteFromPage(htblColNameValue);
            if(isPageDeleted){
                this.getTablePages().remove(i);
                minValues.remove(i);
                maxValues.remove(i);
                i--;
            }else {
                updateMinMax(loadedPage,i);
            }
            loadedPage = null;
            System.gc();
        }
        saveIntoTableFilepath();
    }
//    }
    public Page createNewPage(){
        String path = "page"+nextPageID;
        Page page = new Page(nextPageID,path,maxSizePerPage,clusteringKey,tableName);
        nextPageID++;
        this.getTablePages().add(page.getFilepath());
        this.minValues.add(null);
        this.maxValues.add(null);
        return page;
    }

    public void updateMinMax(Page p, int index) throws IOException {
        Tuple min=p.getMinVal();
        Tuple max=p.getMaxVal();
        minValues.remove(index);
        minValues.add(index,min);
        maxValues.remove(index);
        maxValues.add(index,max);
    }
    public void insertIntoCreatedPage(Hashtable<String, Object> shift) throws IOException, ClassNotFoundException, DBAppException {
        Page page = createNewPage();
        page.insertIntoPage(shift);
        int lastIndex = this.tablePages.size()-1;
        updateMinMax(page,lastIndex);
        page = null;
    }
    public void shift(int nextPage,Tuple shifted) throws IOException, ClassNotFoundException, DBAppException {
        if(shifted==null) {
            return;
        }
        Hashtable<String, Object> shift=null;
        //if nextPage == table size before entering loop
        if(nextPage>=this.tablePages.size()){
            shift=shifted.getTupleAttributes();
            insertIntoCreatedPage(shift);
            return;
//                this.minValues.add(page.getMinVal());
//                this.maxValues.add(page.getMaxVal());
        }
        while(shifted !=null){
            shift=shifted.getTupleAttributes();
            Page currentPage = FileManipulation.loadPage(this.tablePages.get(nextPage));
            shifted=currentPage.insertIntoPage(shift);
            updateMinMax(currentPage,nextPage);
            nextPage++;
            currentPage = null;
            System.gc();
            if(nextPage==this.tablePages.size()){
                if(shifted!=null){
                    shift=shifted.getTupleAttributes();
                    insertIntoCreatedPage(shift);
                }
                return;
//                this.minValues.add(page.getMinVal());
//                this.maxValues.add(page.getMaxVal());
            }
        }
    }
}


