package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a map of key-value pairs in the Maple configuration language.
 *
 * <p>This class provides methods to access the underlying map, as well as to retrieve elements
 * based on their type (e.g., {@code FLOAT}, {@code STRING}, {@code MAP}).
 * This class implements {@link MapleValue} and {@link MapleDeep}. The keys are strings, and the
 * values are instances of {@link MapleValue}.</p>
 */
public class MapleMap implements MapleValue<Map<String, MapleValue<?>>>, MapleDeep<String> {

	private final Map<String, MapleValue<?>> map;

	/**
	 * Constructs a new `MapleMap` with the specified map.
	 *
	 * @param map the map of key-value pairs
	 */
	public MapleMap(Map<String, MapleValue<?>> map) {
		this.map = map;
	}

	/**
	 * Constructs a new `MapleMap` with an empty map.
	 */
	public MapleMap() {
		this(new HashMap<>());
	}

	/**
	 * Returns a string representation of the map.
	 *
	 * @return a string representation of the map
	 */
	@Override
	public String toString() {
		return map.toString();
	}

	/**
	 * Retrieves the value associated with the specified key as a {@link MapleValue}, regardless of its type.
	 *
	 * @param key the key for the value
	 * @return the value as a {@link MapleValue}, or {@link MapleNull} if no value is associated with the key
	 */
	@Override
	@NotNull
	public MapleValue<?> get(@NotNull String key) {
		return map.getOrDefault(key, new MapleNull());
	}

	@Override
	public int size() {
		return map.size();
	}

	/**
	 * Retrieves the current map of key-value pairs.
	 *
	 * @return the map of key-value pairs
	 */
	@Override
	@NotNull
	public Map<String, MapleValue<?>> value() {
		return map;
	}

	/**
	 * Returns the type of this value, which is always {@link MapleValue.Type#MAP}.
	 *
	 * @return the type of this value
	 */
	@Override
	@NotNull
	public Type type() {
		return Type.MAP;
	}
}
