// Generated from Query.g4 by ANTLR 4.9.2

package sqlAntlrParser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
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
		LPAREN=35, RPAREN=36, COMMA=37, WS=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "LETTER", "DIGIT", "DAY", "MONTH", "YEAR", "A", "B", "C", "D", 
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
			"S", "T", "U", "V", "W", "X", "Y", "Z", "TABLE", "CREATE", "UPDATE", 
			"INSERT", "DELETE", "SET", "SELECT", "FROM", "WHERE", "AND", "OR", "XOR", 
			"INDEX", "ON", "INTO", "VALUES", "PRIMARY", "INT", "DECIMALSTRING", "VARCHAR", 
			"DATESTRING", "KEY", "IDENTIFIER", "INTEGER", "DECIMAL", "STRING", "DATE", 
			"GREATERTHAN", "GREATERTHANOREQUAL", "LESSTHAN", "LESSTHANOREQUAL", "NOTEQUAL", 
			"EQUAL", "LPAREN", "RPAREN", "COMMA", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'>'", "'>='", "'<'", "'<='", "'!='", "'='", 
			"'('", "')'", "','"
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


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0194\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3"+
		"\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$"+
		"\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'"+
		"\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3,"+
		"\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\60\3\60"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67"+
		"\3\67\3\67\38\38\38\38\78\u0154\n8\f8\168\u0157\138\39\69\u015a\n9\r9"+
		"\169\u015b\3:\6:\u015f\n:\r:\16:\u0160\3:\3:\7:\u0165\n:\f:\16:\u0168"+
		"\13:\3;\3;\7;\u016c\n;\f;\16;\u016f\13;\3;\3;\3<\3<\3<\3<\3<\3<\3=\3="+
		"\3>\3>\3>\3?\3?\3@\3@\3@\3A\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\6F\u018f"+
		"\nF\rF\16F\u0190\3F\3F\2\2G\3\3\5\2\7\2\t\2\13\2\r\2\17\2\21\2\23\2\25"+
		"\2\27\2\31\2\33\2\35\2\37\2!\2#\2%\2\'\2)\2+\2-\2/\2\61\2\63\2\65\2\67"+
		"\29\2;\2=\2?\2A\2C\4E\5G\6I\7K\bM\tO\nQ\13S\fU\rW\16Y\17[\20]\21_\22a"+
		"\23c\24e\25g\26i\27k\30m\31o\32q\33s\34u\35w\36y\37{ }!\177\"\u0081#\u0083"+
		"$\u0085%\u0087&\u0089\'\u008b(\3\2 \4\2C\\c|\3\2\62;\4\2CCcc\4\2DDdd\4"+
		"\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMm"+
		"m\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2"+
		"VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\f\f\17\17)"+
		")\5\2\13\f\17\17\"\"\2\u017c\2\3\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2"+
		"\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2"+
		"\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a"+
		"\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2"+
		"\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2"+
		"\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2"+
		"\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\3\u008d"+
		"\3\2\2\2\5\u008f\3\2\2\2\7\u0091\3\2\2\2\t\u0093\3\2\2\2\13\u0096\3\2"+
		"\2\2\r\u0099\3\2\2\2\17\u009e\3\2\2\2\21\u00a0\3\2\2\2\23\u00a2\3\2\2"+
		"\2\25\u00a4\3\2\2\2\27\u00a6\3\2\2\2\31\u00a8\3\2\2\2\33\u00aa\3\2\2\2"+
		"\35\u00ac\3\2\2\2\37\u00ae\3\2\2\2!\u00b0\3\2\2\2#\u00b2\3\2\2\2%\u00b4"+
		"\3\2\2\2\'\u00b6\3\2\2\2)\u00b8\3\2\2\2+\u00ba\3\2\2\2-\u00bc\3\2\2\2"+
		"/\u00be\3\2\2\2\61\u00c0\3\2\2\2\63\u00c2\3\2\2\2\65\u00c4\3\2\2\2\67"+
		"\u00c6\3\2\2\29\u00c8\3\2\2\2;\u00ca\3\2\2\2=\u00cc\3\2\2\2?\u00ce\3\2"+
		"\2\2A\u00d0\3\2\2\2C\u00d2\3\2\2\2E\u00d8\3\2\2\2G\u00df\3\2\2\2I\u00e6"+
		"\3\2\2\2K\u00ed\3\2\2\2M\u00f4\3\2\2\2O\u00f8\3\2\2\2Q\u00ff\3\2\2\2S"+
		"\u0104\3\2\2\2U\u010a\3\2\2\2W\u010e\3\2\2\2Y\u0111\3\2\2\2[\u0115\3\2"+
		"\2\2]\u011b\3\2\2\2_\u011e\3\2\2\2a\u0123\3\2\2\2c\u012a\3\2\2\2e\u0132"+
		"\3\2\2\2g\u0136\3\2\2\2i\u013e\3\2\2\2k\u0146\3\2\2\2m\u014b\3\2\2\2o"+
		"\u014f\3\2\2\2q\u0159\3\2\2\2s\u015e\3\2\2\2u\u0169\3\2\2\2w\u0172\3\2"+
		"\2\2y\u0178\3\2\2\2{\u017a\3\2\2\2}\u017d\3\2\2\2\177\u017f\3\2\2\2\u0081"+
		"\u0182\3\2\2\2\u0083\u0185\3\2\2\2\u0085\u0187\3\2\2\2\u0087\u0189\3\2"+
		"\2\2\u0089\u018b\3\2\2\2\u008b\u018e\3\2\2\2\u008d\u008e\7,\2\2\u008e"+
		"\4\3\2\2\2\u008f\u0090\t\2\2\2\u0090\6\3\2\2\2\u0091\u0092\t\3\2\2\u0092"+
		"\b\3\2\2\2\u0093\u0094\t\3\2\2\u0094\u0095\t\3\2\2\u0095\n\3\2\2\2\u0096"+
		"\u0097\t\3\2\2\u0097\u0098\t\3\2\2\u0098\f\3\2\2\2\u0099\u009a\t\3\2\2"+
		"\u009a\u009b\t\3\2\2\u009b\u009c\t\3\2\2\u009c\u009d\t\3\2\2\u009d\16"+
		"\3\2\2\2\u009e\u009f\t\4\2\2\u009f\20\3\2\2\2\u00a0\u00a1\t\5\2\2\u00a1"+
		"\22\3\2\2\2\u00a2\u00a3\t\6\2\2\u00a3\24\3\2\2\2\u00a4\u00a5\t\7\2\2\u00a5"+
		"\26\3\2\2\2\u00a6\u00a7\t\b\2\2\u00a7\30\3\2\2\2\u00a8\u00a9\t\t\2\2\u00a9"+
		"\32\3\2\2\2\u00aa\u00ab\t\n\2\2\u00ab\34\3\2\2\2\u00ac\u00ad\t\13\2\2"+
		"\u00ad\36\3\2\2\2\u00ae\u00af\t\f\2\2\u00af \3\2\2\2\u00b0\u00b1\t\r\2"+
		"\2\u00b1\"\3\2\2\2\u00b2\u00b3\t\16\2\2\u00b3$\3\2\2\2\u00b4\u00b5\t\17"+
		"\2\2\u00b5&\3\2\2\2\u00b6\u00b7\t\20\2\2\u00b7(\3\2\2\2\u00b8\u00b9\t"+
		"\21\2\2\u00b9*\3\2\2\2\u00ba\u00bb\t\22\2\2\u00bb,\3\2\2\2\u00bc\u00bd"+
		"\t\23\2\2\u00bd.\3\2\2\2\u00be\u00bf\t\24\2\2\u00bf\60\3\2\2\2\u00c0\u00c1"+
		"\t\25\2\2\u00c1\62\3\2\2\2\u00c2\u00c3\t\26\2\2\u00c3\64\3\2\2\2\u00c4"+
		"\u00c5\t\27\2\2\u00c5\66\3\2\2\2\u00c6\u00c7\t\30\2\2\u00c78\3\2\2\2\u00c8"+
		"\u00c9\t\31\2\2\u00c9:\3\2\2\2\u00ca\u00cb\t\32\2\2\u00cb<\3\2\2\2\u00cc"+
		"\u00cd\t\33\2\2\u00cd>\3\2\2\2\u00ce\u00cf\t\34\2\2\u00cf@\3\2\2\2\u00d0"+
		"\u00d1\t\35\2\2\u00d1B\3\2\2\2\u00d2\u00d3\5\65\33\2\u00d3\u00d4\5\17"+
		"\b\2\u00d4\u00d5\5\21\t\2\u00d5\u00d6\5%\23\2\u00d6\u00d7\5\27\f\2\u00d7"+
		"D\3\2\2\2\u00d8\u00d9\5\23\n\2\u00d9\u00da\5\61\31\2\u00da\u00db\5\27"+
		"\f\2\u00db\u00dc\5\17\b\2\u00dc\u00dd\5\65\33\2\u00dd\u00de\5\27\f\2\u00de"+
		"F\3\2\2\2\u00df\u00e0\5\67\34\2\u00e0\u00e1\5-\27\2\u00e1\u00e2\5\25\13"+
		"\2\u00e2\u00e3\5\17\b\2\u00e3\u00e4\5\65\33\2\u00e4\u00e5\5\27\f\2\u00e5"+
		"H\3\2\2\2\u00e6\u00e7\5\37\20\2\u00e7\u00e8\5)\25\2\u00e8\u00e9\5\63\32"+
		"\2\u00e9\u00ea\5\27\f\2\u00ea\u00eb\5\61\31\2\u00eb\u00ec\5\65\33\2\u00ec"+
		"J\3\2\2\2\u00ed\u00ee\5\25\13\2\u00ee\u00ef\5\27\f\2\u00ef\u00f0\5%\23"+
		"\2\u00f0\u00f1\5\27\f\2\u00f1\u00f2\5\65\33\2\u00f2\u00f3\5\27\f\2\u00f3"+
		"L\3\2\2\2\u00f4\u00f5\5\63\32\2\u00f5\u00f6\5\27\f\2\u00f6\u00f7\5\65"+
		"\33\2\u00f7N\3\2\2\2\u00f8\u00f9\5\63\32\2\u00f9\u00fa\5\27\f\2\u00fa"+
		"\u00fb\5%\23\2\u00fb\u00fc\5\27\f\2\u00fc\u00fd\5\23\n\2\u00fd\u00fe\5"+
		"\65\33\2\u00feP\3\2\2\2\u00ff\u0100\5\31\r\2\u0100\u0101\5\61\31\2\u0101"+
		"\u0102\5+\26\2\u0102\u0103\5\'\24\2\u0103R\3\2\2\2\u0104\u0105\5;\36\2"+
		"\u0105\u0106\5\35\17\2\u0106\u0107\5\27\f\2\u0107\u0108\5\61\31\2\u0108"+
		"\u0109\5\27\f\2\u0109T\3\2\2\2\u010a\u010b\5\17\b\2\u010b\u010c\5)\25"+
		"\2\u010c\u010d\5\25\13\2\u010dV\3\2\2\2\u010e\u010f\5+\26\2\u010f\u0110"+
		"\5\61\31\2\u0110X\3\2\2\2\u0111\u0112\5=\37\2\u0112\u0113\5+\26\2\u0113"+
		"\u0114\5\61\31\2\u0114Z\3\2\2\2\u0115\u0116\5\37\20\2\u0116\u0117\5)\25"+
		"\2\u0117\u0118\5\25\13\2\u0118\u0119\5\27\f\2\u0119\u011a\5=\37\2\u011a"+
		"\\\3\2\2\2\u011b\u011c\5+\26\2\u011c\u011d\5)\25\2\u011d^\3\2\2\2\u011e"+
		"\u011f\5\37\20\2\u011f\u0120\5)\25\2\u0120\u0121\5\65\33\2\u0121\u0122"+
		"\5+\26\2\u0122`\3\2\2\2\u0123\u0124\59\35\2\u0124\u0125\5\17\b\2\u0125"+
		"\u0126\5%\23\2\u0126\u0127\5\67\34\2\u0127\u0128\5\27\f\2\u0128\u0129"+
		"\5\63\32\2\u0129b\3\2\2\2\u012a\u012b\5-\27\2\u012b\u012c\5\61\31\2\u012c"+
		"\u012d\5\37\20\2\u012d\u012e\5\'\24\2\u012e\u012f\5\17\b\2\u012f\u0130"+
		"\5\61\31\2\u0130\u0131\5? \2\u0131d\3\2\2\2\u0132\u0133\5\37\20\2\u0133"+
		"\u0134\5)\25\2\u0134\u0135\5\65\33\2\u0135f\3\2\2\2\u0136\u0137\5\25\13"+
		"\2\u0137\u0138\5\27\f\2\u0138\u0139\5\23\n\2\u0139\u013a\5\37\20\2\u013a"+
		"\u013b\5\'\24\2\u013b\u013c\5\17\b\2\u013c\u013d\5%\23\2\u013dh\3\2\2"+
		"\2\u013e\u013f\59\35\2\u013f\u0140\5\17\b\2\u0140\u0141\5\61\31\2\u0141"+
		"\u0142\5\23\n\2\u0142\u0143\5\35\17\2\u0143\u0144\5\17\b\2\u0144\u0145"+
		"\5\61\31\2\u0145j\3\2\2\2\u0146\u0147\5\25\13\2\u0147\u0148\5\17\b\2\u0148"+
		"\u0149\5\65\33\2\u0149\u014a\5\27\f\2\u014al\3\2\2\2\u014b\u014c\5#\22"+
		"\2\u014c\u014d\5\27\f\2\u014d\u014e\5? \2\u014en\3\2\2\2\u014f\u0155\5"+
		"\5\3\2\u0150\u0154\5\5\3\2\u0151\u0154\5\7\4\2\u0152\u0154\7a\2\2\u0153"+
		"\u0150\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2"+
		"\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156p\3\2\2\2\u0157\u0155"+
		"\3\2\2\2\u0158\u015a\5\7\4\2\u0159\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b"+
		"\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015cr\3\2\2\2\u015d\u015f\5\7\4\2"+
		"\u015e\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161"+
		"\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0166\7\60\2\2\u0163\u0165\5\7\4\2"+
		"\u0164\u0163\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167t\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u016d\7)\2\2\u016a\u016c"+
		"\n\36\2\2\u016b\u016a\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2"+
		"\u016d\u016e\3\2\2\2\u016e\u0170\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0171"+
		"\7)\2\2\u0171v\3\2\2\2\u0172\u0173\5\r\7\2\u0173\u0174\7/\2\2\u0174\u0175"+
		"\5\13\6\2\u0175\u0176\7/\2\2\u0176\u0177\5\t\5\2\u0177x\3\2\2\2\u0178"+
		"\u0179\7@\2\2\u0179z\3\2\2\2\u017a\u017b\7@\2\2\u017b\u017c\7?\2\2\u017c"+
		"|\3\2\2\2\u017d\u017e\7>\2\2\u017e~\3\2\2\2\u017f\u0180\7>\2\2\u0180\u0181"+
		"\7?\2\2\u0181\u0080\3\2\2\2\u0182\u0183\7#\2\2\u0183\u0184\7?\2\2\u0184"+
		"\u0082\3\2\2\2\u0185\u0186\7?\2\2\u0186\u0084\3\2\2\2\u0187\u0188\7*\2"+
		"\2\u0188\u0086\3\2\2\2\u0189\u018a\7+\2\2\u018a\u0088\3\2\2\2\u018b\u018c"+
		"\7.\2\2\u018c\u008a\3\2\2\2\u018d\u018f\t\37\2\2\u018e\u018d\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0192\3\2"+
		"\2\2\u0192\u0193\bF\2\2\u0193\u008c\3\2\2\2\n\2\u0153\u0155\u015b\u0160"+
		"\u0166\u016d\u0190\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}