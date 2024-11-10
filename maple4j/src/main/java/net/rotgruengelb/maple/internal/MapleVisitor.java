package net.rotgruengelb.maple.internal;

import net.rotgruengelb.maple.internal.generated.MapleParser;
import net.rotgruengelb.maple.internal.generated.MapleParserBaseVisitor;
import net.rotgruengelb.maple.value.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class MapleVisitor extends MapleParserBaseVisitor<MapleValue<?>> {

	@Override
	public MapleValue<?> visitMaple(MapleParser.MapleContext ctx) {
		return visitMapLike(ctx.pair());
	}

	@Override
	public MapleValue<?> visitMap(MapleParser.MapContext ctx) {
		return visitMapLike(ctx.pair());
	}

	@NotNull
	private MapleValue<?> visitMapLike(List<MapleParser.PairContext> pairs) {
		HashMap<String, MapleValue<?>> map = new HashMap<>();
		for (MapleParser.PairContext pairCtx : pairs) {
			String key = pairCtx.KEY().getText();
			MapleValue<?> value = pairCtx.value().accept(this);
			map.put(key, value);
		}
		return new MapleMap(map);
	}

	@Override
	public MapleValue<?> visitValue(MapleParser.ValueContext ctx) {
		if (ctx.STRING() != null) {
			return handelString(ctx.STRING().getText());
		} else if (ctx.NUMBER() != null) {
			return handelNumber(ctx.NUMBER().getText());
		} else if (ctx.TRUE() != null) {
			return new MapleBoolean(true);
		} else if (ctx.FALSE() != null) {
			return new MapleBoolean(false);
		} else if (ctx.list() != null) {
			return ctx.list().accept(this);
		} else if (ctx.map() != null) {
			return ctx.map().accept(this);
		} else {
			throw new IllegalStateException("Unknown value type: " + ctx.getText());
		}
	}

	private MapleValue<?> handelString(String text) {
		return new MapleString(text.substring(1, text.length() - 1));
	}

	private MapleValue<?> handelNumber(String text) {
		if (text.contains(".")) {
			return new MapleFloat(Float.parseFloat(text));
		} else {
			return new MapleInt(Integer.parseInt(text));
		}
	}

	@Override
	public MapleValue<?> visitList(MapleParser.ListContext ctx) {
		ArrayList<MapleValue<?>> list = new ArrayList<>();
		for (MapleParser.ValueContext valueCtx : ctx.value()) {
			list.add(valueCtx.accept(this));
		}
		return new MapleList(list);
	}
}