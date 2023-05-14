package mainClasses;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;


public class Metadata {
    private static String filePath;
    private static Hashtable<String,Vector<Column>> columnsOfMetaData;
    private static File metafile;
    private static FileWriter fw;


    public Metadata(String filePath) throws IOException {
        this.metafile=new File(filePath);
        this.columnsOfMetaData = new Hashtable<String,Vector<Column>>();
        this.filePath = filePath;
        this.fw=new FileWriter(metafile,true);
        this.loadMetaData();
        if(metafile.length()==0){
            writeHeaders();
        }

    }
    public static String getClusterKeyDataType(String strTableName){
        Vector<Column> columns = columnsOfMetaData.get(strTableName);
        String clusterKeyDataType = "";
        for(int i=0;i<columns.size();i++){
            Column currentColumn = columns.get(i);
            if(currentColumn.isClusteringKey()){
                clusterKeyDataType = currentColumn.getColumnType();
                break;
            }
        }
        return clusterKeyDataType;
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

    public static void loadMetaData() throws FileNotFoundException {
        // true: file empty
        if(metafile.length()==0){
            return;
        }
        Scanner sc = new Scanner(metafile);
        sc.useDelimiter(",");
        int i=1;
        for(;i<=8;i++){
            sc.next();
        }
        columnsOfMetaData.clear();
        while (sc.hasNext())  //returns a boolean value
        {
            Vector<String> temp = new Vector<String>();
            for(int j=1;j<=8&&sc.hasNext();j++){
                String curr = sc.next();
                temp.add(curr.replace("\r\n",""));
            }
            if(!sc.hasNext()){
                break;
            }
            String strTableName = temp.get(0);
//            System.out.println(strTableName);
            String columnName = temp.get(1);
            String dataType = temp.get(2);
            String isClusteringKeyStr = temp.get(3);
            Boolean isClusteringKeyBool = isClusteringKeyStr.equals("True")? true:false;
            String indexName = temp.get(4);
            String indexType = temp.get(5);
            String minColValue = temp.get(6);
            String maxColValue = temp.get(7);
            Column col = new Column(strTableName,columnName,dataType,indexName,indexType,isClusteringKeyBool,minColValue,maxColValue);
            Vector<Column> columns = columnsOfMetaData.getOrDefault(strTableName,new Vector<Column>());
            columns.add(col);
            columnsOfMetaData.put(strTableName,columns);
        }
        sc.close();
    }
    public void writeHeaders() throws IOException {
        StringBuilder sb= new StringBuilder();
        //Table Name, main.java.java.Column Name, main.java.java.Column Type, ClusteringKey, IndexName,IndexType, min, max
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
        sb.append(",\r\n");
        fw.append(sb.toString()).flush();
    }

    public void writeMetaData(
                              String strTableName,
                              String strClusteringKeyColumn,
                              Hashtable<String,String> htblColNameType,
                              Hashtable<String,String> htblColNameMin,
                              Hashtable<String,String> htblColNameMax) throws DBAppException {
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
                Column col = new Column(strTableName,columnName,dataType,null,null,isClusteringKeyBool,minColValue,maxColValue);
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
                sb.append(",\r\n");
            }
            fw.append(sb.toString()).flush();
        } catch (Exception e) {
            throw new DBAppException(e.getMessage());
//            System.out.println("create metadata:"+e);
        }
    }
    public int getTupleSize(String strTableName){
        return this.columnsOfMetaData.get(strTableName).size();
    }
    public Vector<String> getTableNames(){
        Vector<String> tableNames = new Vector<String>();
        Set<Entry<String, Vector<Column>>> entrySet = columnsOfMetaData.entrySet();
        for (Entry<String, Vector<Column>> entry : entrySet) {
            String key = entry.getKey();
            tableNames.add(key);
        }
        return tableNames;
    }
    public Vector<String> getColumnNames(String strTableName){
        Vector<String> columnsNames = new Vector<String>();
//        System.out.println(columnsOfMetaData);
//        System.out.println(strTableName+"-"+columnsOfMetaData.keySet()+": ");
        Vector<Column> columns = columnsOfMetaData.get(strTableName);
        for(int i = 0 ; i < columns.size() ; i++){
            Column currentColumn = columns.get(i);
            if(currentColumn.getTableName().equals(strTableName)) {
                columnsNames.add(currentColumn.getColumnName());
            }
        }
        return columnsNames;
    }

    public static String getTableClusteringKey(String strTableName){
        Vector<Column> columns = columnsOfMetaData.get(strTableName);
        String clusteringKey = "";
        for(int i = 0 ; i < columns.size() ; i++){
            Column currentColumn = columns.get(i);
            if(currentColumn.getTableName().equals(strTableName)&&currentColumn.isClusteringKey()) {
                clusteringKey = currentColumn.getColumnName();
                break;
            }
        }
        return clusteringKey;
    }

    public String getColumnType(String strTableName ,String columnName){
        String type = "";
        Vector<Column> columns = this.columnsOfMetaData.get(strTableName);
        for(int i = 0 ; i < columns.size(); i++){
            Column currentColumn = columns.get(i);
            if(strTableName.equalsIgnoreCase(currentColumn.getTableName()) && columnName.equalsIgnoreCase(currentColumn.getColumnName())){
                type = currentColumn.getColumnType();
                break;
            }
        }
        return type;
    }

    public static Vector<Object> getColumnMinAndMax(String strTableName, String columnName) throws Exception {
        Vector<Object> columnsMinAndMax = new Vector<Object>();
        Vector<Column> columns = columnsOfMetaData.get(strTableName);
        for(int i = 0 ; i < columns.size() ; i++){
            Column currentColumn = columns.get(i);
            String currentColumnTableName = currentColumn.getTableName();
            String currentColumnName = currentColumn.getColumnName();
            String currentColumnType = currentColumn.getColumnType();
            if(currentColumnTableName.equals(strTableName) && columnName.equals(currentColumnName)) {
                Object min = Column.adjustDataType(currentColumn.getMin(),currentColumnType);
                Object max = Column.adjustDataType(currentColumn.getMax(),currentColumnType);
                columnsMinAndMax.add(min);
                columnsMinAndMax.add(max);
                break;
            }
        }
        return columnsMinAndMax;
    }
    public static void updateCSV( String strTableName , String[] strarrColName,
                                 String indexName,String indexType) throws IOException, CsvException {



        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(metafile));
        List<String[]> csvBody = reader.readAll();
        StringBuilder sb= new StringBuilder();

        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            List<String> list = new ArrayList<String>(Arrays.asList(strArray));
            list.remove(strArray.length-1);
//            System.out.println(list);
            strArray = list.toArray(new String[0]);
            csvBody.remove(i);
            csvBody.add(i,strArray);
//            if(!strArray[0].equals(strTableName)){
//                continue;
//            }
            boolean isTableName = strArray[0].equals(strTableName);
            String currColName = strArray[1];
            boolean isCurrCol = currColName.equals(strarrColName[0])
                    || currColName.equals(strarrColName[1])
                    || currColName.equals(strarrColName[2]);
//            String print = "";
            for(int j=0; j<strArray.length; j++){
//                print+= strArray[j]+",";
                if(isTableName && isCurrCol && j==4 && strArray[j].equalsIgnoreCase("null")){ //String to be replaced
                    strArray[j] = indexName; //Target replacement
                }else if(isTableName && isCurrCol && j==5 && strArray[j].equalsIgnoreCase("null")){ //String to be replaced
                    strArray[j] = indexType; //Target replacement
                }
                sb.append(strArray[j]+",");
            }
            sb.append("\r\n");
//            System.out.println(print);
        }
        reader.close();
        metafile.delete();
//        metafile=new File(filePath);
        fw=new FileWriter(metafile,false);
        fw.append(sb.toString()).flush();
        fw=new FileWriter(metafile,true);
        loadMetaData();

        // Write to CSV file which is open
//        CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
//        writer.writeAll(csvBody);
//        writer.flush();
//        writer.close();
    }
    public HashSet<String> getColumnNamesWithIndex(String strTableName){
        HashSet<String> columnNamesWithIndex = new HashSet<>();
        Vector<Column> columns = columnsOfMetaData.get(strTableName);
        for(Column col: columns){
            if(!col.getIndexType().equals("null")){
                columnNamesWithIndex.add(col.getColumnName());
            }
        }
        return columnNamesWithIndex;
    }
}


