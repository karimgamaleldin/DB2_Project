package mainClasses;

import index.Octree;
import index.Point;

import java.io.*;
import java.text.ParseException;
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
    private Vector<String> octrees;

    public Table(String tableName , int tuplesSize , int maxPageSize, String clusteringKey) throws IOException{
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
        this.octrees = new Vector<String>();
        this.saveIntoTableFilepath();
        //"resources/data/pages/"
        FileManipulation.createDirectory("src/main/resources/data/indices/"+tableName);
    }

    public void saveIntoTableFilepath() throws IOException {
        //save the newly inserted tuple in the file itself
        FileManipulation.saveIntoFilepath(this,this.filepath);
    }

    public Vector<Tuple> getMinValues() {
        return minValues;
    }

    public Vector<Tuple> getMaxValues() {
        return maxValues;
    }

    public int getNextPageID() {
        return nextPageID;
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
    public Vector<String> getOctrees() {
        return octrees;
    }

    public void setOctrees(Vector<String> octrees) {
        this.octrees = octrees;
    }
    public boolean isTableEmpty() {
        return this.getTablePages().isEmpty();
    }

    public void insert(Hashtable<String,Object> htblColNameValue) throws Exception {
        if(!htblColNameValue.containsKey(clusteringKey)){
            throw new DBAppException("clustering key not found");
        }
        String clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
        Tuple wanted= new Tuple(htblColNameValue,clusteringKey,clusterKeyDataType);
        if(isTableEmpty()){
            Page page = createNewPage();
            page.insertIntoPage(htblColNameValue,this.octrees);
            updateMinMax(page,0);
            this.insertIntoOctree(wanted,false,page.getFilepath(),page.getFilepath());
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
            Tuple shifted= loadedPage.insertIntoPage(htblColNameValue,this.octrees);
            updateMinMax(loadedPage,0);
            this.insertIntoOctree(wanted,false,loadedPage.getFilepath(),loadedPage.getFilepath());
            shift(1,shifted);
            saveIntoTableFilepath();
        }
        else if (wanted.compareTo(max) > 0) {//if tuple greater than biggest tuple in table
            loadedPage = FileManipulation.loadPage(this.tablePages.get(end));
            Tuple shifted= loadedPage.insertIntoPage(htblColNameValue,this.octrees);
            updateMinMax(loadedPage,end);
            this.insertIntoOctree(wanted,false,loadedPage.getFilepath(),loadedPage.getFilepath());
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
                        Tuple shifted= loadedPage.insertIntoPage(htblColNameValue,this.octrees);
                        updateMinMax(loadedPage,mid);
                        this.insertIntoOctree(wanted,false,loadedPage.getFilepath(),loadedPage.getFilepath());
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
                        Tuple shifted = loadedPage.insertIntoPage(htblColNameValue,this.octrees);
                        updateMinMax(loadedPage,mid-1);
                        this.insertIntoOctree(wanted,false,loadedPage.getFilepath(),loadedPage.getFilepath());
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
    public void insertIntoOctree(Tuple tuple, boolean isShifted, String oldRef, String newRef) throws Exception {
        for(String octreePath: octrees){
            Octree currOctree = FileManipulation.loadOctree("src/main/resources/data/indices/"+this.tableName+"/",octreePath);
            Object width = tuple.getTupleAttributes().get(currOctree.getStrColWidth());
            Object length = tuple.getTupleAttributes().get(currOctree.getStrColLength());
            Object height = tuple.getTupleAttributes().get(currOctree.getStrColHeight());
            Point insertPoint = new Point(width,length,height,newRef);
            if(!isShifted) {
                currOctree.insertIntoOctree(insertPoint);
            }else {
                Vector<Point> pts = currOctree.search(insertPoint);
                if(pts.size()==0){
                    currOctree.insertIntoOctree(insertPoint);
                }else {
                    for(Point currPoint: pts){
                        if(insertPoint.isEqual(currPoint)){
                            currPoint.getReferences().remove(oldRef);
                            currPoint.getReferences().add(newRef);
                        }
                    }
                }

            }
            currOctree.saveIntoOctreeFilepath();
        }
    }

    public void update(String strClusteringKeyValue,Hashtable<String,Object> htblColNameValue) throws Exception {
        if(isTableEmpty()){
            return;
        }
        String clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
        Object correctValType = Column.adjustDataType(strClusteringKeyValue,clusterKeyDataType);
        htblColNameValue.put(clusteringKey,correctValType);
        if(this.updateUsingOctree(clusterKeyDataType,correctValType,htblColNameValue)){
            saveIntoTableFilepath();
            return;
        }
        Tuple toBeUpdated = new Tuple(htblColNameValue,clusteringKey,clusterKeyDataType);
        int start =0;
        int end = this.tablePages.size()-1;
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
        loadedPage.updatePage(strClusteringKeyValue,htblColNameValue,clusterKeyDataType, this.octrees);
        if(loadedPage.isPageEmpty()){
            FileManipulation.deleteEntireFile(loadedPage.getFilepath());
            tablePages.remove(mid);
            minValues.remove(mid);
            maxValues.remove(mid);
        }
        loadedPage = null;
        System.gc();
    }
    public boolean updateUsingOctree(String dataType, Object clusteringKeyValue,Hashtable<String,Object> htblColNameValue) throws Exception {
        boolean useOctree = false;
        for(String octreeName: octrees){
            Octree currOctree = FileManipulation.loadOctree("src/main/resources/data/indices/"+this.tableName+"/",octreeName);
            boolean hasWidthAsClusteringKey = currOctree.getStrColWidth().equalsIgnoreCase(this.clusteringKey);
            boolean hasLengthAsClusteringKey = currOctree.getStrColLength().equalsIgnoreCase(this.clusteringKey);
            boolean hasHeightAsClusteringKey = currOctree.getStrColHeight().equalsIgnoreCase(this.clusteringKey);

            if(hasWidthAsClusteringKey || hasLengthAsClusteringKey || hasHeightAsClusteringKey){
                currOctree.updateInOctreeUsingClusteringKey(dataType, clusteringKeyValue,htblColNameValue, this.clusteringKey,this.octrees);
                useOctree = true;
//                currOctree.saveIntoOctreeFilepath();
                break;
            }
        }
        return useOctree;
    }
    public void emptyTable() throws IOException, ClassNotFoundException {
        for(int i=0;i<this.tablePages.size();i++){
            String currPagePath = this.tablePages.get(i);
            FileManipulation.deleteEntireFile(currPagePath);
        }
        this.tablePages = new Vector<String>();
        this.nextPageID = 0;
        this.minValues=new Vector<Tuple>();
        this.maxValues=new Vector<Tuple>();
        for(int i=0;i<this.octrees.size();i++){
            Octree currOctree = FileManipulation.loadOctree("src/main/resources/data/indices/"+this.tableName+"/",octrees.get(i));
            this.clearOctree(currOctree);
            currOctree.saveIntoOctreeFilepath();
        }
        this.saveIntoTableFilepath();
    }
    public void clearOctree(Octree octree) {
        if(octree==null) return;
        octree.getPoints().clear();
        octree.getOverflow().clear();
        clearOctree(octree.getFirstOctant());
        clearOctree(octree.getSecondOctant());
        clearOctree(octree.getThirdOctant());
        clearOctree(octree.getFourthOctant());
        clearOctree(octree.getFifthOctant());
        clearOctree(octree.getSixthOctant());
        clearOctree(octree.getSeventhOctant());
        clearOctree(octree.getEighthOctant());
    }
    public void delete(Hashtable<String,Object> htblColNameValue) throws DBAppException, IOException, ClassNotFoundException {
        if(isTableEmpty()){
//            throw new java.DBAppException("The table is empty");
            return;
        }
        if(htblColNameValue.size()==0){
            emptyTable();
            return;
        }
        if(deleteUsingOctree(htblColNameValue)){
//            saveIntoTableFilepath();
            return;
        }
        for(int i=0;i<this.tablePages.size();i++){
            Page loadedPage = FileManipulation.loadPage(this.tablePages.get(i));
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
    public boolean deleteUsingOctree(Hashtable<String,Object> htblColNameValue) throws IOException, ClassNotFoundException, DBAppException {
        boolean useOctree = false;
        for(String octreeName: octrees){
            Octree currOctree = FileManipulation.loadOctree("src/main/resources/data/indices/"+this.tableName+"/",octreeName);
            boolean hasWidth = htblColNameValue.containsKey(currOctree.getStrColWidth());
            boolean hasLength = htblColNameValue.containsKey(currOctree.getStrColLength());
            boolean hasHeight = htblColNameValue.containsKey(currOctree.getStrColHeight());
            Object width = htblColNameValue.get(currOctree.getStrColWidth());
            Object length = htblColNameValue.get(currOctree.getStrColLength());
            Object height = htblColNameValue.get(currOctree.getStrColHeight());
            if(hasWidth || hasLength || hasHeight){
                currOctree.deleteFromOctree(width,length,height,htblColNameValue);
                useOctree = true;
            }
        }
        return useOctree;
    }
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
    public void insertIntoCreatedPage(Hashtable<String, Object> shift) throws Exception {
        Page page = createNewPage();
        page.insertIntoPage(shift,this.octrees);
        int lastIndex = this.tablePages.size()-1;
        updateMinMax(page,lastIndex);
        String oldPage = this.tablePages.get(lastIndex-1);
        String clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
        Tuple wanted = new Tuple(shift,this.clusteringKey,clusterKeyDataType);
        this.insertIntoOctree(wanted,true,oldPage,page.getFilepath());
//        this.insertIntoOctree(wanted,page.getFilepath());
        page = null;
    }

    public void shift(int nextPage,Tuple shifted) throws Exception {
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
            shifted=currentPage.insertIntoPage(shift,this.octrees);
            updateMinMax(currentPage,nextPage);
            String oldPage = this.tablePages.get(nextPage-1);
            String clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
            Tuple wanted = new Tuple(shift,this.clusteringKey,clusterKeyDataType);
            this.insertIntoOctree(wanted,true,oldPage,currentPage.getFilepath());
            nextPage++;
            currentPage = null;
            System.gc();
            if(nextPage==this.tablePages.size()){
                if(shifted!=null){
                    shift=shifted.getTupleAttributes();
                    insertIntoCreatedPage(shift);
                }
                return;
            }
        }
    }
    public Vector<Tuple> selectDataFromTable(String columnName , Object value , String operator) throws IOException, ClassNotFoundException, DBAppException {
        Vector<Tuple> ret = new Vector<Tuple>();
        if(false){ //columnName.equals(clusteringKey
            Page loadedPage;
            int start =0;
            int end = this.tablePages.size()-1;
            Tuple min=minValues.get(start);
            Tuple max=maxValues.get(end);
            while(start <= end){
                int mid = start + (end-start) / 2 ;
                min=minValues.get(mid);
                max=maxValues.get(mid);
            }

        }else {
        // linear search
            for(int i = 0 ; i < tablePages.size() ; i++){
                Page p = FileManipulation.loadPage(tablePages.get(i));
                Vector<Tuple> temp = p.selectLinearDataInPage(columnName , value , operator);
                for(int j = 0; j < temp.size() ; j++){
                    ret.add(temp.get(j));
                }
            }
        }
        return ret;
    }
}


