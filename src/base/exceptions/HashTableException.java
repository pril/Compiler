package base.exceptions;

import utilities.exceptions.EigeneExceptions;
/**
 * HashTableException tritt bei Fehlern in der HashTable auf.
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 22.06.2011
 */
public class HashTableException extends EigeneExceptions{
	
	public HashTableException(String message)
	{
		super(message);
	}
	public HashTableException()
	{
		super();
	}
}
