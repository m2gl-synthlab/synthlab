package fr.istic.synthlab.abstraction.exception;

/**
 * Exception that is thrown when trying to connect a port that is already in use
 */
public class PortAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 4581175422388020683L;

	public PortAlreadyInUseException(String message) {
		super(message);
	}

}
