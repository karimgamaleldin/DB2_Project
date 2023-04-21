import exceptions.DBAppException;
import helpers.Comparison;
import helpers.FileManipulation;
import metadata.Column;
import metadata.Metadata;
import tableAndCo.Page;
import tableAndCo.Table;

import java.io.*;
import java.util.*;

public class DBApp implements Serializable {

    private static Vector<String> tables;
    private Metadata metaData;
    private String pagesFilepath;
    private String tablesFilepath;
    public DBApp(){
        this.tables = new Vector<String>();
        this.pagesFilepath = "data/pages/";
        this.tablesFilepath = "data/tables/";
        init();
    }
    public void init(){
        try {
            metaData = new Metadata("metadata.csv");
            FileManipulation.createDirectory("data");
            FileManipulation.createDirectory(this.pagesFilepath);
            FileManipulation.createDirectory(this.tablesFilepath);
            FileManipulation.loadFilesFromDirectory(this.tablesFilepath,this.tables);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String,String> htblColNameType,
                            Hashtable<String,String> htblColNameMin,
                            Hashtable<String,String> htblColNameMax ) throws DBAppException, IOException, ClassNotFoundException {

//        for(int i = 0; i< tables.size(); i++){
//            Table currTable = FileManipulation.loadTable(tables.get(i));
//            String currentTableName = currTable.getTableName();
//            if (currentTableName.equals(strTableName)){
//                ;
//                throw new DBAppException("This table already exists");
//            }
//        }

        Set<Map.Entry<String, String>> entrySet = htblColNameType.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (!isSupported(entry.getValue())){
                throw new DBAppException("This data type is not supported");
            }
        }
        metaData.writeMetaData(
                strTableName,
                strClusteringKeyColumn,
                htblColNameType,
                htblColNameMin,
                htblColNameMax
        );
        int maxPageSize = FileManipulation.readFromConfig("MaximumRowsCountinTablePage");
        Table newTable = new Table(strTableName , htblColNameType.size() , maxPageSize, strClusteringKeyColumn);
        tables.add(newTable.getFilepath());
    }
//    public void createIndex(String strTableName , String[] strarrColName) throws DBAppException{
//
//    }
    public int checkTablePresent(String strTableName) throws DBAppException, IOException, ClassNotFoundException {
        int tableIndex = -1;
        for(int i = 0; i< tables.size(); i++){
            Table currTable = FileManipulation.loadTable(this.tables.get(i));
            String currentTableName = currTable.getTableName();
            if (currentTableName.equals(strTableName)){
                tableIndex = i;
                break;
            }
        }
        if (tableIndex==-1) {
            throw new DBAppException("This Table is not present");
        }
        return tableIndex;
    }
    public void checkHtblValid(String strTableName, Hashtable<String,Object> htblColNameValue, boolean insert) throws Exception {
        Vector<String> columnNames = metaData.getColumnNames(strTableName);
        Set<Map.Entry<String, Object>> entrySet = htblColNameValue.entrySet();
        if (insert) {
            if(entrySet.size() != columnNames.size()){
                throw new DBAppException("There are missing or extra column(s)");
            }
        }
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!columnNames.contains(key)){
                throw new DBAppException("The metadata.Column names aren't correct");
            }
            String columnType = metaData.getColumnType(strTableName,key);
            String valClass = ((""+value.getClass()).replaceAll("class","")).replaceAll(" ","");
//            System.out.println(key+": "+valClass+","+columnType);
            if(valClass.compareTo(columnType)!=0){
                throw new DBAppException("wrong entry datatype");
            }
//            System.out.println(key+": "+value);
            Vector<Object> columnMinAndMax = metaData.getColumnMinAndMax(strTableName,key,columnType);
            Object min = columnMinAndMax.get(0);
            Object max = columnMinAndMax.get(1);
//            System.out.println(max.toString());
            boolean lessThanMin = Comparison.compareTo(value,min,columnType)<0;
            boolean greaterThanMax = Comparison.compareTo(value,max,columnType)>0;
//            System.out.println(lessThanMin+" ,"+ greaterThanMax);
            if(lessThanMin || greaterThanMax){
                throw new DBAppException("entry is less than min or greater than max");
            }
        }
    }
    public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws Exception {
        if (!tables.contains(this.tablesFilepath+strTableName+".class")){
            throw new DBAppException("This Table is not present");
        }
        int tableIndex = checkTablePresent(strTableName);
        checkHtblValid(strTableName, htblColNameValue, true);
        Table toBeInsertedInTable = FileManipulation.loadTable(this.tables.get(tableIndex));
        toBeInsertedInTable.insert(htblColNameValue);
    }
    public void updateTable(String strTableName, String strClusteringKeyValue, Hashtable<String,Object> htblColNameValue ) throws Exception {
        Vector<Column> columns = metaData.getColumnsOfMetaData().get(strTableName);
        int tableIndex = checkTablePresent(strTableName);
        checkHtblValid(strTableName, htblColNameValue, false);
        Table currTable = FileManipulation.loadTable(this.tables.get(tableIndex));
        currTable.update(strClusteringKeyValue, htblColNameValue, columns);
    }
    public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws Exception{
        int tableIndex = checkTablePresent(strTableName);
        checkHtblValid(strTableName, htblColNameValue, false);
        Table currTable = FileManipulation.loadTable(this.tables.get(tableIndex));
        currTable.delete(htblColNameValue);

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


    public static void main(String[] args) throws Exception {
        String strTableName = "CityShop";
        String strTableName2="CityShop2";
        String strTableName3="CityShop3";
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
        dbApp.createTable( strTableName2, "ID", htblColNameType, htblColNameMin, htblColNameMax );
        dbApp.createTable( strTableName3, "ID", htblColNameType, htblColNameMin, htblColNameMax );

        Hashtable htblColNameValue = new Hashtable( );
        Hashtable htblColNameValue2 = new Hashtable( );
        Hashtable htblColNameValue3 = new Hashtable( );
        Hashtable htblColNameValue4 = new Hashtable( );
        Hashtable htblColNameValue5 = new Hashtable( );

        htblColNameValue.put("ID", new Integer( 1 ));
        htblColNameValue.put("Name", "hgfhg");
        htblColNameValue.put("X", new Double(1000));
        htblColNameValue.put("Y", new Double(1000));
        htblColNameValue.put("Z", new Double(1000));
        htblColNameValue.put("Specialization", "b");
        htblColNameValue.put("Address","c" );

        htblColNameValue2.put("ID", new Integer( 5 ));
        htblColNameValue2.put("Name", "dgdf");
        htblColNameValue2.put("X", new Double(1000));
        htblColNameValue2.put("Y", new Double(1000));
        htblColNameValue2.put("Z", new Double(1000));
        htblColNameValue2.put("Specialization", "b");
        htblColNameValue2.put("Address","c" );

        htblColNameValue3.put("ID", new Integer( 3 ));
        htblColNameValue3.put("Name", "dfgfd");
        htblColNameValue3.put("X", new Double(1000));
        htblColNameValue3.put("Y", new Double(1000));
        htblColNameValue3.put("Z", new Double(1000));
        htblColNameValue3.put("Specialization", "b");
        htblColNameValue3.put("Address","c" );

        htblColNameValue4.put("ID", new Integer( 2 ));
        htblColNameValue4.put("Name", "dfgdf");
        htblColNameValue4.put("X", new Double(1000));
        htblColNameValue4.put("Y", new Double(1000));
        htblColNameValue4.put("Z", new Double(1000));
        htblColNameValue4.put("Specialization", "b");
        htblColNameValue4.put("Address","c" );

        htblColNameValue5.put("ID", new Integer( 4 ));
        htblColNameValue5.put("Name", "sefdsd");
        htblColNameValue5.put("X", new Double(1000));
        htblColNameValue5.put("Y", new Double(1000));
        htblColNameValue5.put("Z", new Double(1000));
        htblColNameValue5.put("Specialization", "b");
        htblColNameValue5.put("Address","c" );

        System.out.println("insert 1");
        dbApp.insertIntoTable( strTableName , htblColNameValue );
        System.out.println("insert 5");
        dbApp.insertIntoTable( strTableName , htblColNameValue2 );
        System.out.println("insert 3");
        dbApp.insertIntoTable( strTableName , htblColNameValue3 );
        System.out.println("insert 2");
        dbApp.insertIntoTable( strTableName , htblColNameValue4 );
        System.out.println("insert 4");
        dbApp.insertIntoTable( strTableName , htblColNameValue5 );

    }
}