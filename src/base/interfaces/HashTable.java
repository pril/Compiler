package base.interfaces;

import base.exceptions.HashItemException;
import base.exceptions.HashTableException;


/**
 * Beschreibt die Methoden einer HashTable
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 22.06.2011
 */
public interface HashTable<Key,Value> {
	
	public static final String ERROR_KEY_IS_NULL  = "Schluessel darf nicht null sein .";
	public static final String ERROR_VALUE_IS_NULL  = "Value darf nicht null sein .";
	public static final String ERROR_KEY_IS_EMPTY  = "Schluessel darf nicht leer sein .";
	public static final String ERROR_VALUE_IS_EMPTY  = "Schluessel darf nicht leer sein .";
	public static final String ERROR_KEY_IS_NEGATIV  = "Schluessel darf nicht null sein .";
	public static final String ERROR_VALUE_IS_NEGATIV  = "Value darf nicht null sein .";

	
	/**
	 * Einfuegen eines Keys in die Tabelle
	 * @param key
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws HashtableException
	 * @throws HashItemException
	 */
	public void insert(Key key,Value value) throws IllegalArgumentException,HashTableException,HashItemException;
	
	/**
	 * Einfuegen eines Keys in die Tabelle
	 * @param key
	 * @throws IllegalArgumentException
	 * @throws HashtableException
	 * @throws HashItemException
	 */
	public void insert(Key key) throws IllegalArgumentException,HashTableException,HashItemException;
	
	
	/**
	 * Remove an Item of the HashTable
	 * @param value
	 */
	public void removeItem(Key Key,Value value) throws IllegalArgumentException,HashTableException;
	
	/**
	 * Remove a Key of the HashTable
	 * @param key
	 */
	public void removeKey(Key key) throws IllegalArgumentException,HashTableException;
	/**
	 * Gibt an ob ein Schluessel schon vorhanden ist
	 * @param key
	 * @throws IllegalArgumentException
	 * @return boolean key.
	 */
	public boolean containsKey(Key key) throws IllegalArgumentException;
	
	/**
	 * Gibt die size an.
	 * @return
	 */
	public int size();
	
	/**
	 * Gibt an ob ein HashTable leer ist.
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * Gibt an ob ein HashTable voll ist.
	 * @return
	 */
	public boolean isFull();
	
}
