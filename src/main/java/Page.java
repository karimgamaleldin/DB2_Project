package main.java;

import java.io.*;
import java.util.*;

public class Page implements Serializable{
    // if a field is not serializable it is marked as transient
    private int pageID;
    private int maxSizePerPage;
    private String filepath;
    private Vector<Tuple> pageTuples;
    private Tuple minVal;
    private Tuple maxVal;
    private String clusteringKey;
    private String tableName;
    private String clusterKeyDataType;


    public Page(int pageID, String filepath , int maxSizePerPage,String clusteringKey, String tableName) {
        this.pageID = pageID;
        this.pageTuples = new Vector<Tuple>();
        this.maxSizePerPage = maxSizePerPage;
        //"src/resources/data/pages/"+tableName+"/"+filepath+".class"
        this.filepath = "src/main/resources/data/"+tableName+"_"+filepath+".class" ;
        this.minVal = null;
        this.maxVal = null;
        this.clusteringKey = clusteringKey;
        this.tableName = tableName;
        this.clusterKeyDataType = Metadata.getClusterKeyDataType(this.tableName);
        FileManipulation.createSerFile(this.filepath);
    }

    public Tuple getMinVal() {
        return minVal;
    }

    public void setMinVal(Tuple minVal) {
        this.minVal = minVal;
    }

    public Tuple getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Tuple maxVal) {
        this.maxVal = maxVal;
    }

    public String getTableName() {
        return tableName;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public void setClusteringKey(String clusteringKey) {
        this.clusteringKey = clusteringKey;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    // some helper methods
    public int getSizeOfPage(){
        return this.pageTuples.size();
   }
    public boolean isPageFull(){
        return this.getSizeOfPage() == this.maxSizePerPage;
    }
    public boolean isPageEmpty(){
        return this.pageTuples.isEmpty();
    }

    public void saveIntoPageFilepath() throws IOException {
        //save the newly inserted tuple in the file itself
        FileManipulation.saveIntoFilepath(this,this.filepath);
//        this.setPageTuples(null);//may give error
    }
    public void updateMinMax(){
        minVal = this.pageTuples.get(0);
        maxVal = this.pageTuples.get(this.pageTuples.size()-1);
    }
    public void printPageInfo(){
        System.out.println("page "+this.getPageID());
        for(int i=0;i<this.getPageTuples().size();i++){
            System.out.println(this.getPageTuples().get(i).getTupleAttributes().toString());
        }
        System.out.println("-------------------");
    }
    public Tuple insertIntoPage(Hashtable<String,Object> tuple) throws IOException, DBAppException {
        boolean wasFull = this.isPageFull();
        Tuple insertedTuple = new Tuple(tuple, clusteringKey,this.clusterKeyDataType);
        if(this.maxVal!=null&&insertedTuple.compareTo(this.maxVal) > 0&&wasFull){
            return insertedTuple;
        }
        if(this.isPageEmpty()){
            this.pageTuples.add(insertedTuple);
            updateMinMax();
//            //print page -------------------
//            printPageInfo();
            saveIntoPageFilepath();
            return null;
        }
        int start = 0 ;
        int end = this.getSizeOfPage() - 1;
        if(insertedTuple.compareTo(this.pageTuples.get(0)) <= 0){
            this.pageTuples.add(0, insertedTuple);
        }
        else {
           while (start <= end) {
               int mid = start + (end-start) / 2;
               Tuple currentTuple = this.pageTuples.get(mid);
               if (insertedTuple.compareTo(currentTuple) > 0) {
                   start = mid + 1;
               } else if (insertedTuple.compareTo(currentTuple) < 0) {
                   end = mid - 1;
               } else {
                   Object clusterKey = insertedTuple.getTupleAttributes().get(insertedTuple.getClusteringKey());
                   throw new DBAppException("the key: "+insertedTuple.getClusteringKey()+" "+clusterKey.toString()+" already exists.");
               }
           }
        }
        Tuple lastTuple = wasFull ?  this.pageTuples.remove(this.getSizeOfPage() - 1) : null ;
        if(start>end) {
            if(start==this.getSizeOfPage()){
                this.pageTuples.add(insertedTuple);
            }else {
                this.pageTuples.add(start,insertedTuple);
            }
        }
        updateMinMax();
        saveIntoPageFilepath();
        //print page -------------------
//        printPageInfo();

        return lastTuple;
    }
    public boolean deleteFromPage(Hashtable<String,Object> htblColNameValue) throws DBAppException, IOException {
        // true: page is empty and deleted so delete from table
        // false: page is not empty so don't delete from table
        if(htblColNameValue.containsKey(this.getClusteringKey())){
            int indexDeleted = getIndexBinarySearch(htblColNameValue);
            if(indexDeleted == -1){
                return false;
            }
            Tuple tupleToBeDeleted = this.pageTuples.get(indexDeleted);
            if(shouldBeDeleted(tupleToBeDeleted,htblColNameValue)){
                pageTuples.remove(indexDeleted);
            }
        } else {
            for(int i = 0 ; i<pageTuples.size();i++){
                Tuple currentTuple = pageTuples.get(i);
                boolean getDeleted = shouldBeDeleted(currentTuple,htblColNameValue);
                if(getDeleted) {
                    pageTuples.remove(i);
                    i--;
                }
            }
        }
//        //print page -------------------
//        printPageInfo();

        if(isPageEmpty()) {
            FileManipulation.deleteEntireFile(this.filepath);
            return true;
        }
        updateMinMax();
        saveIntoPageFilepath();
        return false;
    }



    public boolean shouldBeDeleted(Tuple currentTuple, Hashtable<String, Object> htblColNameValue){
        Set<Map.Entry<String, Object>> entrySet = htblColNameValue.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object currentColValue = currentTuple.getTupleAttributes().get(key);
            if(!currentColValue.equals(value)) {
                return false;
            }
        }
        return true;
    }

    public void updatePage (String strClusteringKeyValue,Hashtable<String,Object> htblColNameValue,String dataType) throws Exception{
//        boolean clusteringKeyExist=false;
        Hashtable tupleHashtable = new Hashtable<String,Object>();
        Object valueCorrectDataType= Column.adjustDataType(strClusteringKeyValue,dataType);
        tupleHashtable.put(this.getClusteringKey(),valueCorrectDataType);
        int indexToBeUpdated = getIndexBinarySearch(tupleHashtable);
        if(indexToBeUpdated == -1) {
            return;
        }
//        if(htblColNameValue.containsKey(this.getClusteringKey())){
//            clusteringKeyExist=true;
//        }
        Tuple tupleToBeUpdated = this.getPageTuples().get(indexToBeUpdated);
        Set<Map.Entry<String, Object>> entrySet = htblColNameValue.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String keyToBeUpdated = entry.getKey();
            Object newValue = entry.getValue();
            tupleToBeUpdated.getTupleAttributes().put(keyToBeUpdated,newValue);
        }

        //print page info -----------
//        this.printPageInfo();
//        if(clusteringKeyExist) {
//            this.getPageTuples().remove(indexToBeUpdated);
//            saveIntoPageFilepath();
//            return tupleToBeUpdated;
//        }
        saveIntoPageFilepath();
    }

    public int getMaxSizePerPage() {
        return maxSizePerPage;
    }

    public String getFilepath() {
        return filepath;
    }

    public Vector<Tuple> getPageTuples() {
        return pageTuples;
    }

    public void setMaxSizePerPage(int maxSizePerPage) {
        this.maxSizePerPage = maxSizePerPage;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setPageTuples(Vector<Tuple> pageTuples) {
        this.pageTuples = pageTuples;
    }

    public int getPageID() {
        return pageID;
    }

    public String getClusteringKey() {
        return clusteringKey;
    }

    public int getIndexBinarySearch(Hashtable<String,Object> htblColNameValue){
        // we need to adjust the case of duplicates
        Tuple tuple = new Tuple(htblColNameValue , this.clusteringKey, this.clusterKeyDataType);
        int start = 0;
        int end = this.pageTuples.size() - 1;
        while(start <= end){
            int mid = start + (end-start) / 2 ;
            if(tuple.compareTo(this.pageTuples.get(mid)) == 0){
                // we should not return immediately need to check if whole tuples are equal
                // made a method called isEqual in Tuple class (indices maybe?)
                return mid;
            }
            else if (tuple.compareTo(this.pageTuples.get(mid)) < 0){
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        String pageInfo = "";
        pageInfo+= "page id: "+this.getPageID()+"\n";
        pageInfo+= "max page size: "+this.getMaxSizePerPage()+"\n";
        pageInfo+= "filepath: "+this.getFilepath()+"\n";
        pageInfo+= "min: "+this.getMinVal().toString()+"\n";
        pageInfo+= "max: "+this.getMaxVal().toString()+"\n";
        pageInfo+= "cluster key: "+this.getClusteringKey()+"\n";
        pageInfo+= "table name: "+this.getTableName()+"\n";
        pageInfo+= "tuples:\n";
        for(int i=0;i<this.getSizeOfPage();i++){
            pageInfo+=this.getPageTuples().get(i).toString()+"\n";
        }
        pageInfo+= this.getFilepath()+"done ...................... \n\n";
        return pageInfo;
    }
}
