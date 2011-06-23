package utilities.exceptions;
/**
 * NodeException is intended to use with any node class.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 29.05.2011
 */
public class NodeException extends EigeneExceptions {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 */
	public NodeException() {
		super();
	}

	/**
	 * Erstelle eine Exception mit einer vorgegebenen Nachricht.
	 * 
	 * @param message
	 */
	public NodeException(String message) {
		super(message);
	}
}
