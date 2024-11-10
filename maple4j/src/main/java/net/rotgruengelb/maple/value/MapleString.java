package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a {@code String} value in the Maple configuration language.
 *
 * <p>This class provides methods to set and retrieve a {@code String} value
 * and determines its type as {@link Type#STRING}.</p>
 */
public class MapleString implements MapleValue<String> {

	private final String string;

	/**
	 * Constructs a new {@code MapleString} with the specified value.
	 *
	 * @param string the string value to be set
	 */
	public MapleString(String string) {
		this.string = string;
	}

	/**
	 * Returns the string representation of this {@code MapleString}.
	 *
	 * @return the string value
	 */
	@Override
	public String toString() {
		return string;
	}

	/**
	 * Retrieves the current {@code String} value.
	 *
	 * @return the current value, never {@code null}
	 */
	@Override
	@NotNull
	public String value() {
		return string;
	}

	/**
	 * Returns the type of this value, which is always {@link Type#STRING}.
	 *
	 * @return the type {@link Type#STRING}
	 */
	@Override
	public @NotNull Type type() {
		return Type.STRING;
	}
}
