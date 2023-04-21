package metadata;

import java.io.*;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;


public class Metadata {
    private String filePath;
    private Hashtable<String,Vector<Column>> columnsOfMetaData;
    private File metafile;
    private FileWriter fw;


    public Metadata(String filePath) throws IOException {
        this.filePath = filePath;
        this.columnsOfMetaData = new Hashtable<String,Vector<Column>>();
        this.metafile=new File("./"+filePath);
        this.fw=new FileWriter(metafile);
        writeHeaders();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Hashtable<String, Vector<Column>> getColumnsOfMetaData() {
        return columnsOfMetaData;
    }

    public void setColumnsOfMetaData(Hashtable<String, Vector<Column>> columnsOfMetaData) {
        this.columnsOfMetaData = columnsOfMetaData;
    }

    public File getMetafile() {
        return metafile;
    }

    public void setMetafile(File metafile) {
        this.metafile = metafile;
    }

    public FileWriter getFw() {
        return fw;
    }

    public void setFw(FileWriter fw) {
        this.fw = fw;
    }

    public void writeHeaders() throws IOException {
        StringBuilder sb= new StringBuilder();
        //Table Name, metadata.Column Name, metadata.Column Type, ClusteringKey, IndexName,IndexType, min, max
        sb.append("Table Name");
        sb.append(",");
        sb.append("metadata.Column Name");
        sb.append(",");
        sb.append("metadata.Column Type");
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
        fw.append(sb.toString()).flush();
    }

    public void writeMetaData(
                              String strTableName,
                              String strClusteringKeyColumn,
                              Hashtable<String,String> htblColNameType,
                              Hashtable<String,String> htblColNameMin,
                              Hashtable<String,String> htblColNameMax)
    {
        try {

//            fw= new FileWriter(metafile,true);
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
                Vector<Column> columns = columnsOfMetaData.getOrDefault(strTableName,new Vector<Column>());
                columns.add(col);
                columnsOfMetaData.put(strTableName,columns);
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
            }
            fw.append(sb.toString()).flush();
            System.out.println("finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public int getTupleSize(String strTableName){
//        int size = 0;
//        for (int i = 0 ; i < columnsOfMetaData.size() ; i++){
//            String currentTablename = columnsOfMetaData.get(i).getTableName();
//            if(currentTablename.equals(strTableName)){
//                size++;
//            }
//        }
//        return size;
        return this.columnsOfMetaData.get(strTableName).size();
    }
    public Vector<String> getColumnNames(String strTableName){
        Vector<String> columnsNames = new Vector<String>();
        Vector<Column> columns = this.columnsOfMetaData.get(strTableName);
        for(int i = 0 ; i < columns.size() ; i++){
            Column currentColumn = columns.get(i);
            if(currentColumn.getTableName().equals(strTableName)) {
                columnsNames.add(currentColumn.getColumnName());
            }
        }
        return columnsNames;
    }

    public String getColumnType(String strTableName ,String columnName){
        String type = "";
        Vector<Column> columns = this.columnsOfMetaData.get(strTableName);
        for(int i = 0 ; i < columns.size(); i++){
            Column currentColumn = columns.get(i);
            if(strTableName.equals(currentColumn.getTableName()) && columnName.equals(currentColumn.getColumnName())){
                type = currentColumn.getColumnType();
                break;
            }
        }
        return type;
    }

    public Vector<Object> getColumnMinAndMax(String strTableName, String columnName, String columnType) throws Exception {
        Vector<Object> columnsMinAndMax = new Vector<Object>();
        Vector<Column> columns = this.columnsOfMetaData.get(strTableName);
        for(int i = 0 ; i < columns.size() ; i++){
            Column currentColumn = columns.get(i);
            String currentColumnTableName = currentColumn.getTableName();
            String currentColumnName = currentColumn.getColumnName();
            if(currentColumnTableName.equals(strTableName) && columnName.equals(currentColumnName)) {
                Object min = Column.adjustDataType(currentColumn.getMin(),columnType);
                Object max = Column.adjustDataType(currentColumn.getMax(),columnType);
                columnsMinAndMax.add(min);
                columnsMinAndMax.add(max);
                break;
            }
        }
        return columnsMinAndMax;
    }

}


