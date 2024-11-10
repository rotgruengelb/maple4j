package net.rotgruengelb.maple.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

/**
 * Interface for retrieving values from deeply nested structures within the Maple configuration language.
 *
 * <p>This interface provides methods to retrieve values by a key of type {@code K} and cast them into specific
 * types such as {@code Float}, {@code Integer}, {@code String}, {@code Boolean}, {@link MapleMap}, or {@link MapleList}.
 * If the type does not match the requested type, these methods return {@code null}.
 * Default values can also be supplied, avoiding {@code null} checks in the code.</p>
 *
 * @param <K> the type of the key used to retrieve values
 */
public interface MapleDeep<K> {

	/**
	 * Retrieves the value associated with the specified key as a {@code Float}.
	 *
	 * @param key the key to look up the value
	 * @return the value as a {@code Float}, or {@code null} if the type is not {@code FLOAT} or no value is associated
	 */
	default @Nullable Float getFloat(@NotNull K key) {
		return (Float) get(key).getIf(MapleValue.Type.FLOAT);
	}

	/**
	 * Retrieves the value associated with the specified key as a {@code Float}, or returns the provided default value if the key is not found or the type does not match.
	 *
	 * @param key          the key to look up the value
	 * @param defaultValue the default value to return if the lookup fails
	 * @return the value as a {@code float}, or {@code defaultValue} if the type is not {@code FLOAT} or no value is associated
	 */
	default float getFloat(@NotNull K key, float defaultValue) {
		Float value = getFloat(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Retrieves the value associated with the specified key as an {@code Integer}.
	 *
	 * @param key the key to look up the value
	 * @return the value as an {@code Integer}, or {@code null} if the type is not {@code INT} or no value is associated
	 */
	default @Nullable Integer getInt(@NotNull K key) {
		return (Integer) get(key).getIf(MapleValue.Type.INT);
	}

	/**
	 * Retrieves the value associated with the specified key as an {@code Integer}, or returns the provided default value if the key is not found or the type does not match.
	 *
	 * @param key          the key to look up the value
	 * @param defaultValue the default value to return if the lookup fails
	 * @return the value as an {@code int}, or {@code defaultValue} if the type is not {@code INT} or no value is associated
	 */
	default int getInt(@NotNull K key, int defaultValue) {
		Integer value = getInt(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Retrieves the value associated with the specified key as a {@code String}.
	 *
	 * @param key the key to look up the value
	 * @return the value as a {@code String}, or {@code null} if the type is not {@code STRING} or no value is associated
	 */
	default @Nullable String getString(@NotNull K key) {
		return (String) get(key).getIf(MapleValue.Type.STRING);
	}

	/**
	 * Retrieves the value associated with the specified key as a {@code String}, or returns the provided default value if the key is not found or the type does not match.
	 *
	 * @param key          the key to look up the value
	 * @param defaultValue the default value to return if the lookup fails
	 * @return the value as a {@code String}, or {@code defaultValue} if the type is not {@code STRING} or no value is associated
	 */
	default String getString(@NotNull K key, @NotNull String defaultValue) {
		String value = getString(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Retrieves the value associated with the specified key as a {@code Boolean}.
	 *
	 * @param key the key to look up the value
	 * @return the value as a {@code Boolean}, or {@code null} if the type is not {@code BOOLEAN} or no value is associated
	 */
	default @Nullable Boolean getBoolean(@NotNull K key) {
		return (Boolean) get(key).getIf(MapleValue.Type.BOOLEAN);
	}

	/**
	 * Retrieves the value associated with the specified key as a {@code Boolean}, or returns the provided default value if the key is not found or the type does not match.
	 *
	 * @param key          the key to look up the value
	 * @param defaultValue the default value to return if the lookup fails
	 * @return the value as a {@code boolean}, or {@code defaultValue} if the type is not {@code BOOLEAN} or no value is associated
	 */
	default boolean getBoolean(@NotNull K key, boolean defaultValue) {
		Boolean value = getBoolean(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Retrieves the value associated with the specified key as a {@link MapleMap}.
	 *
	 * @param key the key to look up the value
	 * @return the value as a {@link MapleMap}, or {@code null} if the type is not {@code MAP} or no value is associated
	 */
	default @Nullable MapleMap getMap(@NotNull K key) {
		return getMap(key, true);
	}

	default @UnknownNullability MapleMap getMap(@NotNull K key, boolean emptyIfNull) {
		MapleMap value = (MapleMap) get(key).getSelfIf(MapleValue.Type.MAP);
		return value != null ? value : (emptyIfNull ? new MapleMap() : null);
	}

	/**
	 * Retrieves the value associated with the specified key as a {@link MapleList}.
	 *
	 * @param key the key to look up the value
	 * @return the value as a {@link MapleList}, or {@code null} if the type is not {@code LIST} or no value is associated
	 */
	default @Nullable MapleList getList(@NotNull K key) {
		return getList(key, true);
	}

	default @UnknownNullability MapleList getList(@NotNull K key, boolean emptyIfNull) {
		MapleList value = (MapleList) get(key).getSelfIf(MapleValue.Type.LIST);
		return value != null ? value : (emptyIfNull ? new MapleList() : null);
	}

	/**
	 * Retrieves the value associated with the specified key as a {@link MapleValue}, regardless of its type.
	 *
	 * @param key the key to look up the value
	 * @return the value as a {@link MapleValue}, or {@link MapleNull} if no value is associated with the key
	 */
	@NotNull MapleValue<?> get(@NotNull K key);

	int size();
}
