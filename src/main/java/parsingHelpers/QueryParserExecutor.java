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
    public void selectQuery() throws Exception {
        SQLTerm[] arrSQLTerms = fixSQLVector(qvh.getSelectConditions());
        String[] strarrOperators = fixStringVector(qvh.getSelectColumnOperators());
        System.out.println(qvh.getSelectConditions());
        Iterator x = app.selectFromTable(arrSQLTerms , strarrOperators);
        System.out.println(x.next());
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
            sql.set_strTableName(this.fixTableName(sql.get_strTableName()));
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
        app.insertIntoTable(this.fixTableName(tableName) , htbl);
    }
    public void createTableQuery() throws DBAppException {
        String[] columnNames = this.fixStringVector(qvh.getCreateColumnNames());
        String[] columnTypes = this.fixStringVector(qvh.getCreateColumnTypes());
        Vector<String> clusteringKey = qvh.getCreateTableClusteringKey();
        if(clusteringKey.size() != 0) throw new DBAppException("The clustering key in the Query is wrongly specified");

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
        QueryParserExecutor qf = new QueryParserExecutor(null , "Delete From Students where name = 'karim'");
        qf.queryExecute();
    }

}
