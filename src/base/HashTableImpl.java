package base;

import java.util.Iterator;

import utilities.folz_list_klassen.normal.DList;
import base.exceptions.HashItemException;
import base.exceptions.HashTableException;
import base.interfaces.Beobachtbar;
import base.interfaces.Beobachter;
import base.interfaces.HashTable;

/**
 * Implementiert eine Hashtable.
 * Zum Speichern groesserer Datenmengen
 * @author Alexei Felberg
 * @author Daniel
 * @version 01.07.2011 10:30:29
 */
public class HashTableImpl<Key,Value> implements HashTable<Key, Value>, Beobachtbar{

	private static final int MIN_SIZE=1;
	private int max = 0;
	private DList listarray[]=null;
	private DList beobachter =null;
	
	public  HashTableImpl(int size) throws IllegalArgumentException
	{
		if (size<MIN_SIZE) throw new IllegalArgumentException("Wert muss immer positiv sein.");
		max = size;
		listarray = new DList[max];
		for (int i = 0;i<size;i++)
		{
			listarray[i]=null; //Null-Objekte einfuegen.
		}
	}

	@Override
	public void insert(Key key, Value value) throws IllegalArgumentException,
			HashTableException, HashItemException {
		if (listarray.length==max) throw new HashTableException("Maximalgroesse ueberschritten.Hashtabelle ist voll.");
		
		int indexkey = generateKey(key);
		HashItem<Key,Value> hashitem = new HashItem<Key, Value>(key,value);
		if (listarray[indexkey]==null)
		{	
			DList list = new DList();
			list.add(0,hashitem);
			listarray[indexkey]=list;
		}
		else
		{
			DList list;
			list = listarray[indexkey];
			list.add(list.size(), hashitem);
		}
		System.out.println("Stop hier ist noch ein Fehler vorhanden.");
	}

	
	
	/**
	 * Try to generate a Key with the given information
	 * @param key
	 * @return
	 */
	private int generateKey(Key key) throws IllegalArgumentException
	{
		if (key == null) throw new IllegalArgumentException("Key kann nicht null sein.");
		if ((key.hashCode()%max) <0) return (key.hashCode()%max)*-1;
		return (key.hashCode()%max);
	}

	@Override
	public void removeItem(Key key,Value value) throws IllegalArgumentException,
			HashTableException {
		if (key == null) throw new IllegalArgumentException("Key darf nicht null sein.");
		if (value == null) throw new IllegalArgumentException("Value darf nicht null sein.");
		if(isEmpty()) throw new HashTableException("Hashtabelle ist leer.");
		int indexkey = generateKey(key);
		if (listarray[indexkey]==null) throw new HashTableException("Key nicht vorhanden.");
		DList list = listarray[indexkey];
		if (!list.contains(value)) throw new HashTableException("Value nicht vorhanden.");
		list.remove(value);
	}

	@Override
	public void removeKey(Key key) throws IllegalArgumentException,
			HashTableException {
		if (key == null) throw new IllegalArgumentException("Key darf nicht null sein.");
		if(isEmpty()) throw new HashTableException("Hashtabelle ist leer.");
		int indexkey = generateKey(key);
		if (listarray[indexkey]== null) throw new HashTableException("Key nicht vorhanden.");
		listarray[indexkey].remove(indexkey);
	}

	@Override
	public boolean containsKey(Key key) throws IllegalArgumentException {
		int indexkey = generateKey(key);
		if (listarray[indexkey]!=null)
		{
			
			Iterator iterator = listarray[indexkey].iterator();
			while(iterator.hasNext())
			{
				HashItem<Key,Value> hashitem = (HashItem<Key,Value>)iterator.next();
				if (hashitem.getKey().equals(key)) return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return listarray.length;
	}

	@Override
	public boolean isEmpty() {
		return listarray.length==0;
	}

	@Override
	public boolean isFull() {
		return size()==max;
	}

	@Override
	public void addBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		
		
	}

	@Override
	public void removeBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		
	}

	@Override
	public void insert(Key key) throws IllegalArgumentException,
			HashTableException, HashItemException {
	
		//Weg damit die Hashtable muss aufgefuellt werden.
		//if (listarray.size()==max) throw new HashTableException("Maximalgroesse ueberschritten.Hashtabelle ist voll.");
		
		int indexkey = generateKey(key);
		HashItem<Key,Value> hashitem = new HashItem<Key, Value>(key,null);
		if (indexkey<0) indexkey = indexkey * -1;
		if (listarray[indexkey]==null)
		{	DList list = new DList();
			list.add(0,hashitem);
			listarray[indexkey]= list;
		}
		else
		{
			DList list;
			list =listarray[indexkey];
			list.add(list.size(), hashitem);
		}		
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<listarray.length;i++)
		{
			if (listarray[i]== null)
			{
				sb.append(i).append(". ").append("null ");
			}
			else
			{
				DList liste = listarray[i];
				for (int e=0;e<liste.size();e++)
				sb.append(i).append(liste.get(e).toString()).append("null ");
			}
		}
		return sb.toString();
	}

	@Override
	public base.interfaces.HashItem<Key, Value> get(Key key)
			throws IllegalArgumentException, HashTableException,
			HashItemException {
		
		int indexkey = generateKey(key);
		HashItem<Key,Value> hashitem = null;
		
			DList list;
			list = listarray[indexkey];
			Iterator iterator = list.iterator();
			while(iterator.hasNext())
			{
				hashitem=(HashItem<Key,Value>)iterator.next();
				if (hashitem.getKey().equals(key))
				{
					return hashitem;
				}
			}
			
		return null;
	}
	
}
