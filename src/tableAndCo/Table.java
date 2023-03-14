package tableAndCo;

import java.io.Serializable;
import java.util.Vector;

public class Table implements Serializable {
    private Vector<String> tablePages; // Contain the file names of the pages
    private String tableName;
    private int tuplesSize;
    private int maxPageSize;

    public Table(String tableName , int tuplesSize , int maxPageSize){
        this.tableName = tableName;
        this.tablePages = new Vector<String>();
        this.tuplesSize = tuplesSize;
        this.maxPageSize = maxPageSize;
    }

    public void setTablePages(Vector<String> tablePages) {
        this.tablePages = tablePages;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTuplesSize(int tuplesSize) {
        this.tuplesSize = tuplesSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
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
}
