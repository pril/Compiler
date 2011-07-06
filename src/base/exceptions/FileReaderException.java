package base.exceptions;

import utilities.exceptions.EigeneExceptions;
/**
 * Wird durch den FileReader geworfen.
 * @author Alexei Felberg
 * @author Daniel
 * @version 05.07.2011 20:54:57
 */
public class FileReaderException extends EigeneExceptions{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * FileReaderException-Konstruktor ohne Nachricht.
	 * 
	 */
	public FileReaderException(){
		super();
	}
	
	/**
	 * FileReaderException-Konstruktor mit einer Nachricht vorbelegen.
	 * @param message
	 */
	public FileReaderException(String message)
	{
		super(message);
	}
	
}
