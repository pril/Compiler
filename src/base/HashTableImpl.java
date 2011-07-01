package base;

import utilities.folz_list_klassen.normal.DList;
import base.exceptions.HashItemException;
import base.exceptions.HashTableException;
import base.interfaces.HashTable;

/**
 * Implementiert eine Hashtable.
 * Zum Speichern groesserer Datenmengen
 * @author Alexei Felberg
 * @author Daniel
 * @version 01.07.2011 10:30:29
 */
public class HashTableImpl<Key,Value> implements HashTable<Key, Value>{

	private static final int MIN_SIZE=1;
	private int max = 0;
	private DList listarray=null;
	private DList list = null;
	
	public void HashTableImpl(int size) throws IllegalArgumentException
	{
		if (size<MIN_SIZE) throw new IllegalArgumentException("Wert muss immer positiv sein.");
		max = size;
		listarray = new DList();
		list = new DList();
	}

	@Override
	public void insert(Key key, Value value) throws IllegalArgumentException,
			HashTableException, HashItemException {
		if (listarray.size()==max) throw new HashTableException("Maximalgroesse ueberschritten.Hashtabelle ist voll.");
		
		int indexkey = generateKey(key);
		HashItem<Key,Value> hashitem = new HashItem<Key, Value>(key,value);
		if (listarray.get(indexkey)==null)
		{	DList list = new DList();
			list.add(0,hashitem);
			listarray.add(indexkey, list);
		}
		else
		{
			DList list;
			list =((DList)listarray.get(indexkey));
			list.add(list.size(), hashitem);
		}
	}

	/**
	 * Try to generate a Key with the given information
	 * @param key
	 * @return
	 */
	private int generateKey(Key key) throws IllegalArgumentException
	{
		if (key == null) throw new IllegalArgumentException("Key kann nicht null sein.");
		//Key in Chararray umwandeln
		char[] chararray = String.valueOf(key).toCharArray();
		int indexkey =0;
		String s = new String();
		//Ermittele index-Key fuer Chararray
		for (int i = 0;i<chararray.length;i++)
		{
			indexkey =indexkey +  (chararray[i]*i);
		}
		indexkey = indexkey%max;
		return indexkey;
	}

	@Override
	public void removeItem(Key key,Value value) throws IllegalArgumentException,
			HashTableException {
		if (key == null) throw new IllegalArgumentException("Key darf nicht null sein.");
		if (value == null) throw new IllegalArgumentException("Value darf nicht null sein.");
		if(isEmpty()) throw new HashTableException("Hashtabelle ist leer.");
		int indexkey = generateKey(key);
		if (listarray.get(indexkey)== null) throw new HashTableException("Key nicht vorhanden.");
		DList list = (DList)listarray.get(indexkey);
		if (!list.contains(value)) throw new HashTableException("Value nicht vorhanden.");
		list.remove(value);
	}

	@Override
	public void removeKey(Key key) throws IllegalArgumentException,
			HashTableException {
		if (key == null) throw new IllegalArgumentException("Key darf nicht null sein.");
		if(isEmpty()) throw new HashTableException("Hashtabelle ist leer.");
		int indexkey = generateKey(key);
		if (listarray.get(indexkey)== null) throw new HashTableException("Key nicht vorhanden.");
		listarray.remove(indexkey);
	}

	@Override
	public void containsKey(Key key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		return listarray.size();
	}

	@Override
	public boolean isEmpty() {
		return listarray.size()==0;
	}

	@Override
	public boolean isFull() {
		return listarray.size()==max;
	}
	
	
}
