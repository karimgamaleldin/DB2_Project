package main.java;

import index.Octree;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class DBApp implements Serializable {

    private Vector<String> tables;
    private Metadata metaData;
    private String pagesFilepath;
    private String tablesFilepath;
    private int maxPageSize;
    public DBApp(){
        this.tables = new Vector<String>();
        //src/resources/data/pages
        this.pagesFilepath = "src/main/resources/data/";
        //resources/data/tables
        this.tablesFilepath = "src/main/resources/data/tables/";
//        FileManipulation.loadFilesFromDirectory(this.tablesFilepath,this.tables);

    }
    public void init(){
        try {
            FileManipulation.createDirectory("src/main/resources/data");
//            System.out.println(1);
            FileManipulation.createDirectory(this.pagesFilepath);
//            System.out.println(2);
            FileManipulation.createDirectory(this.tablesFilepath);
//            System.out.println(3);
            metaData = new Metadata("src/main/resources/metadata.csv");
//            System.out.println(4);
            this.maxPageSize = FileManipulation.readFromConfig("MaximumRowsCountinPage");
//            System.out.println(5);
            this.tables = FileManipulation.loadFilesFromDirectory(this.tablesFilepath);
        }catch(Exception e){
            System.out.println("init: "+e);
        }
    }

    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String,String> htblColNameType,
                            Hashtable<String,String> htblColNameMin,
                            Hashtable<String,String> htblColNameMax ) throws DBAppException, IOException, ClassNotFoundException {
        for(int i = 0; i< tables.size(); i++){
            Table currTable = FileManipulation.loadTable(this.tablesFilepath,tables.get(i));
            String currentTableName = currTable.getTableName();
            if (currentTableName.equals(strTableName)){
                throw new DBAppException("This table already exists");
            }
        }

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
        Table newTable = new Table(strTableName , htblColNameType.size() , this.maxPageSize, strClusteringKeyColumn);
        tables.add(newTable.getTableName());
    }
    public void createIndex(String strTableName , String[] strarrColName) throws DBAppException, IOException, ClassNotFoundException, ParseException {
        if(strarrColName.length<3){
            throw new DBAppException("Array missing index attributes ");
        } else if (strarrColName.length>3) {
            throw new DBAppException("index attributes should be only 3 ");
        }
        int tableIndex = checkTablePresent(strTableName);
        if(tableIndex==-1){
            throw new DBAppException("table not found");
        }
        Vector<String> columnNames = metaData.getColumnNames(strTableName);
        for (int i=0;i<3;i++){
            if (!columnNames.contains(strarrColName[i])){
                throw new DBAppException("columns specified are not in the table");
            }
        }
        Object[] firstAttribute= null;
        Object[] secondAttribute= null;
        Object[] thirdAttribute= null;
        Table toBeInsertedInTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
        firstAttribute=getMinMax(columnNames,strarrColName[0],strTableName);
        secondAttribute=getMinMax(columnNames,strarrColName[1],strTableName);
        thirdAttribute=getMinMax(columnNames,strarrColName[2],strTableName);
        Octree octree=new Octree(firstAttribute[0],firstAttribute[1],secondAttribute[0],secondAttribute[1],thirdAttribute[0],thirdAttribute[1],1);
        if(!toBeInsertedInTable.isTableEmpty()){

             // insert all the existing values in octree
            //ezay b2a el 3elm 3end allah
        }

    }
    public Object[] getMinMax(Vector<String> columnNames,String columnNeededString,String strTableName) {
        String[] minMax=null;
        Column columnNeeded=null;
//        for (int i = 0; i < columnNames.size(); i++) {
//            if (columnNames.get(i) == columnNeededString) {
//                type = metaData.getColumnType(strTableName, columnNeededString);
//                break;
//            }
//        }
        Vector<Column> columns = metaData.getColumnsOfMetaData().get(strTableName);
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getColumnName() == columnNeededString) {
                columnNeeded=columns.get(i);
                break;
            }
        }
        minMax[0]=columnNeeded.getMin();
        minMax[1]=columnNeeded.getMax();
        return minMax;

    }
    public int checkTablePresent(String strTableName) throws DBAppException, IOException, ClassNotFoundException {
        int tableIndex = -1;
        for(int i = 0; i < tables.size(); i++){
            Table currTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(i));
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
    public void insertingNulls(Vector<String> missingColumnNames,Hashtable<String,Object> htblColNameValue){
        for(int i=0;i<missingColumnNames.size();i++){
            htblColNameValue.put(missingColumnNames.get(i), new SimulatingNull());
        }
    }
    public void checkHtblValid(String strTableName, Hashtable<String,Object> htblColNameValue, boolean insert) throws Exception {
        Vector<String> columnNames = metaData.getColumnNames(strTableName);
        Set<Map.Entry<String, Object>> entrySet = htblColNameValue.entrySet();
        if (insert) {
            String clusteringKey = metaData.getTableClusteringKey(strTableName);
            if(!htblColNameValue.containsKey(clusteringKey)){
                throw new DBAppException(clusteringKey+" is not found in the entry");
            }
            if(entrySet.size() > columnNames.size()){
                throw new DBAppException("There are extra column(s)");
            }else {
                for (Map.Entry<String, Object> entry : entrySet) {
                    String key = entry.getKey();
                    if(!columnNames.contains(key)){
                        throw new DBAppException(key+" is an extra column");
                    }
                }
                Vector<String> missingColumnNames = new Vector<String>();
//                System.out.println(columnNames);
//                System.out.println(htblColNameValue);
                for(int i=0;i<columnNames.size();i++) {
                    if(!htblColNameValue.containsKey(columnNames.get(i))) {
                        missingColumnNames.add(columnNames.get(i));
                    }
                }
                insertingNulls(missingColumnNames,htblColNameValue);
            }
        }
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!columnNames.contains(key)){
                throw new DBAppException("The Column names aren't correct");
            }
            String columnType = metaData.getColumnType(strTableName,key);
            String valClass = ((""+value.getClass()).replaceAll("class","")).replaceAll(" ","");
//            System.out.println(key+": "+valClass+","+columnType);
            if(valClass.compareTo("main.java.SimulatingNull")==0){
                continue;
            }
            if(valClass.compareTo(columnType)!=0){
//                System.out.println(valClass+"-"+columnType);
                throw new DBAppException("Please check " + key + " as it has a wrong data type");
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
                throw new DBAppException("Please Check " + key + " as " + value + " is out of range.");
            }
        }
    }
    public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws Exception {
        metaData.loadMetaData();
//        if (!tables.contains(this.tablesFilepath+strTableName+".class")){
//            throw new main.java.DBAppException("This Table is not present");
//        }
        int tableIndex = checkTablePresent(strTableName);
        checkHtblValid(strTableName, htblColNameValue, true);
        Table toBeInsertedInTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
        toBeInsertedInTable.insert(htblColNameValue);
    }
    public void updateTable(String strTableName, String strClusteringKeyValue, Hashtable<String,Object> htblColNameValue ) throws Exception {
        metaData.loadMetaData();
        Vector<Column> columns = metaData.getColumnsOfMetaData().get(strTableName);
        int tableIndex = checkTablePresent(strTableName);
        checkHtblValid(strTableName, htblColNameValue, false);
        Table currTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
        currTable.update(strClusteringKeyValue, htblColNameValue, columns);
    }
    public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws Exception{
        metaData.loadMetaData();
        int tableIndex = checkTablePresent(strTableName);
        checkHtblValid(strTableName, htblColNameValue, false);
        Table currTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
        currTable.delete(htblColNameValue);
    }
//  public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws main.java.DBAppException {
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
//        String strTableName = "CityShop";
//        String strTableName2="CityShop2";
//        String strTableName3="CityShop3";
//        String strTableName4="CityShop4";
//        main.java.java.main.java.DBApp dbApp = new main.java.java.main.java.DBApp( );
//        Hashtable htblColNameType = new Hashtable( );
//        htblColNameType.put("ID", "java.lang.Integer");
//        htblColNameType.put("Name", "java.lang.String");
////        htblColNameType.put("X", "java.lang.Double");
////        htblColNameType.put("Y", "java.lang.Double");
////        htblColNameType.put("Z", "java.lang.Double");
////        htblColNameType.put("Specialization", "java.lang.String");
////        htblColNameType.put("Address", "java.lang.String");
////
////        Hashtable htblColNameMin = new Hashtable();
////        htblColNameMin.put("ID", "0");
////        htblColNameMin.put("Name", "A");
////        htblColNameMin.put("X", "0");
////        htblColNameMin.put("Y", "0");
////        htblColNameMin.put("Z", "0");
////        htblColNameMin.put("Specialization", "A");
////        htblColNameMin.put("Address", "A");
////        Hashtable htblColNameMax = new Hashtable();
////        htblColNameMax.put("ID", "10000");
////        htblColNameMax.put("Name", "ZZZZZZZZZZZ");
////        htblColNameMax.put("X", "10000");
////        htblColNameMax.put("Y", "10000");
////        htblColNameMax.put("Z", "10000");
////        htblColNameMax.put("Specialization", "ZZZZZZZZZZZ");
////        htblColNameMax.put("Address","ZZZZZZZZZZZ" );
//
////        dbApp.createTable( strTableName, "ID", htblColNameType, htblColNameMin, htblColNameMax );
////        dbApp.createTable( strTableName2, "ID", htblColNameType, htblColNameMin, htblColNameMax );
////        dbApp.createTable( strTableName3, "ID", htblColNameType, htblColNameMin, htblColNameMax );
////        dbApp.createTable( strTableName4, "ID", htblColNameType, htblColNameMin, htblColNameMax );
//
////        Hashtable htblColNameValue = new Hashtable( );
////        Hashtable htblColNameValue2 = new Hashtable( );
////        Hashtable htblColNameValue3 = new Hashtable( );
////        Hashtable htblColNameValue4 = new Hashtable( );
////        Hashtable htblColNameValue5 = new Hashtable( );
////
////        htblColNameValue.put("ID", new Integer( 1 ));
////        htblColNameValue.put("Name", "hgfhg");
////        htblColNameValue.put("X", new Double(1000));
////        htblColNameValue.put("Y", new Double(1000));
////        htblColNameValue.put("Z", new Double(1000));
////        htblColNameValue.put("Specialization", "b");
////        htblColNameValue.put("Address","c" );
////
////        htblColNameValue2.put("ID", new Integer( 5 ));
////        htblColNameValue2.put("Name", "dgdf");
////        htblColNameValue2.put("X", new Double(1000));
////        htblColNameValue2.put("Y", new Double(1000));
////        htblColNameValue2.put("Z", new Double(1000));
////        htblColNameValue2.put("Specialization", "b");
////        htblColNameValue2.put("Address","c" );
////
////        htblColNameValue3.put("ID", new Integer( 3 ));
////        htblColNameValue3.put("Name", "dfgfd");
////        htblColNameValue3.put("X", new Double(2000));
////        htblColNameValue3.put("Y", new Double(1000));
////        htblColNameValue3.put("Z", new Double(1000));
////        htblColNameValue3.put("Specialization", "b");
////        htblColNameValue3.put("Address","c" );
////
////        htblColNameValue4.put("ID", new Integer( 2 ));
////        htblColNameValue4.put("Name", "dfgdf");
////        htblColNameValue4.put("X", new Double(1000));
////        htblColNameValue4.put("Y", new Double(1000));
////        htblColNameValue4.put("Z", new Double(1000));
////        htblColNameValue4.put("Specialization", "b");
////        htblColNameValue4.put("Address","c" );
////
////        htblColNameValue5.put("ID", new Integer( 4 ));
////        htblColNameValue5.put("Name", "sefdsd");
////        htblColNameValue5.put("X", new Double(2000));
////        htblColNameValue5.put("Y", new Double(1000));
////        htblColNameValue5.put("Z", new Double(1000));
////        htblColNameValue5.put("Specialization", "b");
////        htblColNameValue5.put("Address","c" );
//
////        System.out.println("insert 1");
////        dbApp.insertIntoTable( strTableName , htblColNameValue );
////        System.out.println("insert 5");
////        dbApp.insertIntoTable( strTableName , htblColNameValue2 );
////        System.out.println("insert 3");
////        dbApp.insertIntoTable( strTableName , htblColNameValue3 );
////        System.out.println("insert 2");
////        dbApp.insertIntoTable( strTableName , htblColNameValue4 );
////        System.out.println("insert 4");
////        dbApp.insertIntoTable( strTableName , htblColNameValue5 );
//
//
////        Hashtable htblColNameValueDelete = new Hashtable( );
////        htblColNameValueDelete.put("X", new Double(1000));
////        System.out.println("delete all");
////        dbApp.deleteFromTable( strTableName , htblColNameValueDelete );
//
////        Hashtable htblColNameValueDelete2 = new Hashtable( );
////        htblColNameValueDelete2.put("X", new Double(2000));
////        System.out.println("delete page 1");
////        dbApp.deleteFromTable( strTableName , htblColNameValueDelete2 );
//
////        System.out.println("insert 3");
////        dbApp.insertIntoTable( strTableName , htblColNameValue3 );
////        System.out.println("insert 4");
////        dbApp.insertIntoTable( strTableName , htblColNameValue5 );
//
////        Hashtable htblColNameValueUpdate = new Hashtable( );
////        htblColNameValueUpdate.put("X", new Double(500));
////        htblColNameValueUpdate.put("Y", new Double(4000));
////        System.out.println("update 4");
////        dbApp.updateTable( strTableName ,"4", htblColNameValueUpdate );
//
//
//        // leba tests
////tests/*        Hashtable<String, Object> tuple0 = new Hashtable<>();
////        tuple0.put("age", 0);
////        tuple0.put("name", "Soubra");
////        tuple0.put("gpa", 1.6);*/
//
////        Hashtable<String, Object> tuple0 = new Hashtable<>();
////        tuple0.put("age", 0);
////        tuple0.put("name", "Soubra");
////        tuple0.put("gpa", 1.6);
//
//        Hashtable<String, Object> tuple1 = new Hashtable<>();
//        tuple1.put("age", 1);
//        tuple1.put("name", "Kord");
//        tuple1.put("gpa", 1.6);
//
//        Hashtable<String, Object> tuple2 = new Hashtable<>();
//        tuple2.put("age", 2);
//        tuple2.put("name", "Omar");
//        tuple2.put("gpa", 4.0);
//
//        Hashtable<String, Object> tuple3 = new Hashtable<>();
//        tuple3.put("age", 3);
//        tuple3.put("name", "Ahmed");
//        tuple3.put("gpa", 0.9);
//
//        Hashtable<String, Object> tuple4 = new Hashtable<>();
//        tuple4.put("age", 4);
//        tuple4.put("name", "Malak");
//        tuple4.put("gpa", 2.3);
//
//        Hashtable<String, Object> tuple5 = new Hashtable<>();
//        tuple5.put("age", 5);
//        tuple5.put("name", "Menna");
//        tuple5.put("gpa", 0.8);
//
//        Hashtable<String, Object> tuple6 = new Hashtable<>();
//        tuple6.put("age", 6);
//        tuple6.put("name", "Lobna");
//        tuple6.put("gpa", 1.4);
//
//        Hashtable<String, Object> tuple7 = new Hashtable<>();
//        tuple7.put("age", 7);
//        tuple7.put("name", "boni");
//        tuple7.put("gpa", 3.2);
//
//        Hashtable<String, Object> tuple8 = new Hashtable<>();
//        tuple8.put("age", 8);
//        tuple8.put("name", "nada");
//        tuple8.put("gpa", 2.5);
//
//        Hashtable<String, Object> tuple9 = new Hashtable<>();
//        tuple9.put("age", 9);
//        tuple9.put("name", "noura");
//        tuple9.put("gpa", 3.4);
//
//        Hashtable<String, Object> tuple10 = new Hashtable<>();
//        tuple10.put("age", 10);
//        tuple10.put("name", "nada");
//        tuple10.put("gpa", 0.9);
//
//        Hashtable<String, Object> tuple11 = new Hashtable<>();
//        tuple11.put("age", 11);
//        tuple11.put("name", "nada");
//        tuple11.put("gpa", 0.9);
//
//        Hashtable<String, Object> tuple12 = new Hashtable<>();
//        tuple12.put("age", 12);
//        tuple12.put("name", "sara");
//        tuple12.put("gpa", 0.9);
//
//        Hashtable<String, Object> tuple13 = new Hashtable<>();
//        tuple13.put("age", 13);
//        tuple13.put("name", "sara");
//        tuple13.put("gpa", 0.9);
//
//        Hashtable<String, Object> tuple14 = new Hashtable<>();
//        tuple14.put("age", 14);
//
//        Hashtable<String, Object> tuple15 = new Hashtable<>();
//        tuple15.put("age", 15);
//        tuple15.put("name", "sara");
//
//        Hashtable<String, Object> tuple16 = new Hashtable<>();
//        tuple16.put("age", 20);
//        tuple16.put("name", "kimo");
//        tuple16.put("gpa", 3.0);
//
//        Hashtable<String, Object> tuple17 = new Hashtable<>();
//        tuple17.put("age", 19);
////        tuple17.put("name", "kimo");
//        tuple17.put("gpa", 3.0);
//
//        Hashtable<String, Object> tuple18 = new Hashtable<>();
//        tuple18.put("age", 19);
//        tuple18.put("name", "kimo");
////        tuple18.put("gpa", 3.0);
//        tuple18.put("gpa", 3.0);
//
//
//
//
//        Hashtable<String, String> htblColNameType = new Hashtable<>();
//        htblColNameType.put("age", "java.lang.Integer");
//        htblColNameType.put("name", "java.lang.String");
//        htblColNameType.put("gpa", "java.lang.Double");
//
//        Hashtable<String, String> htblColNameMin = new Hashtable<>();
//        htblColNameMin.put("age", "1");
//        htblColNameMin.put("name", "A");
//        htblColNameMin.put("gpa", "0.7");
//
//        Hashtable<String, String> htblColNameMax = new Hashtable<>();
//        htblColNameMax.put("age", "40");
//        htblColNameMax.put("name", "zzzzzzz");
//        htblColNameMax.put("gpa", "4.0");
//
//        DBApp dbApp = new DBApp();
//        dbApp.init();
//         dbApp.createTable("Students", "age", htblColNameType, htblColNameMin, htblColNameMax);
//         dbApp.createTable("Students2", "age", htblColNameType, htblColNameMin, htblColNameMax);
//     //    dbApp.insertIntoTable("Students", tuple0);
//         dbApp.insertIntoTable("Students", tuple2);
//         dbApp.insertIntoTable("Students", tuple6);
//         dbApp.insertIntoTable("Students", tuple7);
//         dbApp.insertIntoTable("Students", tuple8);
//         dbApp.insertIntoTable("Students", tuple1);
//         dbApp.insertIntoTable("Students", tuple3);
//         dbApp.insertIntoTable("Students", tuple5);
//         dbApp.insertIntoTable("Students", tuple4);
//         dbApp.insertIntoTable("Students", tuple9);
//         dbApp.insertIntoTable("Students", tuple11);
//         dbApp.insertIntoTable("Students", tuple10);
//         dbApp.insertIntoTable("Students", tuple12);
//         dbApp.insertIntoTable("Students", tuple13);
//         dbApp.insertIntoTable("Students", tuple14);
//         dbApp.insertIntoTable("Students", tuple15);
//         dbApp.insertIntoTable("Students", tuple16);
//         dbApp.insertIntoTable("Students", tuple17);
//         dbApp.insertIntoTable("Students", tuple18);

//        dbApp.insertIntoTable("Students", tuple1);
//        dbApp.insertIntoTable("Students", tuple2);
//        dbApp.insertIntoTable("Students", tuple3);
//        dbApp.insertIntoTable("Students", tuple5);
//        dbApp.insertIntoTable("Students", tuple6);
//        dbApp.insertIntoTable("Students", tuple7);
//        dbApp.insertIntoTable("Students", tuple4);



//         Hashtable<String, Object> updateHtbl = new Hashtable<>();
//         updateHtbl.put("name", "kimo");
//         updateHtbl.put("gpa", 1.0);
//         updateHtbl.put("name", "boniiiii");
//         dbApp.updateTable("Students", "20", updateHtbl);

//         Hashtable<String,Object> deletingCriteria0 = new Hashtable<>();
//         Hashtable<String,Object> deletingCriteria1 = new Hashtable<>();
//         Hashtable<String,Object> deletingCriteria2 = new Hashtable<>();
//         Hashtable<String,Object> deletingCriteria3 = new Hashtable<>();
//         deletingCriteria0.put( "age", 10);
//         deletingCriteria0.put("name","nada");
//         deletingCriteria0.put("gpa",0.9);
//         deletingCriteria1.put("gpa", 0.9);
//         deletingCriteria2.put( "name", "nada");
//         deletingCriteria3.put( "age", 5);
//        dbApp.deleteFromTable("Students", deletingCriteria3);
//        deletingCriteria3.put( "age", 6);
//        dbApp.deleteFromTable("Students", deletingCriteria3);
//        deletingCriteria3.put( "age", 7);
//        dbApp.deleteFromTable("Students", deletingCriteria3);
//        deletingCriteria3.put( "age", 8);
//        dbApp.deleteFromTable("Students", deletingCriteria3);



//       dbApp.deleteFromTable("Students", deletingCriteria0);
//        dbApp.deleteFromTable("Students", deletingCriteria1);
//        dbApp.insertIntoTable("Students", tuple12);
//        dbApp.insertIntoTable("Students", tuple13);
//        dbApp.insertIntoTable("Students", tuple13);

//        dbApp.deleteFromTable("Students", deletingCriteria2);
//        dbApp.deleteFromTable("Students", deletingCriteria3);
//    System.out.println(dbApp.tables);
//        Table table = FileManipulation.loadTable(dbApp.tablesFilepath,dbApp.tables.get(0));
//
//        for (String pageName : table.getTablePages()) {
//            Page p = FileManipulation.loadPage(pageName);
//            System.out.println("PAGE " + p.getPageID());
//            System.out.println(p.getPageTuples());
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////            p.serialize();
//        }
    }
}