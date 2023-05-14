package parsingHelpers;

import mainClasses.DBApp;
import mainClasses.DBAppException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sqlAntlrParser.QueryLexer;
import sqlAntlrParser.QueryParser;

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

    public void queryExecute() throws DBAppException {
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
    public void createIndexQuery(){
        System.out.println(qvh.getCreateIndexColumns());
        System.out.println(qvh.getTableName());
        System.out.println("createIndex!!!!!!!!!!!!!!!!!!!!!");
    }
    public void deleteQuery(){
        System.out.println(qvh.getUpdateDeleteColumnNames());
        System.out.println(qvh.getTableName());
        System.out.println(qvh.getUpdateDeleteObjectValues());
        System.out.println("Delete!!!!!!!!!!!!!!!!!!!!!");
    }
    public void updateQuery(){
        System.out.println(qvh.getUpdateDeleteColumnNames());
        System.out.println(qvh.getUpdateDeleteObjectValues());
        System.out.println(qvh.getUpdateColumnToSetNames());
        System.out.println(qvh.getUpdateColumToSetValues());
    }
    public void insertQuery(){
        System.out.println(qvh.getStatement_Type());
        System.out.println(qvh.getTableName());
        System.out.println(qvh.getInsertColumns());
        System.out.println(qvh.getInsertValues());
    }
    public void createTableQuery(){
        System.out.println("column Names: " + qvh.getCreateColumnNames());
        System.out.println("column types: " +qvh.getCreateColumnTypes());
        System.out.println("clusteringKey: " +qvh.getCreateTableClusteringKey()); // if more than 1 throw error
        System.out.println("create Tableeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }
    public Hashtable<String , Object> getHashTable(Vector<String> keys , Vector<String> values){
        String tableName = qvh.getTableName();
        for(int i = 0 ; i < keys.size() ; i++){
            String keyTemp = keys.get(i);
//            String columnType = this.app.
//            String valueString = keys.get(i);
        }
        return null;
    }
    public static void main(String[] args) throws DBAppException {
        QueryParserExecutor qf = new QueryParserExecutor(null , "Delete From Students  where name = 'karim'");
        qf.queryExecute();
    }

}
