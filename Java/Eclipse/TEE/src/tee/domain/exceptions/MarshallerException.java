package tee.domain.exceptions;

public class MarshallerException extends Exception {

	private static final long serialVersionUID = -5632412122583838468L;

	public MarshallerException() {
	}

	public MarshallerException(String message) {
		super(message);
	}

	public MarshallerException(Exception exception) {
		super(exception);
	}

	public MarshallerException(String message, Exception exception) {
		super(message, exception);
	}

}
