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
		RULE_sql_query = 0, RULE_update_table = 1, RULE_insert_into = 2, RULE_value = 3, 
		RULE_insertValue = 4, RULE_delete_from = 5, RULE_select_from = 6, RULE_condition = 7, 
		RULE_create_index = 8, RULE_tableName = 9, RULE_columnName = 10, RULE_indexColumnName = 11, 
		RULE_operator = 12, RULE_otherSelectCondition = 13, RULE_columnOperators = 14, 
		RULE_updateDeleteColumnName = 15, RULE_updateDeleteValue = 16, RULE_updateDeleteCondition = 17, 
		RULE_otherDeleteCondition = 18, RULE_updateColumnName = 19, RULE_updateValue = 20, 
		RULE_updateColumnToSet = 21, RULE_otherUpdateColumnToSet = 22, RULE_insertColumnName = 23, 
		RULE_additionalColumnInsert = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_query", "update_table", "insert_into", "value", "insertValue", "delete_from", 
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
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UPDATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				update_table();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				insert_into();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				delete_from();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(53);
				select_from();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 5);
				{
				setState(54);
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
			setState(57);
			match(UPDATE);
			setState(58);
			tableName();
			setState(59);
			match(SET);
			setState(60);
			updateColumnToSet();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(61);
				otherUpdateColumnToSet();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(WHERE);
			setState(68);
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
			setState(70);
			match(INSERT);
			setState(71);
			match(INTO);
			setState(72);
			tableName();
			setState(73);
			match(LPAREN);
			setState(74);
			insertColumnName();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(75);
				additionalColumnInsert();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
			match(RPAREN);
			setState(82);
			match(VALUES);
			setState(83);
			match(LPAREN);
			setState(84);
			insertValue();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(85);
				match(COMMA);
				setState(86);
				insertValue();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
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
		enterRule(_localctx, 6, RULE_value);
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

	public static class InsertValueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
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
		enterRule(_localctx, 8, RULE_insertValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
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
			setState(98);
			match(DELETE);
			setState(99);
			match(FROM);
			setState(100);
			tableName();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(101);
				match(WHERE);
				setState(102);
				updateDeleteCondition();
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(103);
					otherDeleteCondition();
					}
					}
					setState(108);
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
			setState(111);
			match(SELECT);
			setState(112);
			match(T__0);
			setState(113);
			match(FROM);
			setState(114);
			tableName();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(115);
				match(WHERE);
				setState(116);
				condition();
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
					{
					{
					setState(117);
					otherSelectCondition();
					}
					}
					setState(122);
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
			setState(125);
			columnName();
			setState(126);
			operator();
			setState(127);
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
			setState(129);
			match(CREATE);
			setState(130);
			match(INDEX);
			setState(131);
			match(ON);
			setState(132);
			tableName();
			setState(133);
			match(LPAREN);
			setState(134);
			indexColumnName();
			setState(135);
			match(COMMA);
			setState(136);
			indexColumnName();
			setState(137);
			match(COMMA);
			setState(138);
			indexColumnName();
			setState(139);
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
			setState(145);
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
			setState(147);
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
			setState(149);
			columnOperators();
			setState(150);
			columnName();
			setState(151);
			operator();
			setState(152);
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
			setState(154);
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
			setState(156);
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
			setState(160);
			updateDeleteColumnName();
			setState(161);
			match(EQUAL);
			setState(162);
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
			setState(164);
			match(COMMA);
			setState(165);
			updateDeleteColumnName();
			setState(166);
			match(EQUAL);
			setState(167);
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
			setState(169);
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
			setState(171);
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
			setState(173);
			updateColumnName();
			setState(174);
			match(EQUAL);
			setState(175);
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
			setState(177);
			match(COMMA);
			setState(178);
			updateColumnName();
			setState(179);
			match(EQUAL);
			setState(180);
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
		enterRule(_localctx, 46, RULE_insertColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
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
		enterRule(_localctx, 48, RULE_additionalColumnInsert);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(COMMA);
			setState(185);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\5\2:\n\2\3\3\3\3\3\3\3\3\3\3\7\3A\n\3\f"+
		"\3\16\3D\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4O\n\4\f\4\16\4R\13"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7k\n\7\f\7\16\7n\13\7\5\7p\n\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\7\by\n\b\f\b\16\b|\13\b\5\b~\n\b\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\5\3\2"+
		"\25\27\3\2\30\35\3\2\r\17\2\u00af\29\3\2\2\2\4;\3\2\2\2\6H\3\2\2\2\b`"+
		"\3\2\2\2\nb\3\2\2\2\fd\3\2\2\2\16q\3\2\2\2\20\177\3\2\2\2\22\u0083\3\2"+
		"\2\2\24\u008f\3\2\2\2\26\u0091\3\2\2\2\30\u0093\3\2\2\2\32\u0095\3\2\2"+
		"\2\34\u0097\3\2\2\2\36\u009c\3\2\2\2 \u009e\3\2\2\2\"\u00a0\3\2\2\2$\u00a2"+
		"\3\2\2\2&\u00a6\3\2\2\2(\u00ab\3\2\2\2*\u00ad\3\2\2\2,\u00af\3\2\2\2."+
		"\u00b3\3\2\2\2\60\u00b8\3\2\2\2\62\u00ba\3\2\2\2\64:\5\4\3\2\65:\5\6\4"+
		"\2\66:\5\f\7\2\67:\5\16\b\28:\5\22\n\29\64\3\2\2\29\65\3\2\2\29\66\3\2"+
		"\2\29\67\3\2\2\298\3\2\2\2:\3\3\2\2\2;<\7\6\2\2<=\5\24\13\2=>\7\t\2\2"+
		">B\5,\27\2?A\5.\30\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2"+
		"DB\3\2\2\2EF\7\f\2\2FG\5$\23\2G\5\3\2\2\2HI\7\7\2\2IJ\7\22\2\2JK\5\24"+
		"\13\2KL\7\36\2\2LP\5\60\31\2MO\5\62\32\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2"+
		"PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7\37\2\2TU\7\23\2\2UV\7\36\2\2V[\5\n"+
		"\6\2WX\7 \2\2XZ\5\n\6\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3"+
		"\2\2\2][\3\2\2\2^_\7\37\2\2_\7\3\2\2\2`a\t\2\2\2a\t\3\2\2\2bc\5\b\5\2"+
		"c\13\3\2\2\2de\7\b\2\2ef\7\13\2\2fo\5\24\13\2gh\7\f\2\2hl\5$\23\2ik\5"+
		"&\24\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2nl\3\2\2\2og\3"+
		"\2\2\2op\3\2\2\2p\r\3\2\2\2qr\7\n\2\2rs\7\3\2\2st\7\13\2\2t}\5\24\13\2"+
		"uv\7\f\2\2vz\5\20\t\2wy\5\34\17\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2"+
		"\2\2{~\3\2\2\2|z\3\2\2\2}u\3\2\2\2}~\3\2\2\2~\17\3\2\2\2\177\u0080\5\26"+
		"\f\2\u0080\u0081\5\32\16\2\u0081\u0082\5\b\5\2\u0082\21\3\2\2\2\u0083"+
		"\u0084\7\5\2\2\u0084\u0085\7\20\2\2\u0085\u0086\7\21\2\2\u0086\u0087\5"+
		"\24\13\2\u0087\u0088\7\36\2\2\u0088\u0089\5\30\r\2\u0089\u008a\7 \2\2"+
		"\u008a\u008b\5\30\r\2\u008b\u008c\7 \2\2\u008c\u008d\5\30\r\2\u008d\u008e"+
		"\7\37\2\2\u008e\23\3\2\2\2\u008f\u0090\7\24\2\2\u0090\25\3\2\2\2\u0091"+
		"\u0092\7\24\2\2\u0092\27\3\2\2\2\u0093\u0094\7\24\2\2\u0094\31\3\2\2\2"+
		"\u0095\u0096\t\3\2\2\u0096\33\3\2\2\2\u0097\u0098\5\36\20\2\u0098\u0099"+
		"\5\26\f\2\u0099\u009a\5\32\16\2\u009a\u009b\5\b\5\2\u009b\35\3\2\2\2\u009c"+
		"\u009d\t\4\2\2\u009d\37\3\2\2\2\u009e\u009f\7\24\2\2\u009f!\3\2\2\2\u00a0"+
		"\u00a1\t\2\2\2\u00a1#\3\2\2\2\u00a2\u00a3\5 \21\2\u00a3\u00a4\7\35\2\2"+
		"\u00a4\u00a5\5\"\22\2\u00a5%\3\2\2\2\u00a6\u00a7\7 \2\2\u00a7\u00a8\5"+
		" \21\2\u00a8\u00a9\7\35\2\2\u00a9\u00aa\5\"\22\2\u00aa\'\3\2\2\2\u00ab"+
		"\u00ac\7\24\2\2\u00ac)\3\2\2\2\u00ad\u00ae\t\2\2\2\u00ae+\3\2\2\2\u00af"+
		"\u00b0\5(\25\2\u00b0\u00b1\7\35\2\2\u00b1\u00b2\5*\26\2\u00b2-\3\2\2\2"+
		"\u00b3\u00b4\7 \2\2\u00b4\u00b5\5(\25\2\u00b5\u00b6\7\35\2\2\u00b6\u00b7"+
		"\5*\26\2\u00b7/\3\2\2\2\u00b8\u00b9\7\24\2\2\u00b9\61\3\2\2\2\u00ba\u00bb"+
		"\7 \2\2\u00bb\u00bc\5\60\31\2\u00bc\63\3\2\2\2\n9BP[loz}";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}