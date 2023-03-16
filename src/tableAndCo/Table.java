package tableAndCo;

import java.io.Serializable;
import java.util.Vector;

public class Table implements Serializable {
    private Vector<String> tablePages; // Contain the file names of the pages
    private String tableName;
    private int tuplesSize;
    private int maxPageSize;
    private int numberOfTuples;
    private int numberOfPages;
    private String clusteringKey;

    public Table(String tableName , int tuplesSize , int maxPageSize, String clusteringKey){
        this.tableName = tableName;
        this.tablePages = new Vector<String>();
        this.tuplesSize = tuplesSize;
        this.maxPageSize = maxPageSize;
        this.numberOfPages = 0;
        this.numberOfTuples = 0;
        this.clusteringKey = clusteringKey;
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

    public int getMaxPageSize() {
        return maxPageSize;
    }
    public boolean needsNewPage(){
        return numberOfTuples == (maxPageSize * numberOfPages);
    }
    public void createNewPage(){
        // method to create new page

    }
    public int getPageNumberToInsert(){
        // method to get the page that we need to insert in
        return 0;
    }
}
