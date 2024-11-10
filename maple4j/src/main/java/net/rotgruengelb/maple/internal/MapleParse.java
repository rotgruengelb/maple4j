package net.rotgruengelb.maple.internal;

import net.rotgruengelb.maple.internal.generated.MapleLexer;
import net.rotgruengelb.maple.internal.generated.MapleParser;
import net.rotgruengelb.maple.value.MapleMap;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class MapleParse {

	public static MapleMap parse(CharStream stream, boolean ignoreLexerErrors) {
		MapleLexer lexer = new MapleLexer(stream);
		lexer.removeErrorListeners();
		if (!ignoreLexerErrors) {
			lexer.addErrorListener(new MapleErrorListener());
		}
		MapleParser parser = new MapleParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(new MapleErrorListener());
		return (MapleMap) parser.maple()
				.accept(new MapleVisitor());
	}

	public static MapleMap parse(CharStream stream) {
		return parse(stream, false);
	}
}
