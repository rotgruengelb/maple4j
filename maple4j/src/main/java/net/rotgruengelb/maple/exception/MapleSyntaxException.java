package net.rotgruengelb.maple.exception;

/**
 * An exception that occurred during lexical analysis.
 */
public final class MapleSyntaxException extends RuntimeException implements PositionalException {

	private final ExceptionPosition position;

	public MapleSyntaxException(String message, ExceptionPosition position) {
		super("MapleSyntaxException: " + message);
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
