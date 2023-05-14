// Generated from Query.g4 by ANTLR 4.9.2

package sqlAntlrParser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, TABLE=2, CREATE=3, UPDATE=4, INSERT=5, DELETE=6, SET=7, SELECT=8, 
		FROM=9, WHERE=10, AND=11, OR=12, XOR=13, INDEX=14, ON=15, INTO=16, VALUES=17, 
		IDENTIFIER=18, INTEGER=19, DECIMAL=20, STRING=21, GREATERTHAN=22, GREATERTHANOREQUAL=23, 
		LESSTHAN=24, LESSTHANOREQUAL=25, NOTEQUAL=26, EQUAL=27, LPAREN=28, RPAREN=29, 
		COMMA=30, WS=31;
	public static final int
		RULE_sql_query = 0, RULE_update_table = 1, RULE_insert_into = 2, RULE_column_name = 3, 
		RULE_value = 4, RULE_delete_from = 5, RULE_select_from = 6, RULE_condition = 7, 
		RULE_create_index = 8, RULE_tableName = 9, RULE_columnName = 10, RULE_indexColumnName = 11, 
		RULE_operator = 12, RULE_otherSelectCondition = 13, RULE_columnOperators = 14, 
		RULE_updateDeleteColumnName = 15, RULE_updateDeleteValue = 16, RULE_updateDeleteCondition = 17, 
		RULE_otherDeleteCondition = 18, RULE_updateColumnName = 19, RULE_updateValue = 20, 
		RULE_updateColumnToSet = 21, RULE_otherUpdateColumnToSet = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_query", "update_table", "insert_into", "column_name", "value", "delete_from", 
			"select_from", "condition", "create_index", "tableName", "columnName", 
			"indexColumnName", "operator", "otherSelectCondition", "columnOperators", 
			"updateDeleteColumnName", "updateDeleteValue", "updateDeleteCondition", 
			"otherDeleteCondition", "updateColumnName", "updateValue", "updateColumnToSet", 
			"otherUpdateColumnToSet"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", "'TABLE'", "'CREATE'", "'UPDATE'", "'INSERT'", "'DELETE'", 
			"'SET'", "'SELECT'", "'FROM'", "'WHERE'", "'AND'", "'OR'", "'XOR'", "'INDEX'", 
			"'ON'", "'INTO'", "'VALUES'", null, null, null, null, "'>'", "'>='", 
			"'<'", "'<='", "'!='", "'='", "'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "TABLE", "CREATE", "UPDATE", "INSERT", "DELETE", "SET", "SELECT", 
			"FROM", "WHERE", "AND", "OR", "XOR", "INDEX", "ON", "INTO", "VALUES", 
			"IDENTIFIER", "INTEGER", "DECIMAL", "STRING", "GREATERTHAN", "GREATERTHANOREQUAL", 
			"LESSTHAN", "LESSTHANOREQUAL", "NOTEQUAL", "EQUAL", "LPAREN", "RPAREN", 
			"COMMA", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Sql_queryContext extends ParserRuleContext {
		public Update_tableContext update_table() {
			return getRuleContext(Update_tableContext.class,0);
		}
		public Insert_intoContext insert_into() {
			return getRuleContext(Insert_intoContext.class,0);
		}
		public Delete_fromContext delete_from() {
			return getRuleContext(Delete_fromContext.class,0);
		}
		public Select_fromContext select_from() {
			return getRuleContext(Select_fromContext.class,0);
		}
		public Create_indexContext create_index() {
			return getRuleContext(Create_indexContext.class,0);
		}
		public Sql_queryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_query; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitSql_query(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_queryContext sql_query() throws RecognitionException {
		Sql_queryContext _localctx = new Sql_queryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sql_query);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UPDATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				update_table();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				insert_into();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				delete_from();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				select_from();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 5);
				{
				setState(50);
				create_index();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_tableContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(QueryParser.UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SET() { return getToken(QueryParser.SET, 0); }
		public UpdateColumnToSetContext updateColumnToSet() {
			return getRuleContext(UpdateColumnToSetContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(QueryParser.WHERE, 0); }
		public UpdateDeleteConditionContext updateDeleteCondition() {
			return getRuleContext(UpdateDeleteConditionContext.class,0);
		}
		public List<OtherUpdateColumnToSetContext> otherUpdateColumnToSet() {
			return getRuleContexts(OtherUpdateColumnToSetContext.class);
		}
		public OtherUpdateColumnToSetContext otherUpdateColumnToSet(int i) {
			return getRuleContext(OtherUpdateColumnToSetContext.class,i);
		}
		public Update_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdate_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_tableContext update_table() throws RecognitionException {
		Update_tableContext _localctx = new Update_tableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_update_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(UPDATE);
			setState(54);
			tableName();
			setState(55);
			match(SET);
			setState(56);
			updateColumnToSet();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(57);
				otherUpdateColumnToSet();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(WHERE);
			setState(64);
			updateDeleteCondition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_intoContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(QueryParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(QueryParser.INTO, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(QueryParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(QueryParser.LPAREN, i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(QueryParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(QueryParser.RPAREN, i);
		}
		public TerminalNode VALUES() { return getToken(QueryParser.VALUES, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QueryParser.COMMA, i);
		}
		public Insert_intoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_into; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitInsert_into(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_intoContext insert_into() throws RecognitionException {
		Insert_intoContext _localctx = new Insert_intoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_insert_into);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(INSERT);
			setState(67);
			match(INTO);
			setState(68);
			match(IDENTIFIER);
			setState(69);
			match(LPAREN);
			setState(70);
			column_name();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(71);
				match(COMMA);
				setState(72);
				column_name();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(RPAREN);
			setState(79);
			match(VALUES);
			setState(80);
			match(LPAREN);
			setState(81);
			value();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(82);
				match(COMMA);
				setState(83);
				value();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(QueryParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_fromContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(QueryParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(QueryParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(QueryParser.WHERE, 0); }
		public UpdateDeleteConditionContext updateDeleteCondition() {
			return getRuleContext(UpdateDeleteConditionContext.class,0);
		}
		public List<OtherDeleteConditionContext> otherDeleteCondition() {
			return getRuleContexts(OtherDeleteConditionContext.class);
		}
		public OtherDeleteConditionContext otherDeleteCondition(int i) {
			return getRuleContext(OtherDeleteConditionContext.class,i);
		}
		public Delete_fromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_from; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitDelete_from(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_fromContext delete_from() throws RecognitionException {
		Delete_fromContext _localctx = new Delete_fromContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_delete_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(DELETE);
			setState(96);
			match(FROM);
			setState(97);
			tableName();
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(98);
				match(WHERE);
				setState(99);
				updateDeleteCondition();
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(100);
					otherDeleteCondition();
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_fromContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(QueryParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(QueryParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(QueryParser.WHERE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<OtherSelectConditionContext> otherSelectCondition() {
			return getRuleContexts(OtherSelectConditionContext.class);
		}
		public OtherSelectConditionContext otherSelectCondition(int i) {
			return getRuleContext(OtherSelectConditionContext.class,i);
		}
		public Select_fromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_from; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitSelect_from(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_fromContext select_from() throws RecognitionException {
		Select_fromContext _localctx = new Select_fromContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_select_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(SELECT);
			setState(109);
			match(T__0);
			setState(110);
			match(FROM);
			setState(111);
			tableName();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(112);
				match(WHERE);
				setState(113);
				condition();
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
					{
					{
					setState(114);
					otherSelectCondition();
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			columnName();
			setState(123);
			operator();
			setState(124);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_indexContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(QueryParser.CREATE, 0); }
		public TerminalNode INDEX() { return getToken(QueryParser.INDEX, 0); }
		public TerminalNode ON() { return getToken(QueryParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public List<IndexColumnNameContext> indexColumnName() {
			return getRuleContexts(IndexColumnNameContext.class);
		}
		public IndexColumnNameContext indexColumnName(int i) {
			return getRuleContext(IndexColumnNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QueryParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public Create_indexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_index; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitCreate_index(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_indexContext create_index() throws RecognitionException {
		Create_indexContext _localctx = new Create_indexContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_create_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(CREATE);
			setState(127);
			match(INDEX);
			setState(128);
			match(ON);
			setState(129);
			tableName();
			setState(130);
			match(LPAREN);
			setState(131);
			indexColumnName();
			setState(132);
			match(COMMA);
			setState(133);
			indexColumnName();
			setState(134);
			match(COMMA);
			setState(135);
			indexColumnName();
			setState(136);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public IndexColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitIndexColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexColumnNameContext indexColumnName() throws RecognitionException {
		IndexColumnNameContext _localctx = new IndexColumnNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_indexColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode GREATERTHAN() { return getToken(QueryParser.GREATERTHAN, 0); }
		public TerminalNode GREATERTHANOREQUAL() { return getToken(QueryParser.GREATERTHANOREQUAL, 0); }
		public TerminalNode LESSTHAN() { return getToken(QueryParser.LESSTHAN, 0); }
		public TerminalNode LESSTHANOREQUAL() { return getToken(QueryParser.LESSTHANOREQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(QueryParser.NOTEQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATERTHAN) | (1L << GREATERTHANOREQUAL) | (1L << LESSTHAN) | (1L << LESSTHANOREQUAL) | (1L << NOTEQUAL) | (1L << EQUAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherSelectConditionContext extends ParserRuleContext {
		public ColumnOperatorsContext columnOperators() {
			return getRuleContext(ColumnOperatorsContext.class,0);
		}
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public OtherSelectConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherSelectCondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitOtherSelectCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherSelectConditionContext otherSelectCondition() throws RecognitionException {
		OtherSelectConditionContext _localctx = new OtherSelectConditionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_otherSelectCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			columnOperators();
			setState(147);
			columnName();
			setState(148);
			operator();
			setState(149);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnOperatorsContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(QueryParser.AND, 0); }
		public TerminalNode XOR() { return getToken(QueryParser.XOR, 0); }
		public TerminalNode OR() { return getToken(QueryParser.OR, 0); }
		public ColumnOperatorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnOperators; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitColumnOperators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnOperatorsContext columnOperators() throws RecognitionException {
		ColumnOperatorsContext _localctx = new ColumnOperatorsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_columnOperators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateDeleteColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public UpdateDeleteColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateDeleteColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdateDeleteColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateDeleteColumnNameContext updateDeleteColumnName() throws RecognitionException {
		UpdateDeleteColumnNameContext _localctx = new UpdateDeleteColumnNameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_updateDeleteColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateDeleteValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(QueryParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public UpdateDeleteValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateDeleteValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdateDeleteValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateDeleteValueContext updateDeleteValue() throws RecognitionException {
		UpdateDeleteValueContext _localctx = new UpdateDeleteValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_updateDeleteValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateDeleteConditionContext extends ParserRuleContext {
		public UpdateDeleteColumnNameContext updateDeleteColumnName() {
			return getRuleContext(UpdateDeleteColumnNameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public UpdateDeleteValueContext updateDeleteValue() {
			return getRuleContext(UpdateDeleteValueContext.class,0);
		}
		public UpdateDeleteConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateDeleteCondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdateDeleteCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateDeleteConditionContext updateDeleteCondition() throws RecognitionException {
		UpdateDeleteConditionContext _localctx = new UpdateDeleteConditionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_updateDeleteCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			updateDeleteColumnName();
			setState(158);
			match(EQUAL);
			setState(159);
			updateDeleteValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherDeleteConditionContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(QueryParser.COMMA, 0); }
		public UpdateDeleteColumnNameContext updateDeleteColumnName() {
			return getRuleContext(UpdateDeleteColumnNameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public UpdateDeleteValueContext updateDeleteValue() {
			return getRuleContext(UpdateDeleteValueContext.class,0);
		}
		public OtherDeleteConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherDeleteCondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitOtherDeleteCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherDeleteConditionContext otherDeleteCondition() throws RecognitionException {
		OtherDeleteConditionContext _localctx = new OtherDeleteConditionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_otherDeleteCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(COMMA);
			setState(162);
			updateDeleteColumnName();
			setState(163);
			match(EQUAL);
			setState(164);
			updateDeleteValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public UpdateColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdateColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateColumnNameContext updateColumnName() throws RecognitionException {
		UpdateColumnNameContext _localctx = new UpdateColumnNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_updateColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(QueryParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public UpdateValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdateValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateValueContext updateValue() throws RecognitionException {
		UpdateValueContext _localctx = new UpdateValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_updateValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateColumnToSetContext extends ParserRuleContext {
		public UpdateColumnNameContext updateColumnName() {
			return getRuleContext(UpdateColumnNameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public UpdateValueContext updateValue() {
			return getRuleContext(UpdateValueContext.class,0);
		}
		public UpdateColumnToSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateColumnToSet; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitUpdateColumnToSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateColumnToSetContext updateColumnToSet() throws RecognitionException {
		UpdateColumnToSetContext _localctx = new UpdateColumnToSetContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_updateColumnToSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			updateColumnName();
			setState(171);
			match(EQUAL);
			setState(172);
			updateValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherUpdateColumnToSetContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(QueryParser.COMMA, 0); }
		public UpdateColumnNameContext updateColumnName() {
			return getRuleContext(UpdateColumnNameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public UpdateValueContext updateValue() {
			return getRuleContext(UpdateValueContext.class,0);
		}
		public OtherUpdateColumnToSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherUpdateColumnToSet; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitOtherUpdateColumnToSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherUpdateColumnToSetContext otherUpdateColumnToSet() throws RecognitionException {
		OtherUpdateColumnToSetContext _localctx = new OtherUpdateColumnToSetContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_otherUpdateColumnToSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(COMMA);
			setState(175);
			updateColumnName();
			setState(176);
			match(EQUAL);
			setState(177);
			updateValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00b6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\5\2\66\n\2\3\3\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\7\7h\n\7\f\7\16\7k\13\7\5\7m\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\bv\n\b\f\b\16\by\13\b\5\b{\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\2\2\31\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\2\5\3\2\25\27\3\2\30\35\3\2\r\17\2\u00a9\2\65\3\2\2"+
		"\2\4\67\3\2\2\2\6D\3\2\2\2\b]\3\2\2\2\n_\3\2\2\2\fa\3\2\2\2\16n\3\2\2"+
		"\2\20|\3\2\2\2\22\u0080\3\2\2\2\24\u008c\3\2\2\2\26\u008e\3\2\2\2\30\u0090"+
		"\3\2\2\2\32\u0092\3\2\2\2\34\u0094\3\2\2\2\36\u0099\3\2\2\2 \u009b\3\2"+
		"\2\2\"\u009d\3\2\2\2$\u009f\3\2\2\2&\u00a3\3\2\2\2(\u00a8\3\2\2\2*\u00aa"+
		"\3\2\2\2,\u00ac\3\2\2\2.\u00b0\3\2\2\2\60\66\5\4\3\2\61\66\5\6\4\2\62"+
		"\66\5\f\7\2\63\66\5\16\b\2\64\66\5\22\n\2\65\60\3\2\2\2\65\61\3\2\2\2"+
		"\65\62\3\2\2\2\65\63\3\2\2\2\65\64\3\2\2\2\66\3\3\2\2\2\678\7\6\2\289"+
		"\5\24\13\29:\7\t\2\2:>\5,\27\2;=\5.\30\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2"+
		">?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7\f\2\2BC\5$\23\2C\5\3\2\2\2DE\7\7\2"+
		"\2EF\7\22\2\2FG\7\24\2\2GH\7\36\2\2HM\5\b\5\2IJ\7 \2\2JL\5\b\5\2KI\3\2"+
		"\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\7\37\2\2QR\7"+
		"\23\2\2RS\7\36\2\2SX\5\n\6\2TU\7 \2\2UW\5\n\6\2VT\3\2\2\2WZ\3\2\2\2XV"+
		"\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZX\3\2\2\2[\\\7\37\2\2\\\7\3\2\2\2]^\7\24"+
		"\2\2^\t\3\2\2\2_`\t\2\2\2`\13\3\2\2\2ab\7\b\2\2bc\7\13\2\2cl\5\24\13\2"+
		"de\7\f\2\2ei\5$\23\2fh\5&\24\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2"+
		"jm\3\2\2\2ki\3\2\2\2ld\3\2\2\2lm\3\2\2\2m\r\3\2\2\2no\7\n\2\2op\7\3\2"+
		"\2pq\7\13\2\2qz\5\24\13\2rs\7\f\2\2sw\5\20\t\2tv\5\34\17\2ut\3\2\2\2v"+
		"y\3\2\2\2wu\3\2\2\2wx\3\2\2\2x{\3\2\2\2yw\3\2\2\2zr\3\2\2\2z{\3\2\2\2"+
		"{\17\3\2\2\2|}\5\26\f\2}~\5\32\16\2~\177\5\n\6\2\177\21\3\2\2\2\u0080"+
		"\u0081\7\5\2\2\u0081\u0082\7\20\2\2\u0082\u0083\7\21\2\2\u0083\u0084\5"+
		"\24\13\2\u0084\u0085\7\36\2\2\u0085\u0086\5\30\r\2\u0086\u0087\7 \2\2"+
		"\u0087\u0088\5\30\r\2\u0088\u0089\7 \2\2\u0089\u008a\5\30\r\2\u008a\u008b"+
		"\7\37\2\2\u008b\23\3\2\2\2\u008c\u008d\7\24\2\2\u008d\25\3\2\2\2\u008e"+
		"\u008f\7\24\2\2\u008f\27\3\2\2\2\u0090\u0091\7\24\2\2\u0091\31\3\2\2\2"+
		"\u0092\u0093\t\3\2\2\u0093\33\3\2\2\2\u0094\u0095\5\36\20\2\u0095\u0096"+
		"\5\26\f\2\u0096\u0097\5\32\16\2\u0097\u0098\5\n\6\2\u0098\35\3\2\2\2\u0099"+
		"\u009a\t\4\2\2\u009a\37\3\2\2\2\u009b\u009c\7\24\2\2\u009c!\3\2\2\2\u009d"+
		"\u009e\t\2\2\2\u009e#\3\2\2\2\u009f\u00a0\5 \21\2\u00a0\u00a1\7\35\2\2"+
		"\u00a1\u00a2\5\"\22\2\u00a2%\3\2\2\2\u00a3\u00a4\7 \2\2\u00a4\u00a5\5"+
		" \21\2\u00a5\u00a6\7\35\2\2\u00a6\u00a7\5\"\22\2\u00a7\'\3\2\2\2\u00a8"+
		"\u00a9\7\24\2\2\u00a9)\3\2\2\2\u00aa\u00ab\t\2\2\2\u00ab+\3\2\2\2\u00ac"+
		"\u00ad\5(\25\2\u00ad\u00ae\7\35\2\2\u00ae\u00af\5*\26\2\u00af-\3\2\2\2"+
		"\u00b0\u00b1\7 \2\2\u00b1\u00b2\5(\25\2\u00b2\u00b3\7\35\2\2\u00b3\u00b4"+
		"\5*\26\2\u00b4/\3\2\2\2\n\65>MXilwz";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}