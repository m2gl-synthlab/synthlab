package fr.istic.synthlab.abstraction.exception;

/**
 * Exception that is thrown when trying to connect a wire to the same port type
 */
public class BadConnectionException extends Exception {

	private static final long serialVersionUID = 6431855748115238516L;

	public BadConnectionException(String message) {
		super(message);
	}

}
