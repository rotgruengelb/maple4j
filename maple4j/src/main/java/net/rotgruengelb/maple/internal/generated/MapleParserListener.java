// Generated from MapleParser.g4 by ANTLR 4.13.2

package net.rotgruengelb.maple.internal.generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MapleParser}.
 */
public interface MapleParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MapleParser#maple}.
	 * @param ctx the parse tree
	 */
	void enterMaple(MapleParser.MapleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapleParser#maple}.
	 * @param ctx the parse tree
	 */
	void exitMaple(MapleParser.MapleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapleParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(MapleParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapleParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(MapleParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapleParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(MapleParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapleParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(MapleParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapleParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(MapleParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapleParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(MapleParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapleParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(MapleParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapleParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(MapleParser.ListContext ctx);
}