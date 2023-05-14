// Generated from Query.g4 by ANTLR 4.9.2

package sqlAntlrParser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QueryParser#sql_query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_query(QueryParser.Sql_queryContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#update_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_table(QueryParser.Update_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#column_equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_equals(QueryParser.Column_equalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#insert_into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_into(QueryParser.Insert_intoContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(QueryParser.Column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(QueryParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#delete_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_from(QueryParser.Delete_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#select_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_from(QueryParser.Select_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QueryParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#create_index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_index(QueryParser.Create_indexContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(QueryParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(QueryParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#indexColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnName(QueryParser.IndexColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(QueryParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#otherSelectCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherSelectCondition(QueryParser.OtherSelectConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#columnOperators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnOperators(QueryParser.ColumnOperatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#deleteColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteColumnName(QueryParser.DeleteColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#deleteOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteOperator(QueryParser.DeleteOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#deleteValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteValue(QueryParser.DeleteValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#deleteCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteCondition(QueryParser.DeleteConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#otherDeleteCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherDeleteCondition(QueryParser.OtherDeleteConditionContext ctx);
}