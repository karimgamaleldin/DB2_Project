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
    //delete & update
    private Vector<String> updateDeleteColumnNames = new Vector<String>();
    private Vector<String> updateDeleteObjectValues = new Vector<String>();

    //update :
    private Vector<String> updateColumnToSetNames = new Vector<String>();
    private Vector<String> updateColumToSetValues = new Vector<String>();
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

    //delete
    public Void visitDelete_from(QueryParser.Delete_fromContext ctx) {
        this.statement_Type = "delete";
        return visitChildren(ctx);
    }

    public Void visitUpdateDeleteColumnName(QueryParser.UpdateDeleteColumnNameContext ctx) {
        this.updateDeleteColumnNames.add(ctx.getText());
        return visitChildren(ctx);
    }
    public Void visitUpdateDeleteValue(QueryParser.UpdateDeleteValueContext ctx) {
        this.updateDeleteObjectValues.add(ctx.getText());
        return visitChildren(ctx);
    }

    //update:

    public Void visitUpdate_table(QueryParser.Update_tableContext ctx) {
        this.statement_Type = "update";
        return visitChildren(ctx);
    }

    public Void visitUpdateColumnName(QueryParser.UpdateColumnNameContext ctx) {
        this.updateColumnToSetNames.add(ctx.getText());
        return visitChildren(ctx);
    }
    public Void visitUpdateValue(QueryParser.UpdateValueContext ctx) {
        this.updateColumToSetValues.add(ctx.getText());
        return visitChildren(ctx);
    }

        //getters and setters

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

    public Vector<String> getUpdateDeleteColumnNames() {
        return updateDeleteColumnNames;
    }

    public void setUpdateDeleteColumnNames(Vector<String> updateDeleteColumnNames) {
        this.updateDeleteColumnNames = updateDeleteColumnNames;
    }

    public Vector<String> getUpdateDeleteObjectValues() {
        return updateDeleteObjectValues;
    }

    public void setUpdateDeleteObjectValues(Vector<String> updateDeleteObjectValues) {
        this.updateDeleteObjectValues = updateDeleteObjectValues;
    }


    public Vector<String> getUpdateColumnToSetNames() {
        return updateColumnToSetNames;
    }

    public void setUpdateColumnToSetNames(Vector<String> updateColumnToSetNames) {
        this.updateColumnToSetNames = updateColumnToSetNames;
    }

    public Vector<String> getUpdateColumToSetValues() {
        return updateColumToSetValues;
    }

    public void setUpdateColumToSetValues(Vector<String> updateColumToSetValues) {
        this.updateColumToSetValues = updateColumToSetValues;
    }

    public static void main(String[] args){
        String in = "Update Students set name = 'Karim' , gpa = '0.7' where age = 10";
        in = in.toUpperCase();
        QueryLexer q = new QueryLexer(CharStreams.fromString(in));
        CommonTokenStream commonTokenStream = new CommonTokenStream(q);
        QueryParser parser = new QueryParser(commonTokenStream);
//        ParseTree tree = parser.sql_query();
//        System.out.println(tree.toStringTree(parser));
        QueryVisitorHelper qv = new QueryVisitorHelper();
        qv.visit(parser.sql_query());
        System.out.println(qv.statement_Type);
        System.out.println(qv.tableName);
        System.out.println(qv.getUpdateDeleteColumnNames());
        System.out.println(qv.getUpdateDeleteObjectValues());
        System.out.println(qv.getUpdateColumnToSetNames());
        System.out.println(qv.getUpdateColumToSetValues());



    }
}
