package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

/**
 * Represents a value in the Maple key-value configuration language.
 *
 * <p>This interface provides methods to set, retrieve, and determine the type of a value, as well as
 * to conditionally retrieve the value based on its type.</p>
 *
 * @param <T> the type of the value
 */
public interface MapleValue<T> {

	/**
	 * Retrieves the current value.
	 *
	 * @return the value, or {@code null} if no value is set
	 */
	@Contract(pure = true)
	@UnknownNullability
	T value();

	@Contract(pure = true)
	@UnknownNullability
	default T v() {
		return value();
	}

	/**
	 * Retrieves the value if its type matches the specified {@link Type}.
	 *
	 * @param type the expected type
	 * @return the value if the type matches, or {@code null} if it does not match
	 */
	@Contract(pure = true)
	@Nullable
	default T getIf(@NotNull Type type) {
		if (type == type()) {
			return value();
		}
		return null;
	}

	@Contract(pure = true)
	@Nullable
	default MapleValue<T> getSelfIf(@NotNull Type type) {
		if (type == type()) {
			return this;
		}
		return null;
	}

	/**
	 * Returns the type of the current value.
	 *
	 * @return the {@link Type} of the value
	 */
	@Contract(pure = true)
	@NotNull Type type();

	/**
	 * Enum representing the possible data types of values in Maple and {@code NULL} for missing values.
	 *
	 * <p>This includes basic types such as {@code STRING}, {@code INT}, {@code FLOAT}, and more
	 * complex types like {@code MAP} and {@code LIST}.</p>
	 *
	 * @see MapleString
	 * @see MapleInt
	 * @see MapleFloat
	 * @see MapleBoolean
	 * @see MapleMap
	 * @see MapleList
	 * @see MapleNull
	 */
	enum Type {
		STRING,
		INT,
		FLOAT,
		BOOLEAN,
		MAP,
		LIST,
		NULL
	}
}
