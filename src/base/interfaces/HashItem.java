package base.interfaces;

import base.exceptions.HashItemException;


/**
 * Item eines Hashtables.
 * @author Alexei Felberg
 * @author Daniel
 * @version 01.07.2011 10:37:30
 * @param <Key>
 * @param <Value>
 */
public interface HashItem<Key,Value> extends Comparable<Value>{
	

	/**
	 * Liefert den Key des Knotens zurueck 
	 */
	public Key getKey();
		
	
	/**
	 * Gibt den den Value zurueck
	 * @return
	 */
	public Value getValue() ;
	
	/**
	 * Legt den Value fest.
	 * @return
	 */
	public void setValue(Value value) throws HashItemException ;

	/**
	 * Legt den Schluessel fest.
	 * @throws HashItemException
	 */
	public void setKey(Key key) throws HashItemException;

}
