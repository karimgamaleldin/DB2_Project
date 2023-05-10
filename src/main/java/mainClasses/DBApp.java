package mainClasses;

import index.Octree;
import sqlterm.SQLTerm;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;


public class DBApp implements Serializable {

    private Vector<String> tables;
    private Metadata metaData;
    private String pagesFilepath;
    private String tablesFilepath;
    private String indicesFilepath;
    private int maxPageSize;
    public DBApp(){
        this.tables = new Vector<String>();        //src/resources/data/pages
        this.pagesFilepath = "src/main/resources/data/";
        //resources/data/tables
        this.tablesFilepath = "src/main/resources/data/tables/";
        this.indicesFilepath = "src/main/resources/data/indices/";

    }
    public void init(){
        try {
            FileManipulation.createDirectory("src/main/resources/data");
            FileManipulation.createDirectory(this.pagesFilepath);
            FileManipulation.createDirectory(this.tablesFilepath);
            FileManipulation.createDirectory(this.indicesFilepath);
            metaData = new Metadata("src/main/resources/metadata.csv");
            this.maxPageSize = FileManipulation.readFromConfig("MaximumRowsCountinTablePage");
            this.tables = FileManipulation.loadFilesFromDirectory(this.tablesFilepath);
        }catch(Exception e){
            System.out.println("init: "+e);
        }
    }

    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String,String> htblColNameType,
                            Hashtable<String,String> htblColNameMin,
                            Hashtable<String,String> htblColNameMax ) throws DBAppException {

        try{
            for(int i = 0; i< tables.size(); i++){
                Table currTable = FileManipulation.loadTable(this.tablesFilepath,tables.get(i));
                String currentTableName = currTable.getTableName();
                if (currentTableName.equals(strTableName)) {
                    throw new DBAppException("This table already exists");
                }
            }

            Set<Map.Entry<String, String>> entrySet = htblColNameType.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                if (!isSupported(entry.getValue())){
                    throw new DBAppException("data type: "+entry.getValue()+" is not supported");
                }
            }
            checkMinAndMaxHtbl(htblColNameType, htblColNameMin, htblColNameMax);
            metaData.writeMetaData(
                    strTableName,
                    strClusteringKeyColumn,
                    htblColNameType,
                    htblColNameMin,
                    htblColNameMax
            );
            Table newTable = new Table(strTableName , htblColNameType.size() , this.maxPageSize, strClusteringKeyColumn);
            tables.add(newTable.getTableName());
        }catch (Exception e){
            e.printStackTrace();
            throw new DBAppException(e.getMessage());
        }
    }
    public void checkMinAndMaxHtbl(Hashtable<String,String> htblColNameType,
                                   Hashtable<String,String> htblColNameMin,
                                   Hashtable<String,String> htblColNameMax) throws DBAppException {
        if(htblColNameType.size()!=htblColNameMax.size()){
            throw new DBAppException("the size of htblColNameType is not equal to size of htblColNameMax");
        }
        if(htblColNameType.size()!=htblColNameMin.size()){
            throw new DBAppException("the size of htblColNameType is not equal to size of htblColNameMin");
        }
        HashSet<String> maxColumnNames = new HashSet<String>();
        for(String key: htblColNameMax.keySet()){
            maxColumnNames.add(key);
        }
        HashSet<String> minColumnNames = new HashSet<String>();
        for(String key: htblColNameMin.keySet()){
            minColumnNames.add(key);
        }
        //check same keys in htbl type and htbl max and min
        for(String key: htblColNameType.keySet()){
            if(!maxColumnNames.contains(key)){
                throw new DBAppException("htblColNameMax does not contain the key: "+key);
            }
            if(!minColumnNames.contains(key)){
                throw new DBAppException("htblColNameMin does not contain the key: "+key);
            }
        }
        // check values of max and min are of correct corresponding types
        for(String key: htblColNameMax.keySet()){
            String value = htblColNameMax.get(key);
            String type = htblColNameType.get(key);
            try{
                Column.adjustDataType(value,type);
            } catch (Exception e){
                throw new DBAppException(value+" is not of type "+ type);
            }
        }

        for(String key: htblColNameMin.keySet()){
            String value = htblColNameMin.get(key);
            String type = htblColNameType.get(key);
            try{
                Column.adjustDataType(value,type);
            } catch (Exception e){
                throw new DBAppException(value+" is not of type "+ type);
            }
        }

    }
    public void createIndex(String strTableName , String[] strarrColName) throws Exception {
        metaData.loadMetaData();
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
        Vector<Object> firstAttribute= null;
        Vector<Object> secondAttribute= null;
        Vector<Object> thirdAttribute= null;
        Table toBeInsertedInTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
        firstAttribute=Metadata.getColumnMinAndMax(strTableName,strarrColName[0]);//getMinMax(columnNames,strarrColName[0],strTableName);
        secondAttribute=Metadata.getColumnMinAndMax(strTableName,strarrColName[1]);
        thirdAttribute=Metadata.getColumnMinAndMax(strTableName,strarrColName[2]);
        String indexName = strarrColName[0]+strarrColName[1]+strarrColName[2]+"Index";
        Octree octree=new Octree(firstAttribute.get(0),firstAttribute.get(1),secondAttribute.get(0),secondAttribute.get(1),
                thirdAttribute.get(0),thirdAttribute.get(1),3,
                strarrColName[0],strarrColName[1],strarrColName[2],indexName);
        if(!toBeInsertedInTable.isTableEmpty()){
            // insert all the existing values in octree
            for(int i=0;i<toBeInsertedInTable.getTablePages().size();i++){
                String currPagePath = toBeInsertedInTable.getTablePages().get(i);
                Page currPage = FileManipulation.loadPage(currPagePath);
                for(int j=0;j<currPage.getPageTuples().size();j++){
                    Object valOfCol1 = currPage.getPageTuples().get(j).getTupleAttributes().get(strarrColName[0]);
                    Object valOfCol2 = currPage.getPageTuples().get(j).getTupleAttributes().get(strarrColName[1]);
                    Object valOfCol3 = currPage.getPageTuples().get(j).getTupleAttributes().get(strarrColName[2]);
                    octree.insertIntoOctree(valOfCol1,valOfCol2,valOfCol3,currPagePath);
                }
            }
        }
        Metadata.updateCSV(strTableName,strarrColName,indexName,"Octree");
        metaData.loadMetaData();
        FileManipulation.createSerFile(octree.getFilepath());
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
                        throw new DBAppException(key+" is a non existing column");
                    }
                }
                Vector<String> missingColumnNames = new Vector<String>();
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
            //((""+value.getClass()).replaceAll("class","")).replaceAll(" ","")
            String valClass = value.getClass().getName();
//            System.out.println(key+": "+valClass+","+columnType);
            if(valClass.compareTo("java.SimulatingNull")==0){
                continue;
            }
            if(valClass.compareTo(columnType)!=0){
                if(!(valClass.equals("java.lang.Integer")&&columnType.equals("java.lang.Double"))){
                    throw new DBAppException("Please check " + key + " as it has a wrong data type");
                }
                else {
                    htblColNameValue.put(key,Double.parseDouble("" + value));
                }
//                System.out.println(valClass+"-"+columnType);
            }
//            System.out.println(key+": "+value);
            Vector<Object> columnMinAndMax = Metadata.getColumnMinAndMax(strTableName,key);
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
    public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException {
        try{
            metaData.loadMetaData();
            int tableIndex = checkTablePresent(strTableName);
            checkHtblValid(strTableName, htblColNameValue, true);
            Table toBeInsertedInTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
            toBeInsertedInTable.insert(htblColNameValue);
            toBeInsertedInTable = null;
            System.gc();
        }catch (Exception e){
            throw new DBAppException(e.getMessage());
        }
    }
    public void updateTable(String strTableName, String strClusteringKeyValue, Hashtable<String,Object> htblColNameValue ) throws DBAppException {
        try{
            metaData.loadMetaData();
//            Vector<Column> columns = metaData.getColumnsOfMetaData().get(strTableName);
            int tableIndex = checkTablePresent(strTableName);
            checkHtblValid(strTableName, htblColNameValue, false);
            Table currTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
            if(htblColNameValue.containsKey(currTable.getClusteringKey())){
                throw new DBAppException(currTable.getClusteringKey()+" clustering key should not be in the htbl");
            }
            currTable.update(strClusteringKeyValue, htblColNameValue);
            currTable = null;
            System.gc();
        } catch (Exception e){
            throw new DBAppException(e.getMessage());
        }
    }
    public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException{
        try {
            metaData.loadMetaData();
            int tableIndex = checkTablePresent(strTableName);
            checkHtblValid(strTableName, htblColNameValue, false);
            Table currTable = FileManipulation.loadTable(this.tablesFilepath, this.tables.get(tableIndex));
            currTable.delete(htblColNameValue);
            currTable = null;
            System.gc();
        } catch (Exception e){
            throw new DBAppException(e.getMessage());
        }
    }
//  public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws java.DBAppException {
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
    public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException, IOException, ClassNotFoundException {
        checkSelectQuery(arrSQLTerms , strarrOperators);
        String tableName = arrSQLTerms[0].get_strTableName();
        Table T = FileManipulation.loadTable(this.tablesFilepath , tableName);
        Vector<Vector<Tuple>> results = new Vector<Vector<Tuple>>();
        for(int i = 0; i < arrSQLTerms.length ; i++){
            String columnName = arrSQLTerms[i].get_strColumnName();
            String operator = arrSQLTerms[i].get_strOperator();
            Object value = arrSQLTerms[i].get_objValue();
            Vector<Tuple> temp =  T.selectDataFromTable(columnName , value , operator);
            results.add(temp);
        }
        // to make the operators between the different queries
        boolean andFlag = true;
        boolean useIndex = true;

        for(int i = 0 ; i < strarrOperators.length; i++){
            if(strarrOperators[i].equals("and")) andFlag = false;
        }
        if(andFlag && useIndex){
//            Vector<Tuple> result =
        }
        Vector<Tuple> result = results.get(0);
        for(int i = 1 ; i <= strarrOperators.length ; i++){
            String op = strarrOperators[i-1];
            // should we throw an exception if the operator is not AND , OR , XOR and check that strarrOperators length is less than results by 1
            result = op.equals("XOR") ? XORSelect(result , results.get(i)) : op.equals("OR") ? ORSelect(result , results.get(i)) : ANDSelect(result , results.get(i));
        }

        return result.iterator();
    }
    public Vector<Tuple> XORSelect(Vector<Tuple> vec1 , Vector<Tuple> vec2){
        Vector<Tuple> result = new Vector<Tuple>();
        String clusteringKey = vec1.get(0).getClusteringKey();
        Hashtable<Object , Object> hash1 = new Hashtable<Object , Object>();
        Hashtable<Object , Object> hash2 = new Hashtable<Object , Object>();
        for(int i = 0 ; i < vec1.size() ; i++){
            Tuple tup = vec1.get(i);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            hash1.put(key,tup);
        }
        for(int i = 0 ; i < vec2.size() ; i++){
            Tuple tup = vec2.get(i);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            hash2.put(key,tup);
        }
        for(int j = 0 ; j < vec1.size() ; j++) {
            Tuple tup = vec1.get(j);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            if (!hash2.containsKey(key)) {
                result.add(tup);
            }
        }
        for(int j = 0 ; j < vec2.size() ; j++) {
            Tuple tup = vec2.get(j);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            if (!hash1.containsKey(key)) {
                result.add(tup);
            }
        }
        return result;
    }
    public Vector<Tuple> ORSelect(Vector<Tuple> vec1 , Vector<Tuple> vec2){
        Vector<Tuple> result = new Vector<Tuple>();
        String clusteringKey = vec1.get(0).getClusteringKey();
        Hashtable<Object , Tuple> hash1 = new Hashtable<Object , Tuple>();
        for(int i = 0 ; i < vec1.size() ; i++){
            Tuple tup = vec1.get(i);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            hash1.put(key,tup);
            result.add(tup);
        }
        for(int j = 0 ; j < vec2.size() ; j++){
            Tuple tup = vec2.get(j);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            if(!hash1.containsKey(key)){
                result.add(tup);
            }
        }
        return result;
    }
    public Vector<Tuple> ANDSelect(Vector<Tuple> vec1 , Vector<Tuple> vec2){
        Vector<Tuple> result = new Vector<Tuple>();
        String clusteringKey = vec1.get(0).getClusteringKey();
        Hashtable<Object , Tuple> hash1 = new Hashtable<Object , Tuple>();
        for(int i = 0 ; i < vec1.size() ; i++){
            Tuple tup = vec1.get(i);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            hash1.put(key,tup);
        }
        for(int j = 0 ; j < vec2.size() ; j++){
            Tuple tup = vec2.get(j);
            Object key = tup.getTupleAttributes().get(clusteringKey);
            if(hash1.containsKey(key)){
                result.add(tup);
            }
        }
        return result;
    }
    public void checkSelectQuery(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException, IOException, ClassNotFoundException {
        String[] operators = {">", ">=", "<", "<=", "!=" , "="};
        for(int i = 0; i < arrSQLTerms.length ; i++){
            if(checkTablePresent(arrSQLTerms[i].get_strTableName()) == -1){
                throw new DBAppException("Table specified in the query isn't present");
            }
            else{
                Vector<String> columns = metaData.getColumnNames(arrSQLTerms[i].get_strTableName());
                if (!columns.contains(arrSQLTerms[i].get_strColumnName())){
                    throw new DBAppException("Column Name specified in the query isn't present");
                }
                else {
                    boolean flag = false;
                    for(int j = 0 ; j < operators.length ; j++){
                        if(operators[j].equals(arrSQLTerms[i].get_strOperator())){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        throw new DBAppException("operator specified in the query isn't supported");
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
//      Hashtable<String, Object> tuple0 = new Hashtable<>();
//        tuple0.put("age", 0);
//        tuple0.put("name", "Soubra");
//        tuple0.put("gpa", 1.6);*/

//        Hashtable<String, Object> tuple0 = new Hashtable<>();
//        tuple0.put("age", 0);
//        tuple0.put("name", "Soubra");
//        tuple0.put("gpa", 1.6);

//        Hashtable<String, Object> tuple1 = new Hashtable<>();
//        tuple1.put("age", 1);
//        tuple1.put("name", "Kimo2");
//        tuple1.put("gpa", 5.0);
//        tuple1.put("dob" , new SimpleDateFormat("dd-MM-yyyy").parse("05-07-2002"));
//
//        Hashtable<String, Object> tuple2 = new Hashtable<>();
//        tuple2.put("age", 2);
//        tuple2.put("name", "Omar");
//        tuple2.put("gpa", 4);
//        tuple2.put("dob",new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1956"));
//
//        Hashtable<String, Object> tuple3 = new Hashtable<>();
//        tuple3.put("age", 3);
//        tuple3.put("name", "Ahmed");
//        tuple3.put("gpa", 0.9);
//        tuple3.put("dob" , new SimpleDateFormat("dd-MM-yyyy").parse("05-12-1999"));
//
//        Hashtable<String, Object> tuple4 = new Hashtable<>();
//        tuple4.put("age", 4);
//        tuple4.put("name", "biso");
//        tuple4.put("gpa", 2.3);
//        tuple4.put("dob" , new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2024"));
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
//        tuple15.put("age", 29);
//        tuple15.put("name", "afterMod");
//
//        Hashtable<String, Object> tuple16 = new Hashtable<>();
//        tuple16.put("age", 18);
//        tuple16.put("name", "kimo");
//        tuple15.put("gpa", 4);
//
//        Hashtable<String, Object> tuple17 = new Hashtable<>();
//        tuple17.put("age", 19);
////        tuple17.put("name", "kimo");
//        tuple17.put("gpa", 3.0);
//
//        Hashtable<String, Object> tuple18 = new Hashtable<>();
//        tuple18.put("age", 17);
//        tuple18.put("name", "kimo");
////        tuple18.put("gpa", 3.0);
//        tuple18.put("gpa", 3.0);
//
//        Hashtable<String, Object> tuple19 = new Hashtable<>();
//        tuple19.put("age", 16);
//        tuple19.put("name", "kimo");
////        tuple19.put("gpa", 3.0);
//        tuple19.put("gpa", 3.0);
//
        Hashtable<String, String> htblColNameType = new Hashtable<>();
        htblColNameType.put("age", "java.lang.Integer");
        htblColNameType.put("name", "java.lang.String");
        htblColNameType.put("gpa", "java.lang.Double");
        htblColNameType.put("dob", "java.util.Date");
//        htblColNameType.put("job", "java.lang.String");

        Hashtable<String, String> htblColNameMin = new Hashtable<>();
        htblColNameMin.put("age", "0");
        htblColNameMin.put("name", "A");
        htblColNameMin.put("gpa", "0");
        htblColNameMin.put("dob", "1950-12-31");
//        htblColNameMin.put("job", "1900-012-31");

        Hashtable<String, String> htblColNameMax = new Hashtable<>();
        htblColNameMax.put("age", "40");
        htblColNameMax.put("name", "ZZZZZZZZZZ");
        htblColNameMax.put("gpa", "5");
        htblColNameMax.put("dob", "2023-12-31");
//        htblColNameMax.put("job", "e");

        DBApp dbApp = new DBApp();
        dbApp.init();
//        dbApp.createTable("Students", "dob", htblColNameType, htblColNameMin, htblColNameMax);
//        dbApp.createTable("Students2", "age", htblColNameType, htblColNameMin, htblColNameMax);
//        dbApp.createTable("Students3", "age", htblColNameType, htblColNameMin, htblColNameMax);
        dbApp.createIndex("Students2",new String[]{"name","gpa","dob"});
//////     //    dbApp.insertIntoTable("Students", tuple0);
//         dbApp.insertIntoTable("Students", tuple1);
//         dbApp.insertIntoTable("Students", tuple2);
//         dbApp.insertIntoTable("Students", tuple6);
//         dbApp.insertIntoTable("Students", tuple7);
//         dbApp.insertIntoTable("Students", tuple8);
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
//         dbApp.insertIntoTable("Students", tuple19);


//         Hashtable<String, Object> updateHtbl = new Hashtable<>();
//         updateHtbl.put("name", "a");
//         updateHtbl.put("gpa", 2);
//         dbApp.updateTable("Students", "2002-07-05", updateHtbl);

//         updateHtbl.put("name", "boniiiii");

//         Hashtable<String,Object> deletingCriteria0 = new Hashtable<>();
//         Hashtable<String,Object> deletingCriteria1 = new Hashtable<>();
//         Hashtable<String,Object> deletingCriteria2 = new Hashtable<>();
//         Hashtable<String,Object> deletingCriteria3 = new Hashtable<>();
//         deletingCriteria0.put( "dob",new SimpleDateFormat("dd-MM-yyyy").parse("05-12-1999"));
//         deletingCriteria0.put("gpa",2);
//         deletingCriteria0.put("name","a");
//         deletingCriteria1.put("gpa", 0.9);
//         deletingCriteria2.put( "name", "nada");
//         deletingCriteria3.put( "age", 5);
//        dbApp.deleteFromTable("Students", deletingCriteria0);
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
//            p.serialize();l
//        }
    }
}