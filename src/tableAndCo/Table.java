package tableAndCo;

import exceptions.DBAppException;
import metadata.Column;

import java.io.*;
import java.util.Hashtable;
import java.util.Vector;

public class Table implements Serializable {
    private Vector<String> tablePages; // Contain the unloaded pages
    private String tableName;
    private int tuplesSize; // number of columns
    private int maxSizePerPage;
    private int numberOfTuples;
    private int numberOfPages;
    private Vector<Tuple> minValues;
    private Vector<Tuple> maxValues;
    private String clusteringKey;
    private int nextPageID;

    public Table(String tableName , int tuplesSize , int maxPageSize, String clusteringKey){
        this.tableName = tableName;
        this.tablePages = new Vector<String>();
        this.tuplesSize = tuplesSize;
        this.maxSizePerPage = maxPageSize;
        this.numberOfPages = 0;
        this.numberOfTuples = 0;
        this.clusteringKey = clusteringKey;
        this.nextPageID = 0;
        this.minValues=new Vector<Tuple>();
        this.maxValues=new Vector<Tuple>();
        createTableDirectory();
    }

    public void createTableDirectory() {
        File tableDirectory = new File("data/"+this.getTableName());

        // check if the directory can be created
        // using the specified path name
        if (tableDirectory.mkdir() == true) {
            System.out.println("Directory has been created successfully");
        }
        else {
            System.out.println("Directory cannot be created");
        }
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
    public boolean isTableEmpty() {
        return this.getTablePages().isEmpty();
    }
    public void insert(Hashtable<String,Object> htblColNameValue) throws IOException, ClassNotFoundException {
        Tuple wanted= new Tuple(htblColNameValue,clusteringKey);
        if(isTableEmpty()){
            Page page = createNewPage();
            page.insertIntoPage(htblColNameValue);
            updateMinMax(page,0);
//            minValues.add(page.getMinVal());
//            maxValues.add(page.getMaxVal());
            return;
        }
        int start =0;
        int end = this.getTablePages().size()-1;
        Tuple min=minValues.get(start);
        Tuple max=maxValues.get(end);
        Page loadedPage;
        if(wanted.compareTo(min) < 0){//if tuple less than first tuple in table
            loadedPage = loadPages(this.getTablePages().get(0));
            Tuple shifted= loadedPage.insertIntoPage(htblColNameValue);
            updateMinMax(loadedPage,0);
            shift(1,shifted);
        }
        else if (wanted.compareTo(max) > 0) {//if tuple greater than biggest tuple in table
            loadedPage = loadPages(this.getTablePages().get(end));
            Tuple shifted= loadedPage.insertIntoPage(htblColNameValue);
            updateMinMax(loadedPage,end);
            shift(++end,shifted);
        }
        else{
            while(start <= end){
                int mid = (start + end) / 2 ;
                 min=minValues.get(mid);
                 max=maxValues.get(mid);
                if(wanted.compareTo(min) >0){
                    if(wanted.compareTo(max)<=0){
                        loadedPage = loadPages(this.getTablePages().get(mid));
                        Tuple shifted= loadedPage.insertIntoPage(htblColNameValue);
                        updateMinMax(loadedPage,mid);
                        shift(mid+1,shifted);
                        return;
                    }else{
                        start=mid+1;
                    }

                }
                else if (wanted.compareTo(min) < 0){
                    Tuple maxOfPreviousPage = maxValues.get(mid-1);
                    if(wanted.compareTo(maxOfPreviousPage) > 0) {
                        loadedPage = loadPages(this.getTablePages().get(mid));
                        Tuple shifted = loadedPage.insertIntoPage(htblColNameValue);
                        updateMinMax(loadedPage,mid);
                        shift(mid+1,shifted);
                        return;
                    }else{
                        end=mid-1;
                    }
                }
                else {
                    //tuple = the min value
                    loadedPage = loadPages(this.getTablePages().get(mid));
                    Tuple shifted=  loadedPage.insertIntoPage(htblColNameValue);
                    updateMinMax(loadedPage,mid);
                    shift(mid+1,shifted);
                    return;
                }
            }
        }
//        this.getTablePages().get(0).insertIntoPage(htblColNameValue);

    }
    public void update (String strClusteringKeyValue,Hashtable<String,Object> htblColNameValue,Vector<Column> columns) throws DBAppException {
        if(isTableEmpty()){
            throw new DBAppException("The table is empty");
        }
//        htblColNameValue.put(clusteringKey,);
        Tuple toBeUpdated=new Tuple(htblColNameValue,clusteringKey);
        int start =0;
        int end = this.getTablePages().size()-1;
        Tuple min=minValues.get(start);
        Tuple max=maxValues.get(end);
        if(toBeUpdated.compareTo(min) <0){//if tuple less than first tuple in table
            throw new DBAppException("tuple is not in the table");
        } else if (toBeUpdated.compareTo(max) >0) {//if tuple equal than biggest tuple in table
            throw new DBAppException("tuple is not in the table");
        } else{
            while(start <= end){
                int mid = (start + end) / 2 ;
                min=minValues.get(mid);
                max=maxValues.get(mid);
                if(toBeUpdated.compareTo(min) >0){
                    if(toBeUpdated.compareTo(max)<=0){
//                        this.deleteHelper(mid,htblColNameValue);
                        return;
                    }else{
                        start=mid+1;
                    }
                }
                else if (toBeUpdated.compareTo(min) < 0){
                    end=mid-1;
                }
                else {//if tuple == min
//                    this.deleteHelper(mid,htblColNameValue);
                    return;
                }
            }
            throw new DBAppException("tuple is not in the table");
        }
    }


    private void deleteHelper(int mid, Hashtable<String, Object> htblColNameValue) throws DBAppException, IOException, ClassNotFoundException {
        Page loadedPage = loadPages(this.getTablePages().get(mid));
        boolean isPageDeleted = loadedPage.deleteFromPage(htblColNameValue);
        if(isPageDeleted){
            this.getTablePages().remove(mid);
            this.maxValues.remove(mid);
            this.minValues.remove(mid);
        }else{
            updateMinMax(loadedPage, mid);
        }
    }

    public void delete(Hashtable<String,Object> htblColNameValue) throws DBAppException, IOException, ClassNotFoundException {
        if(isTableEmpty()){
            throw new DBAppException("The table is empty");
        }
        Tuple toBeDeleted=new Tuple(htblColNameValue,clusteringKey);
        int start =0;
        int end = this.getTablePages().size()-1;
        Tuple min=minValues.get(start);
        Tuple max=maxValues.get(end);
        if(toBeDeleted.compareTo(min) <0){//if tuple equal than first tuple in table
            throw new DBAppException("tuple is not in the table");
        } else if (toBeDeleted.compareTo(max) >0) {//if tuple equal than biggest tuple in table
            throw new DBAppException("tuple is not in the table");
        } else{
            while(start <= end){
                int mid = (start + end) / 2 ;
                min=minValues.get(mid);
                max=maxValues.get(mid);
                if(toBeDeleted.compareTo(min) >0){
                    if(toBeDeleted.compareTo(max)<=0){
                        this.deleteHelper(mid,htblColNameValue);
                        return;
                    }else{
                        start=mid+1;
                    }
                }
                else if (toBeDeleted.compareTo(min) < 0){
                    end=mid-1;
                }
                else {//if tuple == min
                    this.deleteHelper(mid,htblColNameValue);
                    return;
                }
            }
            throw new DBAppException("tuple is not in the table");
        }
    }
    public boolean needsNewPage(){
        return numberOfTuples == (maxSizePerPage * numberOfPages);
    }
    public Page createNewPage(){
        String path = "page"+nextPageID;
        Page page = new Page(nextPageID,path,maxSizePerPage,clusteringKey,tableName);
        nextPageID++;
        this.getTablePages().add(path);
        this.minValues.add(null);
        this.maxValues.add(null);
        return page;
        // method to create new page

    }
    public int getPageNumberToInsert(){
        // method to get the page that we need to insert in
        return 0;
    }
    public void updateMinMax(Page p, int index){
        Tuple min=p.getMinVal();
        Tuple max=p.getMaxVal();
        minValues.remove(index);
        minValues.add(index,min);
        maxValues.remove(index);
        maxValues.add(index,max);
    }
    public void insertIntoCreatedPage(Hashtable<String, Object> shift) throws IOException, ClassNotFoundException {
        Page page = createNewPage();
        page.insertIntoPage(shift);
        int lastIndex = this.getTablePages().size()-1;
        updateMinMax(page,lastIndex);
    }
    public void shift(int nextPage,Tuple shifted) throws IOException, ClassNotFoundException {
        if(shifted==null) {
            return;
        }
        Hashtable<String, Object> shift=shifted.getTupleAttributes();
        //if nextPage == table size before entering loop
        if(nextPage==this.getTablePages().size()){
            insertIntoCreatedPage(shift);
            return;
//                this.minValues.add(page.getMinVal());
//                this.maxValues.add(page.getMaxVal());
        }
        while(!(shifted ==null)){
            Page currentPage = loadPages(this.getTablePages().get(nextPage));
            shifted=currentPage.insertIntoPage(shift);
            updateMinMax(currentPage,nextPage);
            nextPage++;
            if(nextPage==this.getTablePages().size()){
                insertIntoCreatedPage(shift);
                return;
//                this.minValues.add(page.getMinVal());
//                this.maxValues.add(page.getMaxVal());
            }
        }
    }

    public Page loadPages(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filepath);
        if(file.available()!=0){
            ObjectInputStream on = new ObjectInputStream(file);
            //System.out.println(((Vector<Tuple>) on.readObject()).get(0).getClusteringKey());
            try {
                Page loadedPage = (Page) on.readObject();
                return loadedPage;
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        }
        return null;
    }
}


