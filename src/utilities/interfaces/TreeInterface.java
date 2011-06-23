package utilities.interfaces;

import utilities.exceptions.NodeException;
import utilities.exceptions.TreeException;


/**
 * Die wichtigsten Methoden eines Baumes.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 31.05.2011
 */
public interface TreeInterface {
	/**
	 * Einfuegen eines Elements in den Baum
	 * 
	 * @param object
	 */
	public void insert(Object key, Object object) throws TreeException, NodeException;

	/**
	 * Loescht ein Element aus dem Tree.
	 * 
	 * @param object zu loeschendes Objekt.
	 * @throws TreeException Object nicht den Anforderungen entspricht oder der Baum leer ist.
	 */
	public boolean delete(Object key) throws TreeException;

	/**
	 * Liefert die Anzahl der Elemente im Baum.
	 * @return Anzahl der Elemente im Baum.
	 */
	public int size();
	
	/**
	 * Gibt an, ob der Baum leer ist oder nicht.
	 * @return
	 */
	public boolean isEmpty();
}
