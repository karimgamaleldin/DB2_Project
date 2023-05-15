package parsingHelpers;

import mainClasses.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sqlAntlrParser.QueryLexer;
import sqlAntlrParser.QueryParser;

import java.io.File;
import java.util.Hashtable;
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

    public void queryExecute() throws Exception {
        visitQuery();
        switch(qvh.getStatement_Type()){
            case "select": selectQuery(); break;
            case "create-index": createIndexQuery();break;
            case "delete": deleteQuery(); break;
            case "update": updateQuery(); break;
            case "insert": insertQuery(); break;
            case "create-table": createTableQuery(); break;
            default: throw new DBAppException("This Query isn't supported!");
        }
    }
    public void selectQuery(){
        System.out.println(qvh.getSelectConditions());
        System.out.println((qvh.getSelectColumnOperators()));
        System.out.println("Select!!!!!!!!!!!!!!1");
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
        app.insertIntoTable(this.fixTableName(tableName) , htbl);
    }
    public void createTableQuery(){
        System.out.println("column Names: " + qvh.getCreateColumnNames());
        System.out.println("column types: " +qvh.getCreateColumnTypes());
        System.out.println("clusteringKey: " +qvh.getCreateTableClusteringKey()); // if more than 1 throw error
        System.out.println("create Tableeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }
    public Hashtable<String , Object> getHashTable(Vector<String> keys , Vector<String> values) throws Exception {
        String tableName = this.fixTableName(qvh.getTableName());
        Hashtable<String , Object> hashTable = new Hashtable<String , Object>();
        for(int i = 0 ; i < keys.size() ; i++){
            String keyTemp = keys.get(i).toLowerCase();
            Metadata metaData = this.app.getMetaData();
            String columnType = metaData.getColumnType(tableName , keyTemp);
            String valueString = values.get(i).replaceAll("'" , "");
            Object valueObject = Column.adjustDataType(valueString , columnType);
            hashTable.put(keyTemp , valueObject);
        }
        return hashTable;
    }

    public String fixTableName(String s){
        return s.toUpperCase().charAt(0) + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws Exception {
        QueryParserExecutor qf = new QueryParserExecutor(null , "Delete From Students  where name = 'karim'");
        qf.queryExecute();
    }

}
