package net.rotgruengelb.maple.exception;

/**
 * An exception that occurred while parsing.
 */
public final class MapleParseException extends RuntimeException implements PositionalException {

	private final ExceptionPosition position;

	public MapleParseException(String message, ExceptionPosition position) {
		super("MapleParseException: " + message);
		this.position = position;
	}

	/**
	 * @return The position where the exception occurred.
	 */
	public ExceptionPosition position() {
		return position;
	}

	@Override
	public String toString() {
		return getMessage();
	}
}
