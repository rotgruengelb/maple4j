package net.rotgruengelb.maple.exception;

public record ExceptionPosition(int line,
								int column) {

	@Override
	public String toString() {
		return "line " + line + ", column " + column;
	}
}
