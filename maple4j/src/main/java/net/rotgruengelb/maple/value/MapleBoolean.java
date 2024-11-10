package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a {@code Boolean} value within the Maple configuration language.
 *
 * <p>This class encapsulates a {@code Boolean} value and provides methods to retrieve it as well as
 * its associated {@link Type#BOOLEAN}.</p>
 */
public class MapleBoolean implements MapleValue<Boolean> {

	/**
	 * The boolean value represented by this {@code MapleBoolean}.
	 */
	private final Boolean value;

	/**
	 * Constructs a {@code MapleBoolean} with the specified boolean value.
	 *
	 * @param b the initial boolean value
	 */
	public MapleBoolean(boolean b) {
		value = b;
	}

	/**
	 * Returns a string representation of the boolean value.
	 *
	 * @return a string representation of the current value
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * Retrieves the current {@code Boolean} value.
	 *
	 * @return the current {@code Boolean} value
	 */
	@Override
	@NotNull
	public Boolean value() {
		return value;
	}

	/**
	 * Returns the type of this value, which is {@link Type#BOOLEAN}.
	 *
	 * @return the type {@link Type#BOOLEAN}
	 */
	@Override
	public @NotNull Type type() {
		return Type.BOOLEAN;
	}
}
