package net.rotgruengelb.maple.exception;

public interface PositionalException {

	/**
	 * @return The position where the exception occurred.
	 */
	ExceptionPosition position();
}
