import java.io.*;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;


public class Metadata {
    private String filePath;
    private Vector<Column> columnsOfMetaData;
    private File metafile;
    private FileWriter fw;


    public Metadata(String filePath) throws IOException {
        this.filePath = filePath;
        this.columnsOfMetaData = new Vector<Column>();
        this.metafile=new File("./"+filePath);
        this.fw=new FileWriter(metafile);
        writeHeaders();
    }
    public void writeHeaders() throws IOException {

        StringBuilder sb= new StringBuilder();
        //Table Name, Column Name, Column Type, ClusteringKey, IndexName,IndexType, min, max
        sb.append("Table Name");
        sb.append(",");
        sb.append("Column Name");
        sb.append(",");
        sb.append("Column Type");
        sb.append(",");
        sb.append("ClusteringKey");
        sb.append(",");
        sb.append("IndexName");
        sb.append(",");
        sb.append("IndexType");
        sb.append(",");
        sb.append("min");
        sb.append(",");
        sb.append("max");
        sb.append("\r\n");
        fw.write(sb.toString());


    }

    public void writeMetaData(
                              String strTableName,
                              String strClusteringKeyColumn,
                              Hashtable<String,String> htblColNameType,
                              Hashtable<String,String> htblColNameMin,
                              Hashtable<String,String> htblColNameMax)
    {
        try {

            fw= new FileWriter(metafile,true);
            StringBuilder sb= new StringBuilder();


            Set<Entry<String, String>> entrySet = htblColNameType.entrySet();

            for (Entry<String, String> entry : entrySet) {
                String columnName = entry.getKey();
                String dataType = entry.getValue();
                String isClusteringKeyStr = columnName.equals(strClusteringKeyColumn) ? "True" : "False";
                String minColValue = htblColNameMin.get(columnName);
                String maxColValue = htblColNameMax.get(columnName);
                Boolean isClusteringKeyBool = isClusteringKeyStr.equals("True")? true:false;
                Column col = new Column(strTableName,columnName,dataType,isClusteringKeyBool,minColValue,maxColValue);
                columnsOfMetaData.add(col);
                sb.append(strTableName);
                sb.append(",");
                sb.append(columnName);
                sb.append(",");
                sb.append(dataType);
                sb.append(",");
                sb.append(isClusteringKeyStr);
                sb.append(",");
                sb.append("null");
                sb.append(",");
                sb.append("null");
                sb.append(",");
                sb.append(minColValue);
                sb.append(",");
                sb.append(maxColValue);
                sb.append("\r\n");
                System.out.println(sb);
            }
           fw.write(sb.toString());


            System.out.println("finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public int getTupleSize(String tableName){
        int size = 0;
        for (int i = 0 ; i < columnsOfMetaData.size() ; i++){
            if(columnsOfMetaData.get(i).getTableName().equals(tableName)){
                size++;
            }
        }
        return size;
    }
    public Vector<String> getColumnNames(String strTableName){
        Vector<String> columnsNames = new Vector<String>();
        for(int i = 0 ; i < this.columnsOfMetaData.size() ; i++){
            Column currentColumn = this.columnsOfMetaData.get(i);
            if(currentColumn.getTableName().equals(strTableName))
                columnsNames.add(currentColumn.getColumnName());
        }
        return columnsNames;
    }

    public String getColumnType(String tableName ,String columnName){
        String type = "";
        for(int i = 0 ; i < this.columnsOfMetaData.size(); i++){
            Column currentColumn = this.columnsOfMetaData.get(i);
            if(tableName.equals(currentColumn.getTableName()) && columnName.equals(currentColumn.getColumnName())){
                type = currentColumn.getColumnType();
                break;
            }
        }
        return type;
    }

}

class Column{
    private String tableName;
    private String columnName;
    private String columnType;
    private boolean isClusteringKey;
    private Object min;
    private Object max;

    public Column(String tableName , String columnName , String columnType , boolean isClusteringKey , Object min, Object max){
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnType = columnType;
        this.isClusteringKey = isClusteringKey;
        this.min = min;
        this.max = max;
    }
    public String toString() {
        return tableName + "," + columnName + "," + columnType + "," + (isClusteringKey ? "True" : "False") + "," + min.toString()+ ","+max.toString();
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean isClusteringKey() {
        return isClusteringKey;
    }

    public Object getMin() {
        return min;
    }

    public Object getMax() {
        return max;
    }
}
