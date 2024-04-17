package exceptions;

public class ConversionException extends RuntimeException {
	private String message;

	public ConversionException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
