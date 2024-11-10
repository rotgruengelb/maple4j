// Generated from MapleLexer.g4 by ANTLR 4.13.2

package net.rotgruengelb.maple.internal.generated;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MapleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BRACE_OPEN=1, BRACE_CLOSE=2, BRACKET_OPEN=3, BRACKET_CLOSE=4, COMMA=5, 
		EQUALS=6, STRING=7, NUMBER=8, TRUE=9, FALSE=10, KEY=11, WS=12, SEMICOLON=13, 
		COMMENT=14, ML_COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BRACE_OPEN", "BRACE_CLOSE", "BRACKET_OPEN", "BRACKET_CLOSE", "COMMA", 
			"EQUALS", "STRING", "NUMBER", "TRUE", "FALSE", "KEY", "WS", "SEMICOLON", 
			"COMMENT", "ML_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'['", "']'", "','", "'='", null, null, "'true'", 
			"'false'", null, null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BRACE_OPEN", "BRACE_CLOSE", "BRACKET_OPEN", "BRACKET_CLOSE", "COMMA", 
			"EQUALS", "STRING", "NUMBER", "TRUE", "FALSE", "KEY", "WS", "SEMICOLON", 
			"COMMENT", "ML_COMMENT"
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


	public MapleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MapleLexer.g4"; }

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
		"\u0004\u0000\u000f|\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u00060\b\u0006\n\u0006"+
		"\f\u00063\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u00078\b\u0007"+
		"\u0001\u0007\u0004\u0007;\b\u0007\u000b\u0007\f\u0007<\u0001\u0007\u0001"+
		"\u0007\u0004\u0007A\b\u0007\u000b\u0007\f\u0007B\u0003\u0007E\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005\nT\b\n\n\n\f\nW\t\n\u0001\u000b"+
		"\u0004\u000bZ\b\u000b\u000b\u000b\f\u000b[\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\rh\b"+
		"\r\n\r\f\rk\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000es\b\u000e\n\u000e\f\u000ev\t\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001t\u0000\u000f\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u0001\u0000\u0006\u0002\u0000\'\'\\\\\u0001\u000009\u0003\u0000AZ__a"+
		"z\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r\r\u0085"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0003!\u0001"+
		"\u0000\u0000\u0000\u0005#\u0001\u0000\u0000\u0000\u0007%\u0001\u0000\u0000"+
		"\u0000\t\'\u0001\u0000\u0000\u0000\u000b)\u0001\u0000\u0000\u0000\r+\u0001"+
		"\u0000\u0000\u0000\u000f7\u0001\u0000\u0000\u0000\u0011F\u0001\u0000\u0000"+
		"\u0000\u0013K\u0001\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017"+
		"Y\u0001\u0000\u0000\u0000\u0019_\u0001\u0000\u0000\u0000\u001bc\u0001"+
		"\u0000\u0000\u0000\u001dn\u0001\u0000\u0000\u0000\u001f \u0005{\u0000"+
		"\u0000 \u0002\u0001\u0000\u0000\u0000!\"\u0005}\u0000\u0000\"\u0004\u0001"+
		"\u0000\u0000\u0000#$\u0005[\u0000\u0000$\u0006\u0001\u0000\u0000\u0000"+
		"%&\u0005]\u0000\u0000&\b\u0001\u0000\u0000\u0000\'(\u0005,\u0000\u0000"+
		"(\n\u0001\u0000\u0000\u0000)*\u0005=\u0000\u0000*\f\u0001\u0000\u0000"+
		"\u0000+1\u0005\'\u0000\u0000,0\b\u0000\u0000\u0000-.\u0005\\\u0000\u0000"+
		".0\u0005\'\u0000\u0000/,\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000"+
		"03\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u000012\u0001\u0000\u0000"+
		"\u000024\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000045\u0005\'\u0000"+
		"\u00005\u000e\u0001\u0000\u0000\u000068\u0005-\u0000\u000076\u0001\u0000"+
		"\u0000\u000078\u0001\u0000\u0000\u00008:\u0001\u0000\u0000\u00009;\u0007"+
		"\u0001\u0000\u0000:9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000"+
		"<:\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=D\u0001\u0000\u0000"+
		"\u0000>@\u0005.\u0000\u0000?A\u0007\u0001\u0000\u0000@?\u0001\u0000\u0000"+
		"\u0000AB\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000CE\u0001\u0000\u0000\u0000D>\u0001\u0000\u0000\u0000DE\u0001"+
		"\u0000\u0000\u0000E\u0010\u0001\u0000\u0000\u0000FG\u0005t\u0000\u0000"+
		"GH\u0005r\u0000\u0000HI\u0005u\u0000\u0000IJ\u0005e\u0000\u0000J\u0012"+
		"\u0001\u0000\u0000\u0000KL\u0005f\u0000\u0000LM\u0005a\u0000\u0000MN\u0005"+
		"l\u0000\u0000NO\u0005s\u0000\u0000OP\u0005e\u0000\u0000P\u0014\u0001\u0000"+
		"\u0000\u0000QU\u0007\u0002\u0000\u0000RT\u0007\u0003\u0000\u0000SR\u0001"+
		"\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000"+
		"UV\u0001\u0000\u0000\u0000V\u0016\u0001\u0000\u0000\u0000WU\u0001\u0000"+
		"\u0000\u0000XZ\u0007\u0004\u0000\u0000YX\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000"+
		"\\]\u0001\u0000\u0000\u0000]^\u0006\u000b\u0000\u0000^\u0018\u0001\u0000"+
		"\u0000\u0000_`\u0005;\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0006\f"+
		"\u0000\u0000b\u001a\u0001\u0000\u0000\u0000cd\u0005/\u0000\u0000de\u0005"+
		"/\u0000\u0000ei\u0001\u0000\u0000\u0000fh\b\u0005\u0000\u0000gf\u0001"+
		"\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000jl\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000"+
		"\u0000lm\u0006\r\u0000\u0000m\u001c\u0001\u0000\u0000\u0000no\u0005/\u0000"+
		"\u0000op\u0005*\u0000\u0000pt\u0001\u0000\u0000\u0000qs\t\u0000\u0000"+
		"\u0000rq\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000tr\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000vt\u0001"+
		"\u0000\u0000\u0000wx\u0005*\u0000\u0000xy\u0005/\u0000\u0000yz\u0001\u0000"+
		"\u0000\u0000z{\u0006\u000e\u0000\u0000{\u001e\u0001\u0000\u0000\u0000"+
		"\u000b\u0000/17<BDU[it\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}