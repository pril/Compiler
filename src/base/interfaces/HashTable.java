package base.interfaces;


/**
 * Beschreibt die Methoden einer HashTable
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 22.06.2011
 */
public interface HashTable<Key,Value> {
	/**
	 * Einfuegen eines Keys in die Tabelle
	 * @param key
	 * @param value
	 */
	public void insert(Key key,Value value);
	
	public void insert(Key key);
	
}
