package net.rotgruengelb.maple.internal;

import net.rotgruengelb.maple.internal.generated.MapleLexer;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.Stream;

public enum MapleTokenName {
	BRACE_OPEN("{", MapleLexer.BRACE_OPEN),
	BRACE_CLOSE("}", MapleLexer.BRACE_CLOSE),
	BRACKET_OPEN("[", MapleLexer.BRACKET_OPEN),
	BRACKET_CLOSE("]", MapleLexer.BRACKET_CLOSE),
	COMMA("a comma", MapleLexer.COMMA),
	EQUALS("=", MapleLexer.EQUALS),
	STRING("a string", MapleLexer.STRING),
	NUMBER("a number", MapleLexer.NUMBER),
	BOOLEAN("a boolean", MapleLexer.TRUE, MapleLexer.FALSE),
	KEY("a key", MapleLexer.KEY),
	EOF("<EOF>", MapleLexer.EOF),
	HIDDEN("§hiddenToken§", 0, MapleLexer.WS, MapleLexer.COMMENT, MapleLexer.ML_COMMENT);

	private final String displayName;
	@SuppressWarnings("ImmutableEnumChecker") private final BitSet tokenTypes;

	MapleTokenName(String displayName, int... tokenTypes) {
		this.displayName = displayName;
		this.tokenTypes = new BitSet(MapleLexer.VOCABULARY.getMaxTokenType() + 1);
		for (int type : tokenTypes) {
			this.tokenTypes.set(type + 1);
		}
	}

	public static Stream<MapleTokenName> namesForToken(int tokenType) {
		return Arrays.stream(MapleTokenName.values())
				.filter(n -> n.tokenTypes.get(tokenType + 1));
	}

	public String displayName() {
		return displayName;
	}
}
