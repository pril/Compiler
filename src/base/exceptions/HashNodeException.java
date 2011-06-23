package base.exceptions;

import utilities.exceptions.EigeneExceptions;
/**
 * HashNodeException tritt bei Fehlern in der HashNode auf.
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 22.06.2011
 */
public class HashNodeException extends EigeneExceptions{
	
	public HashNodeException(String message)
	{
		super(message);
	}
	public HashNodeException()
	{
		super();
	}
}
