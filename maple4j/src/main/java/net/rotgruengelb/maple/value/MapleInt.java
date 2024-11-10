package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an {@code Integer} value in the Maple configuration language.
 *
 * <p>This class provides methods to set and retrieve an {@code Integer} value
 * and determines its type as {@link Type#INT}.</p>
 */
public class MapleInt implements MapleValue<Integer> {

	private final Integer value;

	/**
	 * Constructs a new {@code MapleInt} with the specified integer value.
	 *
	 * @param i the integer value to be set
	 */
	public MapleInt(int i) {
		value = i;
	}

	/**
	 * Returns a string representation of the {@code MapleInt} value.
	 *
	 * @return a string representation of the value
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * Retrieves the current {@code Integer} value.
	 *
	 * @return the current value, or {@code null} if not set
	 */
	@Override
	@NotNull
	public Integer value() {
		return value;
	}

	/**
	 * Returns the type of this value, which is always {@link Type#INT}.
	 *
	 * @return the type {@link Type#INT}
	 */
	@Override
	public @NotNull Type type() {
		return Type.INT;
	}
}
