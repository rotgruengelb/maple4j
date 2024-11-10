package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of {@link MapleValue} elements in the Maple configuration language.
 *
 * <p>This class provides methods to access the underlying list, as well as to retrieve elements
 * based on their type (e.g., {@code FLOAT}, {@code STRING}, {@code MAP}). It implements both
 * {@link MapleValue} and {@link MapleDeep} interfaces.</p>
 */
public class MapleList implements MapleValue<ArrayList<MapleValue<?>>>, MapleDeep<Integer>, Iterable<MapleValue<?>> {

	/**
	 * The underlying list of {@link MapleValue} elements.
	 */
	private final ArrayList<MapleValue<?>> list;

	/**
	 * Constructs a new MapleList with the specified list of {@link MapleValue} elements.
	 *
	 * @param list the list of {@link MapleValue} elements
	 */
	public MapleList(ArrayList<MapleValue<?>> list) {
		this.list = list;
	}

	/**
	 * Constructs a new, empty MapleList.
	 */
	public MapleList() {
		this(new ArrayList<>());
	}

	@Override
	public String toString() {
		return list.toString();
	}

	/**
	 * Retrieves the element at the specified index as a {@link MapleValue}, regardless of its type.
	 *
	 * @param key the index of the element
	 * @return the element as a {@link MapleValue}, or {@link MapleNull} if no element exists at the index
	 */
	@Override
	@NotNull
	public MapleValue<?> get(@NotNull Integer key) {
		try {
			MapleValue<?> value = list.get(key);
			return value != null ? value : MapleNull.NULL;
		} catch (IndexOutOfBoundsException e) {
			return MapleNull.NULL;
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Retrieves the current list of {@link MapleValue} elements.
	 *
	 * @return the list of elements
	 */
	@Override
	@NotNull
	public ArrayList<MapleValue<?>> value() {
		return list;
	}

	/**
	 * Returns the type of this value, which is always {@link MapleValue.Type#LIST}.
	 *
	 * @return the type of this value
	 */
	@Override
	@NotNull
	public Type type() {
		return Type.LIST;
	}

	@Override
	public @NotNull Iterator<MapleValue<?>> iterator() {
		return list.iterator();
	}
}
