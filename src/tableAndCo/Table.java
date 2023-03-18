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
        if(isTableEmpty()){
            Page page = createNewPage();
            page.insertIntoPage(htblColNameValue);
            return;
        }
        int start =0;
        int end = this.getTablePages().size()-1;
//        System.out.println("in table");
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
}
