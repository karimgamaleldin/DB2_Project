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
		RULE_sql_query = 0, RULE_update_table = 1, RULE_column_equals = 2, RULE_insert_into = 3, 
		RULE_column_name = 4, RULE_value = 5, RULE_delete_from = 6, RULE_select_from = 7, 
		RULE_condition = 8, RULE_create_index = 9, RULE_tableName = 10, RULE_columnName = 11, 
		RULE_indexColumnName = 12, RULE_operator = 13, RULE_otherSelectCondition = 14, 
		RULE_columnOperators = 15, RULE_deleteColumnName = 16, RULE_deleteOperator = 17, 
		RULE_deleteValue = 18, RULE_deleteCondition = 19, RULE_otherDeleteCondition = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_query", "update_table", "column_equals", "insert_into", "column_name", 
			"value", "delete_from", "select_from", "condition", "create_index", "tableName", 
			"columnName", "indexColumnName", "operator", "otherSelectCondition", 
			"columnOperators", "deleteColumnName", "deleteOperator", "deleteValue", 
			"deleteCondition", "otherDeleteCondition"
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
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UPDATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				update_table();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				insert_into();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
				delete_from();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				select_from();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 5);
				{
				setState(46);
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
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public TerminalNode SET() { return getToken(QueryParser.SET, 0); }
		public List<Column_equalsContext> column_equals() {
			return getRuleContexts(Column_equalsContext.class);
		}
		public Column_equalsContext column_equals(int i) {
			return getRuleContext(Column_equalsContext.class,i);
		}
		public TerminalNode WHERE() { return getToken(QueryParser.WHERE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(QueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QueryParser.COMMA, i);
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
			setState(49);
			match(UPDATE);
			setState(50);
			match(IDENTIFIER);
			setState(51);
			match(SET);
			setState(52);
			column_equals();
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(53);
				match(COMMA);
				setState(54);
				column_equals();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(WHERE);
			setState(61);
			condition();
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

	public static class Column_equalsContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public TerminalNode INTEGER() { return getToken(QueryParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public Column_equalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_equals; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitColumn_equals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_equalsContext column_equals() throws RecognitionException {
		Column_equalsContext _localctx = new Column_equalsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_column_equals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(IDENTIFIER);
			setState(64);
			match(EQUAL);
			setState(65);
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
		enterRule(_localctx, 6, RULE_insert_into);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(INSERT);
			setState(68);
			match(INTO);
			setState(69);
			match(IDENTIFIER);
			setState(70);
			match(LPAREN);
			setState(71);
			column_name();
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(72);
				match(COMMA);
				setState(73);
				column_name();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
			match(RPAREN);
			setState(80);
			match(VALUES);
			setState(81);
			match(LPAREN);
			setState(82);
			value();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(83);
				match(COMMA);
				setState(84);
				value();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
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
		enterRule(_localctx, 8, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
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
		enterRule(_localctx, 10, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
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
		public DeleteConditionContext deleteCondition() {
			return getRuleContext(DeleteConditionContext.class,0);
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
		enterRule(_localctx, 12, RULE_delete_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(DELETE);
			setState(97);
			match(FROM);
			setState(98);
			tableName();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(99);
				match(WHERE);
				setState(100);
				deleteCondition();
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(101);
					otherDeleteCondition();
					}
					}
					setState(106);
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
		enterRule(_localctx, 14, RULE_select_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(SELECT);
			setState(110);
			match(T__0);
			setState(111);
			match(FROM);
			setState(112);
			tableName();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(113);
				match(WHERE);
				setState(114);
				condition();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
					{
					{
					setState(115);
					otherSelectCondition();
					}
					}
					setState(120);
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
		enterRule(_localctx, 16, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			columnName();
			setState(124);
			operator();
			setState(125);
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
		enterRule(_localctx, 18, RULE_create_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(CREATE);
			setState(128);
			match(INDEX);
			setState(129);
			match(ON);
			setState(130);
			tableName();
			setState(131);
			match(LPAREN);
			setState(132);
			indexColumnName();
			setState(133);
			match(COMMA);
			setState(134);
			indexColumnName();
			setState(135);
			match(COMMA);
			setState(136);
			indexColumnName();
			setState(137);
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
		enterRule(_localctx, 20, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
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
		enterRule(_localctx, 22, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
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
		enterRule(_localctx, 24, RULE_indexColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
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
		enterRule(_localctx, 26, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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
		enterRule(_localctx, 28, RULE_otherSelectCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			columnOperators();
			setState(148);
			columnName();
			setState(149);
			operator();
			setState(150);
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
		enterRule(_localctx, 30, RULE_columnOperators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
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

	public static class DeleteColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public DeleteColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitDeleteColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteColumnNameContext deleteColumnName() throws RecognitionException {
		DeleteColumnNameContext _localctx = new DeleteColumnNameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_deleteColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
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

	public static class DeleteOperatorContext extends ParserRuleContext {
		public TerminalNode GREATERTHAN() { return getToken(QueryParser.GREATERTHAN, 0); }
		public TerminalNode GREATERTHANOREQUAL() { return getToken(QueryParser.GREATERTHANOREQUAL, 0); }
		public TerminalNode LESSTHAN() { return getToken(QueryParser.LESSTHAN, 0); }
		public TerminalNode LESSTHANOREQUAL() { return getToken(QueryParser.LESSTHANOREQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(QueryParser.NOTEQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(QueryParser.EQUAL, 0); }
		public DeleteOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitDeleteOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteOperatorContext deleteOperator() throws RecognitionException {
		DeleteOperatorContext _localctx = new DeleteOperatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_deleteOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
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

	public static class DeleteValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(QueryParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public DeleteValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitDeleteValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteValueContext deleteValue() throws RecognitionException {
		DeleteValueContext _localctx = new DeleteValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_deleteValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
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

	public static class DeleteConditionContext extends ParserRuleContext {
		public DeleteColumnNameContext deleteColumnName() {
			return getRuleContext(DeleteColumnNameContext.class,0);
		}
		public DeleteOperatorContext deleteOperator() {
			return getRuleContext(DeleteOperatorContext.class,0);
		}
		public DeleteValueContext deleteValue() {
			return getRuleContext(DeleteValueContext.class,0);
		}
		public DeleteConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteCondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitDeleteCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteConditionContext deleteCondition() throws RecognitionException {
		DeleteConditionContext _localctx = new DeleteConditionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_deleteCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			deleteColumnName();
			setState(161);
			deleteOperator();
			setState(162);
			deleteValue();
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
		public DeleteColumnNameContext deleteColumnName() {
			return getRuleContext(DeleteColumnNameContext.class,0);
		}
		public DeleteOperatorContext deleteOperator() {
			return getRuleContext(DeleteOperatorContext.class,0);
		}
		public DeleteValueContext deleteValue() {
			return getRuleContext(DeleteValueContext.class,0);
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
		enterRule(_localctx, 40, RULE_otherDeleteCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(COMMA);
			setState(165);
			deleteColumnName();
			setState(166);
			deleteOperator();
			setState(167);
			deleteValue();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00ac\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\5\2\62\n"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5M\n\5\f\5\16\5P\13\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5X\n\5\f\5\16\5[\13\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\7\bi\n\b\f\b\16\bl\13\b\5\bn\n\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\tw\n\t\f\t\16\tz\13\t\5\t|\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\2\2\27\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\5\3\2\25\27\3\2\30\35\3"+
		"\2\r\17\2\u00a1\2\61\3\2\2\2\4\63\3\2\2\2\6A\3\2\2\2\bE\3\2\2\2\n^\3\2"+
		"\2\2\f`\3\2\2\2\16b\3\2\2\2\20o\3\2\2\2\22}\3\2\2\2\24\u0081\3\2\2\2\26"+
		"\u008d\3\2\2\2\30\u008f\3\2\2\2\32\u0091\3\2\2\2\34\u0093\3\2\2\2\36\u0095"+
		"\3\2\2\2 \u009a\3\2\2\2\"\u009c\3\2\2\2$\u009e\3\2\2\2&\u00a0\3\2\2\2"+
		"(\u00a2\3\2\2\2*\u00a6\3\2\2\2,\62\5\4\3\2-\62\5\b\5\2.\62\5\16\b\2/\62"+
		"\5\20\t\2\60\62\5\24\13\2\61,\3\2\2\2\61-\3\2\2\2\61.\3\2\2\2\61/\3\2"+
		"\2\2\61\60\3\2\2\2\62\3\3\2\2\2\63\64\7\6\2\2\64\65\7\24\2\2\65\66\7\t"+
		"\2\2\66;\5\6\4\2\678\7 \2\28:\5\6\4\29\67\3\2\2\2:=\3\2\2\2;9\3\2\2\2"+
		";<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\f\2\2?@\5\22\n\2@\5\3\2\2\2AB\7\24"+
		"\2\2BC\7\35\2\2CD\t\2\2\2D\7\3\2\2\2EF\7\7\2\2FG\7\22\2\2GH\7\24\2\2H"+
		"I\7\36\2\2IN\5\n\6\2JK\7 \2\2KM\5\n\6\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2"+
		"NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\37\2\2RS\7\23\2\2ST\7\36\2\2TY\5\f"+
		"\7\2UV\7 \2\2VX\5\f\7\2WU\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2"+
		"\2\2[Y\3\2\2\2\\]\7\37\2\2]\t\3\2\2\2^_\7\24\2\2_\13\3\2\2\2`a\t\2\2\2"+
		"a\r\3\2\2\2bc\7\b\2\2cd\7\13\2\2dm\5\26\f\2ef\7\f\2\2fj\5(\25\2gi\5*\26"+
		"\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2kn\3\2\2\2lj\3\2\2\2me\3\2\2"+
		"\2mn\3\2\2\2n\17\3\2\2\2op\7\n\2\2pq\7\3\2\2qr\7\13\2\2r{\5\26\f\2st\7"+
		"\f\2\2tx\5\22\n\2uw\5\36\20\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2"+
		"y|\3\2\2\2zx\3\2\2\2{s\3\2\2\2{|\3\2\2\2|\21\3\2\2\2}~\5\30\r\2~\177\5"+
		"\34\17\2\177\u0080\5\f\7\2\u0080\23\3\2\2\2\u0081\u0082\7\5\2\2\u0082"+
		"\u0083\7\20\2\2\u0083\u0084\7\21\2\2\u0084\u0085\5\26\f\2\u0085\u0086"+
		"\7\36\2\2\u0086\u0087\5\32\16\2\u0087\u0088\7 \2\2\u0088\u0089\5\32\16"+
		"\2\u0089\u008a\7 \2\2\u008a\u008b\5\32\16\2\u008b\u008c\7\37\2\2\u008c"+
		"\25\3\2\2\2\u008d\u008e\7\24\2\2\u008e\27\3\2\2\2\u008f\u0090\7\24\2\2"+
		"\u0090\31\3\2\2\2\u0091\u0092\7\24\2\2\u0092\33\3\2\2\2\u0093\u0094\t"+
		"\3\2\2\u0094\35\3\2\2\2\u0095\u0096\5 \21\2\u0096\u0097\5\30\r\2\u0097"+
		"\u0098\5\34\17\2\u0098\u0099\5\f\7\2\u0099\37\3\2\2\2\u009a\u009b\t\4"+
		"\2\2\u009b!\3\2\2\2\u009c\u009d\7\24\2\2\u009d#\3\2\2\2\u009e\u009f\t"+
		"\3\2\2\u009f%\3\2\2\2\u00a0\u00a1\t\2\2\2\u00a1\'\3\2\2\2\u00a2\u00a3"+
		"\5\"\22\2\u00a3\u00a4\5$\23\2\u00a4\u00a5\5&\24\2\u00a5)\3\2\2\2\u00a6"+
		"\u00a7\7 \2\2\u00a7\u00a8\5\"\22\2\u00a8\u00a9\5$\23\2\u00a9\u00aa\5&"+
		"\24\2\u00aa+\3\2\2\2\n\61;NYjmx{";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}