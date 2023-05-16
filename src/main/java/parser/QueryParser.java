// Generated from Query.g4 by ANTLR 4.9.2

package parser;

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
		PRIMARY=18, INT=19, DECIMALSTRING=20, VARCHAR=21, DATESTRING=22, KEY=23, 
		IDENTIFIER=24, INTEGER=25, DECIMAL=26, STRING=27, DATE=28, GREATERTHAN=29, 
		GREATERTHANOREQUAL=30, LESSTHAN=31, LESSTHANOREQUAL=32, NOTEQUAL=33, EQUAL=34, 
		LPAREN=35, RPAREN=36, COMMA=37, SEMICOLON=38, WS=39;
	public static final int
		RULE_sql_query = 0, RULE_create_table = 1, RULE_column_def = 2, RULE_createColumnName = 3, 
		RULE_primaryKey = 4, RULE_datatype = 5, RULE_update_table = 6, RULE_insert_into = 7, 
		RULE_value = 8, RULE_insertValue = 9, RULE_delete_from = 10, RULE_select_from = 11, 
		RULE_condition = 12, RULE_create_index = 13, RULE_tableName = 14, RULE_columnName = 15, 
		RULE_indexColumnName = 16, RULE_operator = 17, RULE_otherSelectCondition = 18, 
		RULE_columnOperators = 19, RULE_updateDeleteColumnName = 20, RULE_updateDeleteValue = 21, 
		RULE_updateDeleteCondition = 22, RULE_otherDeleteCondition = 23, RULE_updateColumnName = 24, 
		RULE_updateValue = 25, RULE_updateColumnToSet = 26, RULE_otherUpdateColumnToSet = 27, 
		RULE_insertColumnName = 28, RULE_additionalColumnInsert = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_query", "create_table", "column_def", "createColumnName", "primaryKey", 
			"datatype", "update_table", "insert_into", "value", "insertValue", "delete_from", 
			"select_from", "condition", "create_index", "tableName", "columnName", 
			"indexColumnName", "operator", "otherSelectCondition", "columnOperators", 
			"updateDeleteColumnName", "updateDeleteValue", "updateDeleteCondition", 
			"otherDeleteCondition", "updateColumnName", "updateValue", "updateColumnToSet", 
			"otherUpdateColumnToSet", "insertColumnName", "additionalColumnInsert"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'>'", "'>='", "'<'", "'<='", "'!='", "'='", 
			"'('", "')'", "','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "TABLE", "CREATE", "UPDATE", "INSERT", "DELETE", "SET", "SELECT", 
			"FROM", "WHERE", "AND", "OR", "XOR", "INDEX", "ON", "INTO", "VALUES", 
			"PRIMARY", "INT", "DECIMALSTRING", "VARCHAR", "DATESTRING", "KEY", "IDENTIFIER", 
			"INTEGER", "DECIMAL", "STRING", "DATE", "GREATERTHAN", "GREATERTHANOREQUAL", 
			"LESSTHAN", "LESSTHANOREQUAL", "NOTEQUAL", "EQUAL", "LPAREN", "RPAREN", 
			"COMMA", "SEMICOLON", "WS"
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
		public Create_tableContext create_table() {
			return getRuleContext(Create_tableContext.class,0);
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
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				update_table();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				insert_into();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				delete_from();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				select_from();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(64);
				create_index();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(65);
				create_table();
				}
				break;
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

	public static class Create_tableContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(QueryParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(QueryParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public List<TerminalNode> COMMA() { return getTokens(QueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QueryParser.COMMA, i);
		}
		public TerminalNode SEMICOLON() { return getToken(QueryParser.SEMICOLON, 0); }
		public Create_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitCreate_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_tableContext create_table() throws RecognitionException {
		Create_tableContext _localctx = new Create_tableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_create_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(CREATE);
			setState(69);
			match(TABLE);
			setState(70);
			tableName();
			setState(71);
			match(LPAREN);
			setState(72);
			column_def();
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(73);
				match(COMMA);
				setState(74);
				column_def();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
			match(RPAREN);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(81);
				match(SEMICOLON);
				}
			}

			setState(84);
			match(EOF);
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

	public static class Column_defContext extends ParserRuleContext {
		public CreateColumnNameContext createColumnName() {
			return getRuleContext(CreateColumnNameContext.class,0);
		}
		public DatatypeContext datatype() {
			return getRuleContext(DatatypeContext.class,0);
		}
		public PrimaryKeyContext primaryKey() {
			return getRuleContext(PrimaryKeyContext.class,0);
		}
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			createColumnName();
			setState(87);
			datatype();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIMARY) {
				{
				setState(88);
				primaryKey();
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

	public static class CreateColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public CreateColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitCreateColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateColumnNameContext createColumnName() throws RecognitionException {
		CreateColumnNameContext _localctx = new CreateColumnNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_createColumnName);
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

	public static class PrimaryKeyContext extends ParserRuleContext {
		public TerminalNode PRIMARY() { return getToken(QueryParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(QueryParser.KEY, 0); }
		public PrimaryKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryKey; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitPrimaryKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryKeyContext primaryKey() throws RecognitionException {
		PrimaryKeyContext _localctx = new PrimaryKeyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_primaryKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(PRIMARY);
			setState(94);
			match(KEY);
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

	public static class DatatypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(QueryParser.INT, 0); }
		public TerminalNode DECIMALSTRING() { return getToken(QueryParser.DECIMALSTRING, 0); }
		public TerminalNode VARCHAR() { return getToken(QueryParser.VARCHAR, 0); }
		public TerminalNode DATESTRING() { return getToken(QueryParser.DATESTRING, 0); }
		public DatatypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_datatype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitDatatype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatatypeContext datatype() throws RecognitionException {
		DatatypeContext _localctx = new DatatypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_datatype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << DECIMALSTRING) | (1L << VARCHAR) | (1L << DATESTRING))) != 0)) ) {
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
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public List<OtherUpdateColumnToSetContext> otherUpdateColumnToSet() {
			return getRuleContexts(OtherUpdateColumnToSetContext.class);
		}
		public OtherUpdateColumnToSetContext otherUpdateColumnToSet(int i) {
			return getRuleContext(OtherUpdateColumnToSetContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(QueryParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 12, RULE_update_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(UPDATE);
			setState(99);
			tableName();
			setState(100);
			match(SET);
			setState(101);
			updateColumnToSet();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(102);
				otherUpdateColumnToSet();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(WHERE);
			setState(109);
			updateDeleteCondition();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(110);
				match(SEMICOLON);
				}
			}

			setState(113);
			match(EOF);
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
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(QueryParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(QueryParser.LPAREN, i);
		}
		public InsertColumnNameContext insertColumnName() {
			return getRuleContext(InsertColumnNameContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(QueryParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(QueryParser.RPAREN, i);
		}
		public TerminalNode VALUES() { return getToken(QueryParser.VALUES, 0); }
		public List<InsertValueContext> insertValue() {
			return getRuleContexts(InsertValueContext.class);
		}
		public InsertValueContext insertValue(int i) {
			return getRuleContext(InsertValueContext.class,i);
		}
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public List<AdditionalColumnInsertContext> additionalColumnInsert() {
			return getRuleContexts(AdditionalColumnInsertContext.class);
		}
		public AdditionalColumnInsertContext additionalColumnInsert(int i) {
			return getRuleContext(AdditionalColumnInsertContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QueryParser.COMMA, i);
		}
		public TerminalNode SEMICOLON() { return getToken(QueryParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 14, RULE_insert_into);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(INSERT);
			setState(116);
			match(INTO);
			setState(117);
			tableName();
			setState(118);
			match(LPAREN);
			setState(119);
			insertColumnName();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(120);
				additionalColumnInsert();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(RPAREN);
			setState(127);
			match(VALUES);
			setState(128);
			match(LPAREN);
			setState(129);
			insertValue();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(130);
				match(COMMA);
				setState(131);
				insertValue();
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(137);
			match(RPAREN);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(138);
				match(SEMICOLON);
				}
			}

			setState(141);
			match(EOF);
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
		public TerminalNode DATE() { return getToken(QueryParser.DATE, 0); }
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
		enterRule(_localctx, 16, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING) | (1L << DATE))) != 0)) ) {
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

	public static class InsertValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(QueryParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode DATE() { return getToken(QueryParser.DATE, 0); }
		public InsertValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitInsertValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertValueContext insertValue() throws RecognitionException {
		InsertValueContext _localctx = new InsertValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_insertValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING) | (1L << DATE))) != 0)) ) {
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
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public TerminalNode WHERE() { return getToken(QueryParser.WHERE, 0); }
		public UpdateDeleteConditionContext updateDeleteCondition() {
			return getRuleContext(UpdateDeleteConditionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(QueryParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 20, RULE_delete_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(DELETE);
			setState(148);
			match(FROM);
			setState(149);
			tableName();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(150);
				match(WHERE);
				setState(151);
				updateDeleteCondition();
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(152);
					otherDeleteCondition();
					}
					}
					setState(157);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(160);
				match(SEMICOLON);
				}
			}

			setState(163);
			match(EOF);
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
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public TerminalNode WHERE() { return getToken(QueryParser.WHERE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(QueryParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 22, RULE_select_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(SELECT);
			setState(166);
			match(T__0);
			setState(167);
			match(FROM);
			setState(168);
			tableName();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(169);
				match(WHERE);
				setState(170);
				condition();
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
					{
					{
					setState(171);
					otherSelectCondition();
					}
					}
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(179);
				match(SEMICOLON);
				}
			}

			setState(182);
			match(EOF);
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
		enterRule(_localctx, 24, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			columnName();
			setState(185);
			operator();
			setState(186);
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
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public TerminalNode SEMICOLON() { return getToken(QueryParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 26, RULE_create_index);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(CREATE);
			setState(189);
			match(INDEX);
			setState(190);
			match(ON);
			setState(191);
			tableName();
			setState(192);
			match(LPAREN);
			setState(193);
			indexColumnName();
			setState(194);
			match(COMMA);
			setState(195);
			indexColumnName();
			setState(196);
			match(COMMA);
			setState(197);
			indexColumnName();
			setState(198);
			match(RPAREN);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(199);
				match(SEMICOLON);
				}
			}

			setState(202);
			match(EOF);
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
		enterRule(_localctx, 28, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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
		enterRule(_localctx, 30, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
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
		enterRule(_localctx, 32, RULE_indexColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
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
		enterRule(_localctx, 34, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
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
		enterRule(_localctx, 36, RULE_otherSelectCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			columnOperators();
			setState(213);
			columnName();
			setState(214);
			operator();
			setState(215);
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
		enterRule(_localctx, 38, RULE_columnOperators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
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
		enterRule(_localctx, 40, RULE_updateDeleteColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
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
		public TerminalNode DATE() { return getToken(QueryParser.DATE, 0); }
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
		enterRule(_localctx, 42, RULE_updateDeleteValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING) | (1L << DATE))) != 0)) ) {
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
		enterRule(_localctx, 44, RULE_updateDeleteCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			updateDeleteColumnName();
			setState(224);
			match(EQUAL);
			setState(225);
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
		public TerminalNode AND() { return getToken(QueryParser.AND, 0); }
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
		enterRule(_localctx, 46, RULE_otherDeleteCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(AND);
			setState(228);
			updateDeleteColumnName();
			setState(229);
			match(EQUAL);
			setState(230);
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
		enterRule(_localctx, 48, RULE_updateColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
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
		public TerminalNode DATE() { return getToken(QueryParser.DATE, 0); }
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
		enterRule(_localctx, 50, RULE_updateValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << STRING) | (1L << DATE))) != 0)) ) {
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
		enterRule(_localctx, 52, RULE_updateColumnToSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			updateColumnName();
			setState(237);
			match(EQUAL);
			setState(238);
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
		public TerminalNode AND() { return getToken(QueryParser.AND, 0); }
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
		enterRule(_localctx, 54, RULE_otherUpdateColumnToSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(AND);
			setState(241);
			updateColumnName();
			setState(242);
			match(EQUAL);
			setState(243);
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

	public static class InsertColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryParser.IDENTIFIER, 0); }
		public InsertColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitInsertColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertColumnNameContext insertColumnName() throws RecognitionException {
		InsertColumnNameContext _localctx = new InsertColumnNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_insertColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
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

	public static class AdditionalColumnInsertContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(QueryParser.COMMA, 0); }
		public InsertColumnNameContext insertColumnName() {
			return getRuleContext(InsertColumnNameContext.class,0);
		}
		public AdditionalColumnInsertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionalColumnInsert; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryVisitor ) return ((QueryVisitor<? extends T>)visitor).visitAdditionalColumnInsert(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionalColumnInsertContext additionalColumnInsert() throws RecognitionException {
		AdditionalColumnInsertContext _localctx = new AdditionalColumnInsertContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_additionalColumnInsert);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(COMMA);
			setState(248);
			insertColumnName();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u00fd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\5\2E\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3"+
		"Q\13\3\3\3\3\3\5\3U\n\3\3\3\3\3\3\4\3\4\3\4\5\4\\\n\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\bj\n\b\f\b\16\bm\13\b\3\b\3\b\3\b\5"+
		"\br\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\t|\n\t\f\t\16\t\177\13\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\7\t\u0087\n\t\f\t\16\t\u008a\13\t\3\t\3\t\5\t\u008e"+
		"\n\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u009c\n\f\f"+
		"\f\16\f\u009f\13\f\5\f\u00a1\n\f\3\f\5\f\u00a4\n\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\7\r\u00af\n\r\f\r\16\r\u00b2\13\r\5\r\u00b4\n\r\3\r\5"+
		"\r\u00b7\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00cb\n\17\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<\2\6\3\2\25\30\3\2\33\36\3\2\37$\3\2\r\17\2\u00f2\2D\3\2\2\2"+
		"\4F\3\2\2\2\6X\3\2\2\2\b]\3\2\2\2\n_\3\2\2\2\fb\3\2\2\2\16d\3\2\2\2\20"+
		"u\3\2\2\2\22\u0091\3\2\2\2\24\u0093\3\2\2\2\26\u0095\3\2\2\2\30\u00a7"+
		"\3\2\2\2\32\u00ba\3\2\2\2\34\u00be\3\2\2\2\36\u00ce\3\2\2\2 \u00d0\3\2"+
		"\2\2\"\u00d2\3\2\2\2$\u00d4\3\2\2\2&\u00d6\3\2\2\2(\u00db\3\2\2\2*\u00dd"+
		"\3\2\2\2,\u00df\3\2\2\2.\u00e1\3\2\2\2\60\u00e5\3\2\2\2\62\u00ea\3\2\2"+
		"\2\64\u00ec\3\2\2\2\66\u00ee\3\2\2\28\u00f2\3\2\2\2:\u00f7\3\2\2\2<\u00f9"+
		"\3\2\2\2>E\5\16\b\2?E\5\20\t\2@E\5\26\f\2AE\5\30\r\2BE\5\34\17\2CE\5\4"+
		"\3\2D>\3\2\2\2D?\3\2\2\2D@\3\2\2\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\3\3"+
		"\2\2\2FG\7\5\2\2GH\7\4\2\2HI\5\36\20\2IJ\7%\2\2JO\5\6\4\2KL\7\'\2\2LN"+
		"\5\6\4\2MK\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2R"+
		"T\7&\2\2SU\7(\2\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\2\2\3W\5\3\2\2\2X"+
		"Y\5\b\5\2Y[\5\f\7\2Z\\\5\n\6\2[Z\3\2\2\2[\\\3\2\2\2\\\7\3\2\2\2]^\7\32"+
		"\2\2^\t\3\2\2\2_`\7\24\2\2`a\7\31\2\2a\13\3\2\2\2bc\t\2\2\2c\r\3\2\2\2"+
		"de\7\6\2\2ef\5\36\20\2fg\7\t\2\2gk\5\66\34\2hj\58\35\2ih\3\2\2\2jm\3\2"+
		"\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7\f\2\2oq\5.\30\2pr\7("+
		"\2\2qp\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\2\2\3t\17\3\2\2\2uv\7\7\2\2vw\7"+
		"\22\2\2wx\5\36\20\2xy\7%\2\2y}\5:\36\2z|\5<\37\2{z\3\2\2\2|\177\3\2\2"+
		"\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\7&\2\2"+
		"\u0081\u0082\7\23\2\2\u0082\u0083\7%\2\2\u0083\u0088\5\24\13\2\u0084\u0085"+
		"\7\'\2\2\u0085\u0087\5\24\13\2\u0086\u0084\3\2\2\2\u0087\u008a\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008b\u008d\7&\2\2\u008c\u008e\7(\2\2\u008d\u008c\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7\2\2\3\u0090\21\3\2\2"+
		"\2\u0091\u0092\t\3\2\2\u0092\23\3\2\2\2\u0093\u0094\t\3\2\2\u0094\25\3"+
		"\2\2\2\u0095\u0096\7\b\2\2\u0096\u0097\7\13\2\2\u0097\u00a0\5\36\20\2"+
		"\u0098\u0099\7\f\2\2\u0099\u009d\5.\30\2\u009a\u009c\5\60\31\2\u009b\u009a"+
		"\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u0098\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a4\7(\2\2\u00a3\u00a2\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\7\2\2\3\u00a6\27\3\2\2"+
		"\2\u00a7\u00a8\7\n\2\2\u00a8\u00a9\7\3\2\2\u00a9\u00aa\7\13\2\2\u00aa"+
		"\u00b3\5\36\20\2\u00ab\u00ac\7\f\2\2\u00ac\u00b0\5\32\16\2\u00ad\u00af"+
		"\5&\24\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00ab\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b7\7(\2\2\u00b6"+
		"\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7\2"+
		"\2\3\u00b9\31\3\2\2\2\u00ba\u00bb\5 \21\2\u00bb\u00bc\5$\23\2\u00bc\u00bd"+
		"\5\22\n\2\u00bd\33\3\2\2\2\u00be\u00bf\7\5\2\2\u00bf\u00c0\7\20\2\2\u00c0"+
		"\u00c1\7\21\2\2\u00c1\u00c2\5\36\20\2\u00c2\u00c3\7%\2\2\u00c3\u00c4\5"+
		"\"\22\2\u00c4\u00c5\7\'\2\2\u00c5\u00c6\5\"\22\2\u00c6\u00c7\7\'\2\2\u00c7"+
		"\u00c8\5\"\22\2\u00c8\u00ca\7&\2\2\u00c9\u00cb\7(\2\2\u00ca\u00c9\3\2"+
		"\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\7\2\2\3\u00cd"+
		"\35\3\2\2\2\u00ce\u00cf\7\32\2\2\u00cf\37\3\2\2\2\u00d0\u00d1\7\32\2\2"+
		"\u00d1!\3\2\2\2\u00d2\u00d3\7\32\2\2\u00d3#\3\2\2\2\u00d4\u00d5\t\4\2"+
		"\2\u00d5%\3\2\2\2\u00d6\u00d7\5(\25\2\u00d7\u00d8\5 \21\2\u00d8\u00d9"+
		"\5$\23\2\u00d9\u00da\5\22\n\2\u00da\'\3\2\2\2\u00db\u00dc\t\5\2\2\u00dc"+
		")\3\2\2\2\u00dd\u00de\7\32\2\2\u00de+\3\2\2\2\u00df\u00e0\t\3\2\2\u00e0"+
		"-\3\2\2\2\u00e1\u00e2\5*\26\2\u00e2\u00e3\7$\2\2\u00e3\u00e4\5,\27\2\u00e4"+
		"/\3\2\2\2\u00e5\u00e6\7\r\2\2\u00e6\u00e7\5*\26\2\u00e7\u00e8\7$\2\2\u00e8"+
		"\u00e9\5,\27\2\u00e9\61\3\2\2\2\u00ea\u00eb\7\32\2\2\u00eb\63\3\2\2\2"+
		"\u00ec\u00ed\t\3\2\2\u00ed\65\3\2\2\2\u00ee\u00ef\5\62\32\2\u00ef\u00f0"+
		"\7$\2\2\u00f0\u00f1\5\64\33\2\u00f1\67\3\2\2\2\u00f2\u00f3\7\r\2\2\u00f3"+
		"\u00f4\5\62\32\2\u00f4\u00f5\7$\2\2\u00f5\u00f6\5\64\33\2\u00f69\3\2\2"+
		"\2\u00f7\u00f8\7\32\2\2\u00f8;\3\2\2\2\u00f9\u00fa\7\'\2\2\u00fa\u00fb"+
		"\5:\36\2\u00fb=\3\2\2\2\22DOT[kq}\u0088\u008d\u009d\u00a0\u00a3\u00b0"+
		"\u00b3\u00b6\u00ca";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}