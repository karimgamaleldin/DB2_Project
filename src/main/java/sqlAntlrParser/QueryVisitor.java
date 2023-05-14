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
	 * Visit a parse tree produced by {@link QueryParser#insert_into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_into(QueryParser.Insert_intoContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(QueryParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#insertValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertValue(QueryParser.InsertValueContext ctx);
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
	 * Visit a parse tree produced by {@link QueryParser#updateDeleteColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateDeleteColumnName(QueryParser.UpdateDeleteColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#updateDeleteValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateDeleteValue(QueryParser.UpdateDeleteValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#updateDeleteCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateDeleteCondition(QueryParser.UpdateDeleteConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#otherDeleteCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherDeleteCondition(QueryParser.OtherDeleteConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#updateColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateColumnName(QueryParser.UpdateColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#updateValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateValue(QueryParser.UpdateValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#updateColumnToSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateColumnToSet(QueryParser.UpdateColumnToSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#otherUpdateColumnToSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherUpdateColumnToSet(QueryParser.OtherUpdateColumnToSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#insertColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertColumnName(QueryParser.InsertColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#additionalColumnInsert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionalColumnInsert(QueryParser.AdditionalColumnInsertContext ctx);
}