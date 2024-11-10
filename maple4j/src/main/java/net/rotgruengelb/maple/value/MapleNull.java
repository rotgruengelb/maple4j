package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a null value in the Maple configuration language.
 *
 * <p>This class always returns {@code null} for its value and has a type of {@link Type#NULL}.</p>
 */
public class MapleNull implements MapleValue<Object> {

	/**
	 * The singleton instance of this class.
	 */
	public static final MapleNull NULL = new MapleNull();

	@Override
	public String toString() {
		return "MapleNull.NULL";
	}

	/**
	 * Always returns {@code null} as the value is not set.
	 *
	 * @return {@code null}
	 */
	@Override
	@Nullable
	public Object value() {
		return null;
	}

	/**
	 * Returns the type of this value, which is always {@link MapleValue.Type#NULL}.
	 *
	 * @return the type of this value
	 */
	@Override
	@NotNull
	public Type type() {
		return Type.NULL;
	}
}
