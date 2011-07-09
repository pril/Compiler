package base.exceptions;

import utilities.exceptions.EigeneExceptions;

/**
 * Exceptions die beim Compiler Auftreten koennen.
 * @author Alexei Felberg
 * @author Daniel
 * @version 09.07.2011 08:08:09
 */
public class CompilerExceptions extends EigeneExceptions{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompilerExceptions()
	{
		super();
	}
	
	public CompilerExceptions(String message)
	{
		super(message);
	}
}
