package tableAndCo;

import java.util.Vector;

public class Table {
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
}
