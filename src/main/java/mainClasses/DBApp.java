package mainClasses;

import index.Octree;
import index.Point;
import parsingHelpers.QueryParserExecutor;
import sqlterm.SQLTerm;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static mainClasses.Comparison.compareTo;


public class DBApp implements Serializable {

    private Vector<String> tables;
    private Metadata metaData;
    private String pagesFilepath;
    private String tablesFilepath;
    private String indicesFilepath;
    private int maxPageSize;

    public Vector<String> getTables() {
        return tables;
    }

    public Metadata getMetaData() {
        return metaData;
    }

    public String getPagesFilepath() {
        return pagesFilepath;
    }

    public String getTablesFilepath() {
        return tablesFilepath;
    }

    public String getIndicesFilepath() {
        return indicesFilepath;
    }

    public int getMaxPageSize() {
        return maxPageSize;
    }

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
                if (currentTableName.equalsIgnoreCase(strTableName)) {
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
    public void createIndex(String strTableName , String[] strarrColName) throws DBAppException {
        try{
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

            HashSet<String> columnNamesWithIndex = metaData.getColumnNamesWithIndex(strTableName);
            if(columnNamesWithIndex.contains(strarrColName[0])||columnNamesWithIndex.contains(strarrColName[1])||columnNamesWithIndex.contains(strarrColName[2])){
                throw new DBAppException("one of the entered columns has an index created on it");
            }

            Vector<Object> firstAttribute= null;
            Vector<Object> secondAttribute= null;
            Vector<Object> thirdAttribute= null;
            Table toBeInsertedInTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(tableIndex));
            firstAttribute=Metadata.getColumnMinAndMax(strTableName,strarrColName[0]);//getMinMax(columnNames,strarrColName[0],strTableName);
            secondAttribute=Metadata.getColumnMinAndMax(strTableName,strarrColName[1]);
            thirdAttribute=Metadata.getColumnMinAndMax(strTableName,strarrColName[2]);
            String indexName = strarrColName[0]+strarrColName[1]+strarrColName[2]+"Index";
            int maxEntries = FileManipulation.readFromConfig("MaximumEntriesinOctreeNode");
            Octree octree=new Octree(firstAttribute.get(0),firstAttribute.get(1),secondAttribute.get(0),secondAttribute.get(1),
                    thirdAttribute.get(0),thirdAttribute.get(1),maxEntries,
                    strarrColName[0],strarrColName[1],strarrColName[2],indexName,strTableName);
            if(!toBeInsertedInTable.isTableEmpty()){
                // insert all the existing values in octree
                for(int i=0;i<toBeInsertedInTable.getTablePages().size();i++){
                    String currPagePath = toBeInsertedInTable.getTablePages().get(i);
                    Page currPage = FileManipulation.loadPage(currPagePath);
                    for(int j=0;j<currPage.getPageTuples().size();j++){
                        Object valOfCol1 = currPage.getPageTuples().get(j).getTupleAttributes().get(strarrColName[0]);
                        Object valOfCol2 = currPage.getPageTuples().get(j).getTupleAttributes().get(strarrColName[1]);
                        Object valOfCol3 = currPage.getPageTuples().get(j).getTupleAttributes().get(strarrColName[2]);
                        Point insertPoint = new Point(valOfCol1,valOfCol2,valOfCol3,currPagePath);
                        octree.insertIntoOctree(insertPoint);
                    }
                }
            }
            Metadata.updateCSV(strTableName,strarrColName,indexName,"Octree");
            FileManipulation.createSerFile(octree.getFilepath());
            octree.saveIntoOctreeFilepath();
        }catch(Exception e){
            throw new DBAppException(e.getMessage());
        }

    }

    public int checkTablePresent(String strTableName) throws DBAppException, IOException, ClassNotFoundException {
        int tableIndex = -1;
        for(int i = 0; i < tables.size(); i++){
            Table currTable = FileManipulation.loadTable(this.tablesFilepath,this.tables.get(i));
            String currentTableName = currTable.getTableName();
            if (currentTableName.equalsIgnoreCase(strTableName)){
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
            htblColNameValue.put(missingColumnNames.get(i), new DBAppNull());
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
            String valClass = value.getClass().getName();
            if(value instanceof DBAppNull){
                continue;
            }
            if(valClass.compareTo(columnType)!=0){
                if(!(valClass.equalsIgnoreCase("java.lang.Integer")&&columnType.equalsIgnoreCase("java.lang.Double"))){
                    throw new DBAppException("Please check " + key + " as it has a wrong data type");
                }
                else {
                    htblColNameValue.put(key,Double.parseDouble("" + value));
                }
            }
            Vector<Object> columnMinAndMax = Metadata.getColumnMinAndMax(strTableName,key);
            Object min = columnMinAndMax.get(0);
            Object max = columnMinAndMax.get(1);
            boolean lessThanMin = Comparison.compareTo(value,min,columnType)<0;
            boolean greaterThanMax = Comparison.compareTo(value,max,columnType)>0;
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            throw new DBAppException(e.getMessage());
        }
    }

    public boolean isSupported(String dt){
        HashSet<String> supportedDataTypes = new HashSet<String>();
        supportedDataTypes.add("java.lang.Integer");
        supportedDataTypes.add("java.lang.String");
        supportedDataTypes.add("java.lang.Double");
        supportedDataTypes.add("java.util.Date");
        return supportedDataTypes.contains(dt);
    }

    public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException{
        try{
            checkSelectQuery(arrSQLTerms , strarrOperators);
            String tableName = arrSQLTerms[0].get_strTableName();
            Table T = FileManipulation.loadTable(this.tablesFilepath , tableName);
            Hashtable<String , Object> htblColumnNameValues = new Hashtable<String , Object>();
            Hashtable<String , String> htblColumnNameOperators = new Hashtable<String , String>();
            boolean allAnds = true;
            for(int i = 0 ; i < arrSQLTerms.length ; i++){
                htblColumnNameValues.put(arrSQLTerms[i].get_strColumnName() , arrSQLTerms[i].get_objValue());
                htblColumnNameOperators.put(arrSQLTerms[i].get_strColumnName() , arrSQLTerms[i].get_strOperator());
                if(i < arrSQLTerms.length - 1 && !strarrOperators[i].equalsIgnoreCase("and")) allAnds = false;
            }
            Vector<String> octrees = T.getOctrees();
            int n = octrees.size();
            Vector<Octree> octreesThatCanBeUsed = new Vector<Octree>();
            HashSet<String> participatingInOctree = new HashSet<String>();
            for(int i = 0 ; i < n ; i++){
                Octree oct = FileManipulation.loadOctree("src/main/resources/data/indices/"+T.getTableName()+"/" , octrees.get(i)); // check path ya lol
                if(oct.canBeUsed(htblColumnNameValues , htblColumnNameOperators, participatingInOctree)) octreesThatCanBeUsed.add(oct);
            }
            Vector<Tuple> result = new Vector<Tuple>();
            int canBeUsedLength = octreesThatCanBeUsed.size();
            //canBeUsedLength > 0 && allAnds
            if( canBeUsedLength > 0 && allAnds ){
                for(int i = 0 ; i < canBeUsedLength ; i++){
                    Octree currOctree = octreesThatCanBeUsed.get(i);
                    String currOctreeWidth = currOctree.getStrColWidth();
                    String currOctreeLength = currOctree.getStrColLength();
                    String currOctreeHeight = currOctree.getStrColHeight();
                    String operatorWidth = htblColumnNameOperators.get(currOctreeWidth);
                    String operatorLength = htblColumnNameOperators.get(currOctreeLength);
                    String operatorHeight = htblColumnNameOperators.get(currOctreeHeight);
                    if(operatorWidth.equals("=") && operatorLength.equals("=") && operatorHeight.equals("=")){
                        Point p = currOctree.pointToBeSearchedFor(htblColumnNameValues);
                        Vector<Point> resultPoints = currOctree.search(p);
                        if(resultPoints.size()==0){
                            result = new Vector<Tuple>();
                            return result.iterator();
                        }
                        if(i == 0){
                            result = resultPoints.get(0).getPointsTuples();
                        }else{
                            Vector<Tuple> temp = resultPoints.get(0).getPointsTuples();
                            result = ANDSelect(result,temp);
                        }
                    }
                    else{
                        Object objWidth = htblColumnNameValues.get(currOctreeWidth);
                        Object objHeight = htblColumnNameValues.get(currOctreeHeight);
                        Object objLength = htblColumnNameValues.get(currOctreeLength);
                        SQLTerm[] sqlTerms = new SQLTerm[3];
                        SQLTerm widthSQl = new SQLTerm(tableName,currOctreeWidth, operatorWidth, objWidth);
                        SQLTerm lengthSql = new SQLTerm(tableName,currOctreeLength,operatorLength,objLength);
                        SQLTerm heightSql = new SQLTerm(tableName,currOctreeHeight,operatorHeight,objHeight);
                        sqlTerms[0] = widthSQl;
                        sqlTerms[1] = lengthSql;
                        sqlTerms[2] = heightSql;
                        Vector<Tuple> tups = currOctree.searchRange(sqlTerms);
                        if(tups.size()==0){
                            result = new Vector<Tuple>();
                            return result.iterator();
                        }
                        if(i == 0)
                            result = tups;
                        else
                            result = ANDSelect(result , tups);
                    }
                }
                for(int i = 0 ; i < arrSQLTerms.length ; i++){
                    String columnName = arrSQLTerms[i].get_strColumnName();
                    Object columnValue = arrSQLTerms[i].get_objValue();
                    String op = arrSQLTerms[i].get_strOperator();
                    if(participatingInOctree.contains(columnName)) continue;
                    this.filter(result, columnName , columnValue,op);
                }
                return result.iterator();
            }
            else {
                for(int i = 0 ; i < arrSQLTerms.length ; i++){
                    String columnName = arrSQLTerms[i].get_strColumnName();
                    Object value = arrSQLTerms[i].get_objValue();
                    String operator = arrSQLTerms[i].get_strOperator();
                    if(i == 0){
                        result = T.selectDataFromTable(columnName,value,operator);
                    }else{
                        Vector<Tuple> temp = new Vector<Tuple>();
                        temp = T.selectDataFromTable(columnName , value , operator);
                        String op = strarrOperators[i-1];
                        result = op.equalsIgnoreCase("XOR") ? XORSelect(result , temp) : op.equalsIgnoreCase("OR") ? ORSelect(result , temp) : ANDSelect(result , temp);
                    }
                }
            }
            return result.iterator();
        }catch(Exception e){
            throw new DBAppException(e.getMessage());
        }
    }
    public void filter(Vector<Tuple> result, String colName, Object colValue,String op){
        for(int i=0;i<result.size();i++){
            Tuple currTuple = result.get(i);
            Object currValue = currTuple.getTupleAttributes().get(colName);
            if(!checkOperator(op,currValue,colValue)){
                result.remove(i);
                i--;
            }
        }
    }
    public boolean checkOperator(String operator,Object o1, Object o2){
        boolean flag = false;
        switch(operator) {
            case ">": flag = (compareTo(o1 , o2 , null) > 0) ; break;
            case ">=": flag = (compareTo(o1 , o2 , null) >= 0) ; break;
            case "<": flag = (compareTo(o1 , o2 , null) < 0) ; break;
            case "<=": flag = (compareTo(o1 , o2, null) <= 0) ; break;
            case "!=": flag = (compareTo(o1 , o2, null) != 0) ; break;
            case "=": flag = (compareTo(o1 , o2, null) == 0) ; break;
        }
        return flag;
    }

    public Vector<Tuple> XORSelect(Vector<Tuple> vec1 , Vector<Tuple> vec2){
        Vector<Tuple> result = new Vector<Tuple>();
        //check 3ashan sleepy :))
        if(vec1.size()==0) {
            return vec2;
        }else if(vec2.size()==0){
            return vec1;
        }
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
        if(vec1.size()==0) {
            return vec2;
        }else if(vec2.size()==0){
            return vec1;
        }
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
        if(vec1.size()==0 || vec2.size()==0) {
            return result;
        }
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
        if(!(arrSQLTerms.length == strarrOperators.length + 1)) throw new DBAppException("The Query you have entered is wrong");
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
    public Iterator parseSQL( StringBuffer strbufSQL ) throws DBAppException {
        try{
            QueryParserExecutor qpe = new QueryParserExecutor(this , strbufSQL.toString());
            return qpe.queryExecute();
        } catch(Exception e){
            throw new DBAppException(e.getMessage());
        }

    }
}