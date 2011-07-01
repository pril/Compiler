package base.exceptions;

import utilities.exceptions.EigeneExceptions;
/**
 * HashNodeException tritt bei Fehlern in der HashNode auf.
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 22.06.2011
 */
public class HashItemException extends EigeneExceptions{
	
	public HashItemException(String message)
	{
		super(message);
	}
	public HashItemException()
	{
		super();
	}
}
