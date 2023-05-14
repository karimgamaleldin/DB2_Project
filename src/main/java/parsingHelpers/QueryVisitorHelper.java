package parsingHelpers;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sqlAntlrParser.QueryBaseVisitor;
import sqlAntlrParser.QueryLexer;
import sqlAntlrParser.QueryParser;
import sqlterm.SQLTerm;

import java.util.Vector;


public class QueryVisitorHelper extends QueryBaseVisitor<Void> {
    private String statement_Type = "";
    private String tableName = "";
    private String column;
    private String operator;
    private String value;
    //select
    private Vector<SQLTerm> selectConditions = new Vector<SQLTerm>();
    private Vector<String> selectColumnOperators = new Vector<String>();
    //create index
    private Vector<String> createIndexColumns = new Vector<String>();

    //Select From

    public Void visitSelect_from (QueryParser.Select_fromContext ctx){
        this.statement_Type = "select";
        return visitChildren(ctx);
    }
    public Void visitTableName(QueryParser.TableNameContext ctx) {
        this.tableName= ctx.getText();
        return visitChildren(ctx);
    }
    public Void visitValue(QueryParser.ValueContext ctx) {
        this.value = ctx.getText();

        selectConditions.add(new SQLTerm(this.tableName,this.column,this.operator , this.value));
        return visitChildren(ctx);
    }
    public Void visitCondition(QueryParser.ConditionContext ctx) {
        return visitChildren(ctx);
    }
    public Void visitColumnName(QueryParser.ColumnNameContext ctx) {
        this.column = ctx.getText();
        return visitChildren(ctx);
    }
    public Void visitColumnOperators(QueryParser.ColumnOperatorsContext ctx) {
        this.selectColumnOperators.add(ctx.getText());
        return visitChildren(ctx);
    }


    public Void visitOperator(QueryParser.OperatorContext ctx) {
        this.operator = ctx.getText();
        return visitChildren(ctx);
    }

    // Create Index
    public Void visitCreate_index(QueryParser.Create_indexContext ctx) {
        this.statement_Type = "createindex";
        return visitChildren(ctx);
    }
    public Void visitIndexColumnName(QueryParser.IndexColumnNameContext ctx) {
        this.createIndexColumns.add(ctx.getText());
        return visitChildren(ctx);
    }
    public String getStatement_Type() {
        return statement_Type;
    }

    public void setStatement_Type(String statement_Type) {
        this.statement_Type = statement_Type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Vector<SQLTerm> getSelectConditions() {
        return selectConditions;
    }

    public void setSelectConditions(Vector<SQLTerm> selectConditions) {
        this.selectConditions = selectConditions;
    }

    public Vector<String> getSelectColumnOperators() {
        return selectColumnOperators;
    }

    public void setSelectColumnOperators(Vector<String> selectColumnOperators) {
        this.selectColumnOperators = selectColumnOperators;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Vector<String> getCreateIndexColumns() {
        return createIndexColumns;
    }

    public void setCreateIndexColumns(Vector<String> createIndexColumns) {
        this.createIndexColumns = createIndexColumns;
    }

    public static void main(String[] args){
        String in = "Create Index on Student(age,gpa,name)";
        in = in.toUpperCase();
        QueryLexer q = new QueryLexer(CharStreams.fromString(in));
        CommonTokenStream commonTokenStream = new CommonTokenStream(q);
        QueryParser parser = new QueryParser(commonTokenStream);
//        ParseTree tree = parser.sql_query();
//        System.out.println(tree.toStringTree(parser));
        QueryVisitorHelper qv = new QueryVisitorHelper();
        qv.visit(parser.sql_query());
        System.out.println(qv.tableName);
        System.out.println(qv.statement_Type);
        System.out.print(qv.createIndexColumns);


    }
}
