import exceptions.DBAppException;
import tableAndCo.Table;

import java.io.*;
import java.util.*;

public class DBApp implements Serializable {

    private Vector<String> tableNames;
    private Metadata metaData;

    public DBApp(){
        this.tableNames = new Vector<String>();
        init();
    }
    public void init(){
        try {
            metaData = new Metadata("data/metadata.csv");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String,String> htblColNameType,
                            Hashtable<String,String> htblColNameMin,
                            Hashtable<String,String> htblColNameMax ) throws DBAppException {

        for(int i = 0;i<tableNames.size();i++){
            if (tableNames.get(i).equals(strTableName)){
                throw new DBAppException("This table already exists");
            }
        }

        Set<Map.Entry<String, String>> entrySet = htblColNameType.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (!isSupported(entry.getValue())){
                throw new DBAppException("This data type is not supported");
            }
        }
        Metadata metadata = new Metadata("metadata.csv");
        metadata.writeMetaData(
                "metadata.csv",
                strTableName,
                strClusteringKeyColumn,
                htblColNameType,
                htblColNameMin,
                htblColNameMax
        );
        int maxPageSize = readFromConfig("MaximumRowsCountinTablePage");
        new Table(strTableName , htblColNameType.size() , maxPageSize);
        tableNames.add(strTableName);
    }
//    public void createIndex(String strTableName , String[] strarrColName) throws DBAppException{
//
//    }
    public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException{
        if (!tableNames.contains(strTableName)){
            throw new DBAppException("This Table is not present");
        }
        int tupleSize = metaData.getTupleSize(strTableName);
        Vector<String> columnNames = metaData.getColumnNames(strTableName);
        Set<Map.Entry<String, Object>> entrySet = htblColNameValue.entrySet();
        if(entrySet.size() != columnNames.size()){
            throw new DBAppException("There are missing or extra column(s)");
        }
        for (Map.Entry<String, Object> entry : entrySet) {
            if(!columnNames.contains(entry.getKey())){
                throw new DBAppException("The Column names aren't correct");
            }
        }

    }
    public void updateTable(String strTableName, String strClusteringKeyValue, Hashtable<String,Object> htblColNameValue ) throws DBAppException{

    }
    public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException{

    }
//  public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException {
//
//  }
    public boolean isSupported(String dt){
        HashSet<String> supportedDataTypes = new HashSet<String>();
        supportedDataTypes.add("java.lang.Integer");
        supportedDataTypes.add("java.lang.String");
        supportedDataTypes.add("java.lang.Double");
        supportedDataTypes.add("java.util.Date");
        return supportedDataTypes.contains(dt);
    }

    public int readFromConfig(String cfgPath){
        Properties prop = new Properties();
        String fileName = "src/resources/DBApp.config";
        try{
            FileInputStream fis = new FileInputStream(fileName);
            prop.load(fis);

        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
        }
        catch(IOException io){
            System.out.println(io);
        }
        return Integer.parseInt(prop.getProperty(cfgPath));
    }


    public static void main(String[] args) throws DBAppException, IOException {
        String strTableName = "CityShop";
        DBApp dbApp = new DBApp( );
        Hashtable htblColNameType = new Hashtable( );
        htblColNameType.put("ID", "java.lang.Integer");
        htblColNameType.put("Name", "java.lang.String");
        htblColNameType.put("X", "java.lang.Double");
        htblColNameType.put("Y", "java.lang.Double");
        htblColNameType.put("Z", "java.lang.Double");
        htblColNameType.put("Specialization", "java.lang.String");
        htblColNameType.put("Address", "java.lang.String");

        Hashtable htblColNameMin = new Hashtable();
        htblColNameMin.put("ID", "0");
        htblColNameMin.put("Name", "A");
        htblColNameMin.put("X", "0");
        htblColNameMin.put("Y", "0");
        htblColNameMin.put("Z", "0");
        htblColNameMin.put("Specialization", "A");
        htblColNameMin.put("Address", "A");
        Hashtable htblColNameMax = new Hashtable();
        htblColNameMax.put("ID", "10000");
        htblColNameMax.put("Name", "ZZZZZZZZZZZ");
        htblColNameMax.put("X", "10000");
        htblColNameMax.put("Y", "10000");
        htblColNameMax.put("Z", "10000");
        htblColNameMax.put("Specialization", "ZZZZZZZZZZZ");
        htblColNameMax.put("Address","ZZZZZZZZZZZ" );
        dbApp.createTable( strTableName, "ID", htblColNameType, htblColNameMin, htblColNameMax );
    }
}