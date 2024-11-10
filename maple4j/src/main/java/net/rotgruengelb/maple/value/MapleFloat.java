package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a {@code Float} value within the Maple configuration language.
 *
 * <p>This class encapsulates a {@code Float} value and provides methods to retrieve it as well as
 * its associated {@link Type#BOOLEAN}.</p>
 */
public class MapleFloat implements MapleValue<Float> {

	private final Float value;

	/**
	 * Constructs a new {@code MapleFloat} with the specified value.
	 *
	 * @param v the float value to be set
	 */
	public MapleFloat(float v) {
		value = v;
	}

	/**
	 * Returns a string representation of the {@code MapleFloat} value.
	 *
	 * @return a string representation of the value
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * Retrieves the current {@code Float} value.
	 *
	 * @return the current value, or {@code null} if not set
	 */
	@Override
	@NotNull
	public Float value() {
		return value;
	}

	/**
	 * Returns the type of this value, which is always {@link Type#FLOAT}.
	 *
	 * @return the type {@link Type#FLOAT}
	 */
	@Override
	public @NotNull Type type() {
		return Type.FLOAT;
	}
}
