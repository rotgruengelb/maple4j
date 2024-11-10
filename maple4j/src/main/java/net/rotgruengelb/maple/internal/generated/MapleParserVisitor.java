// Generated from MapleParser.g4 by ANTLR 4.13.2

package net.rotgruengelb.maple.internal.generated;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MapleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MapleParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MapleParser#maple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaple(MapleParser.MapleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapleParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(MapleParser.MapContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapleParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(MapleParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapleParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(MapleParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapleParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(MapleParser.ListContext ctx);
}