package tableAndCo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class Table implements Serializable {
    private Vector<Page> tablePages; // Contain the unloaded pages
    private String tableName;
    private int tuplesSize;
    private int maxSizePerPage;
    private int numberOfTuples;
    private int numberOfPages;
    private Vector<Tuple> minValues;
    private Vector<Tuple> maxValues;
    private String clusteringKey;
    private int nextPageID;

    public Table(String tableName , int tuplesSize , int maxPageSize, String clusteringKey){
        this.tableName = tableName;
        this.tablePages = new Vector<Page>();
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
    public Vector<Page> getTablePages() {
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
            minValues.add(page.getMinVal());
            maxValues.add( page.getMaxVal());
            return;
        }

        int start =0;
        int end = this.getTablePages().size()-1;
        Tuple min=minValues.get(start);
        Tuple max=maxValues.get(end);
        if(wanted.compareTo(min) < 0){//if tuple less than first tuple in table
            Tuple shifted= this.getTablePages().get(0).insertIntoPage(htblColNameValue);
            updateMinmax(this.getTablePages().get(0),0);
            shift(1,shifted);
            return;
        }
        else if (wanted.compareTo(max) > 0) {//if tuple greater than biggest tuple in table
            Tuple shifted=  this.getTablePages().get(end).insertIntoPage(htblColNameValue);
            updateMinmax(this.getTablePages().get(end),end);
            shift(++end,shifted);
            return;
        }
        else{
        while(start <= end){
            int mid = (start + end) / 2 ;
             min =minValues.get(mid);
             max=maxValues.get(mid);

            if(wanted.compareTo(min) >0){
                if(wanted.compareTo(max)<=0){
                    Tuple shifted=  this.getTablePages().get(mid).insertIntoPage(htblColNameValue);
                    updateMinmax(this.getTablePages().get(mid),mid);
                    shift(mid+1,shifted);
                    return;
                }else{
                    start=mid+1;
                }

            }
            else if (wanted.compareTo(min) < 0){
                if(wanted.compareTo(maxValues.get(mid-1)) > 0){
                    Tuple shifted=  this.getTablePages().get(mid).insertIntoPage(htblColNameValue);
                    updateMinmax(this.getTablePages().get(mid),mid);
                    shift(mid+1,shifted);
                    return;
                }else{
                    end=mid+1;
                }

            }
            else {//tuple = the min value
                Tuple shifted=  this.getTablePages().get(mid).insertIntoPage(htblColNameValue);
                updateMinmax(this.getTablePages().get(mid),mid);
                shift(mid+1,shifted);
            }
        }
       }
//        this.getTablePages().get(0).insertIntoPage(htblColNameValue);


//        while(start<=end){
//            int mid = (start + end) / 2;
//
//        }
    }
    public boolean needsNewPage(){
        return numberOfTuples == (maxSizePerPage * numberOfPages);
    }
    public Page createNewPage(){
        String path = "page"+nextPageID;
        Page page = new Page(nextPageID,path,maxSizePerPage,clusteringKey,tableName);
        nextPageID++;
        this.getTablePages().add(page);
        return page;
        // method to create new page

    }
    public int getPageNumberToInsert(){
        // method to get the page that we need to insert in
        return 0;
    }
    public void updateMinmax(Page p,int index){
        Tuple min=p.getMinVal();
        Tuple max=p.getMaxVal();
        minValues.remove(index);
        minValues.add(index,min);
        maxValues.remove(index);
        maxValues.add(index,max);

    }
    public void shift(int nextpage,Tuple shifted) throws IOException, ClassNotFoundException {
        Hashtable shift=shifted.getTupleAttributes();
        while(!(shifted ==null)){
            shifted=this.getTablePages().get(nextpage).insertIntoPage(shift);
            updateMinmax(this.getTablePages().get(nextpage),nextpage);
            nextpage++;
            if(nextpage==this.getTablePages().size()){
            Page page = createNewPage();
            page.insertIntoPage(shift);
            }
        }
    }
}


