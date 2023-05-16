package parsingHelpers;

import mainClasses.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sqlAntlrParser.QueryLexer;
import sqlAntlrParser.QueryParser;
import sqlterm.SQLTerm;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class QueryParserExecutor {
    private DBApp app;
    private String query;
    private QueryVisitorHelper qvh;
    public QueryParserExecutor(DBApp app , String query){
        this.app = app;
        this.query = query.toUpperCase();
        this.qvh = new QueryVisitorHelper();
    }
    private void visitQuery(){
        QueryLexer q = new QueryLexer(CharStreams.fromString(this.query));
        CommonTokenStream commonTokenStream = new CommonTokenStream(q);
        QueryParser parser = new QueryParser(commonTokenStream);
        qvh.visit(parser.sql_query());
    }

    public Iterator queryExecute() throws Exception {
        visitQuery();
        Iterator it = null;
        switch(qvh.getStatement_Type()){
            case "select": return selectQuery();
            case "create-index": createIndexQuery();break;
            case "delete": deleteQuery(); break;
            case "update": updateQuery(); break;
            case "insert": insertQuery(); break;
            case "create-table": createTableQuery(); break;
            default: throw new DBAppException("This Query isn't supported!");
        }
        return it;
    }
    public Iterator selectQuery() throws Exception {
        SQLTerm[] arrSQLTerms = fixSQLVector(qvh.getSelectConditions());
        String[] strarrOperators = fixStringVector(qvh.getSelectColumnOperators());
        System.out.println(qvh.getSelectConditions());
        Iterator x = app.selectFromTable(arrSQLTerms , strarrOperators);
        return x;
    }
    public String[] fixStringVector(Vector<String> vector){
        int n = vector.size();
        String[] re = new String[n];
        for(int i = 0 ; i < n ; i++){
            re[i] = vector.get(i).toLowerCase();
        }
        return re;
    }
    public SQLTerm[] fixSQLVector(Vector<SQLTerm> vector) throws Exception {
        int n = vector.size();
        SQLTerm[] re = new SQLTerm[n];
        for(int i = 0 ; i < n ; i++){
            SQLTerm sql = vector.get(i);
//            this.fixTableName(sql.get_strTableName())
            sql.set_strTableName(sql.get_strTableName().toLowerCase());
            sql.set_strColumnName(sql.get_strColumnName().toLowerCase());
            sql.set_strOperator(sql.get_strOperator());
            String value = (String) sql.get_objValue();
            Metadata metaData = this.app.getMetaData();
            String columnType = metaData.getColumnType(sql.get_strTableName() , sql.get_strColumnName());
            String valueString = value.replaceAll("'" , "");
            Object valueObject = Column.adjustDataType(valueString , columnType);
            sql.set_objValue(valueObject);
            re[i] = sql;
        }
        return re;
    }
    public void createIndexQuery() throws Exception {
        String tableName = qvh.getTableName();
        Vector<String> vectorcolumns = qvh.getCreateIndexColumns();
        String[] vectorColumnsString = new String[vectorcolumns.size()];
        for(int i = 0 ; i < vectorcolumns.size() ; i++){
            vectorColumnsString[i] = vectorcolumns.get(i).toLowerCase();
        }
        app.createIndex(this.fixTableName(tableName) , vectorColumnsString);
    }
    public void deleteQuery() throws Exception {
        Hashtable<String , Object> htbl = getHashTable(qvh.getUpdateDeleteColumnNames() , qvh.getUpdateDeleteObjectValues());
        String tableName = qvh.getTableName();
        app.deleteFromTable(this.fixTableName(tableName) , htbl);
    }
    public void updateQuery() throws Exception {
        String tableName = this.fixTableName(qvh.getTableName());
        Hashtable<String , Object> htbl = getHashTable(qvh.getUpdateColumnToSetNames() , qvh.getUpdateColumToSetValues());
        String tempClusteringKey = qvh.getUpdateDeleteObjectValues().get(0);
        Metadata metaData = this.app.getMetaData();
        String columnType = metaData.getColumnType(tableName , qvh.getUpdateDeleteColumnNames().get(0));
        String valueString = tempClusteringKey.replaceAll("'" , "");
//        Object valueObject = Column.adjustDataType(valueString , columnType);
        app.updateTable(tableName , valueString , htbl);
    }
    public void insertQuery() throws Exception {
        Hashtable<String , Object> htbl = getHashTable(qvh.getInsertColumns() , qvh.getInsertValues());
        String tableName = qvh.getTableName();
        System.out.println(htbl);
        //this.fixTableName(tableName)
        app.insertIntoTable(tableName.toLowerCase() , htbl);
    }
    public void createTableQuery() throws DBAppException {
        Vector<String> columnNames = (qvh.getCreateColumnNames());
        Vector<String> columnTypes = (qvh.getCreateColumnTypes());
        Vector<String> clusteringKeyVec = qvh.getCreateTableClusteringKey();
        if(clusteringKeyVec.size() != 1) throw new DBAppException("The clustering key in the Query is wrongly specified");
        String clusteringKey = clusteringKeyVec.get(0).toLowerCase();
        String tableName = this.fixTableName(qvh.getTableName());
        Hashtable<String , String> htblColNameType = new Hashtable<String , String>();
        Hashtable<String , String> htblColNameMin = new Hashtable<String , String>();
        Hashtable<String , String> htblColNameMax = new Hashtable<String , String>();
        int n = columnTypes.size();
        for (int i = 0 ; i < n ; i++){
            String type = columnTypes.get(i).toLowerCase();
            String name = columnNames.get(i).toLowerCase();
            String min = this.getMinValueOfColumn(type);
            String max = this.getMaxValueOfColumn(type);
            String javaType = this.getType(type);
            htblColNameType.put(name , javaType);
            htblColNameMax.put(name , max);
            htblColNameMin.put(name , min);
        }
        app.createTable(tableName,clusteringKey,htblColNameType,htblColNameMin,htblColNameMax);
        System.out.println("Table Created!!!!!!!!!!!!!");
    }
    public Hashtable<String , Object> getHashTable(Vector<String> keys , Vector<String> values) throws Exception {
        String tableName = this.fixTableName(qvh.getTableName());
        Hashtable<String , Object> hashTable = new Hashtable<String , Object>();
        for(int i = 0 ; i < keys.size() ; i++){
            String keyTemp = keys.get(i).toLowerCase();
            Metadata metaData = this.app.getMetaData();
            String columnType = metaData.getColumnType(tableName , keyTemp);
//            System.out.println("key: "+keyTemp+"type: "+columnType);
            String valueString = values.get(i).replaceAll("'" , "");
            Object valueObject = Column.adjustDataType(valueString , columnType);
            hashTable.put(keyTemp , valueObject);
        }
        return hashTable;
    }

    public String fixTableName(String s){
        return s.toUpperCase().charAt(0) + s.substring(1).toLowerCase();
    }

    public String getMinValueOfColumn(String type) throws DBAppException {
        switch (type){
            case "int":
            case "decimal": return "0";
            case "varchar": return "A";
            case "date": return "1900-1-1";
            default: throw new DBAppException("Type specified is not good");
        }
    }
    public String getMaxValueOfColumn(String type) throws DBAppException {
        switch (type){
            case "int":
            case "decimal": return "10000";
            case "varchar": return "ZZZZZZZZZZZ";
            case "date": return "2099-12-31";
            default: throw new DBAppException("Type specified is not good");
        }
    }
    public String getType(String type) throws DBAppException {
        switch (type){
            case "int": return "java.lang.Integer";
            case "decimal": return "java.lang.Double";
            case "varchar": return "java.lang.String";
            case "date": return "java.util.Date";
            default: throw new DBAppException("Type specified is not good");
        }
    }
    public static void main(String[] args) throws Exception {
        QueryParserExecutor qf = new QueryParserExecutor(null , "Delete From Students where name = 'karim'");
        qf.queryExecute();
    }


}
