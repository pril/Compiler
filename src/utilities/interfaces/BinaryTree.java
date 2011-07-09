package utilities.interfaces;

import utilities.exceptions.TreeException;

/**
 * Methoden eines Binaerbaumes.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.07.2011
 */
public interface BinaryTree {
	/**
	 * Einfuegen eines Elements in den Baum
	 * 
	 * @param object
	 */
	public void insert(Object object) throws TreeException;

	/**
	 * Liefert die Elementenanzahl des Baumes.
	 * 
	 * @return Anzahl der Elemente, die sich im Baum befinden.
	 */
	public int size();

	/**
	 * Liefert den Root des Baumes zurueck.
	 * 
	 * @return
	 */
	public Object getRoot();

	/**
	 * Gibt an, ob der Baum leer ist oder nicht.
	 * 
	 * @return
	 */
	public boolean isEmpty();

}
