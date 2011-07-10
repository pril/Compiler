package base;

import java.util.ArrayList;
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
	private ArrayList<DList> listarray=null;
	private DList beobachter =null;
	
	public  HashTableImpl(int size) throws IllegalArgumentException
	{
		if (size<MIN_SIZE) throw new IllegalArgumentException("Wert muss immer positiv sein.");
		max = size;
		listarray = new ArrayList<DList>(max);
		for (int i = 0;i<size;i++)
		{
			listarray.add(i, null); //Null-Objekte einfuegen.
		}
	}

	@Override
	public void insert(Key key, Value value) throws IllegalArgumentException,
			HashTableException, HashItemException {
		if (listarray.size()==max) throw new HashTableException("Maximalgroesse ueberschritten.Hashtabelle ist voll.");
		
		int indexkey = generateKey(key);
		HashItem<Key,Value> hashitem = new HashItem<Key, Value>(key,value);
		if (listarray.get(indexkey)==null)
		{	
			DList list = new DList();
			list.add(0,hashitem);
			listarray.add(indexkey, list);
		}
		else
		{
			DList list;
			list =((DList)listarray.get(indexkey));
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
	public boolean containsKey(Key key) throws IllegalArgumentException {
		int indexkey = generateKey(key);
		System.out.println(this.toString());
		if (listarray.get(indexkey)!=null)
		{
			
			Iterator iterator = ((DList)listarray.get(indexkey)).iterator();
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

	@Override
	public void addBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		
		
	}

	@Override
	public void removeBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Key key) throws IllegalArgumentException,
			HashTableException, HashItemException {
	
		//Weg damit die Hashtable muss aufgefuellt werden.
		//if (listarray.size()==max) throw new HashTableException("Maximalgroesse ueberschritten.Hashtabelle ist voll.");
		
		int indexkey = generateKey(key);
		HashItem<Key,Value> hashitem = new HashItem<Key, Value>(key,null);
		if (indexkey<0) indexkey = indexkey * -1;
		if (listarray.get(indexkey)==null)
		{	DList list = new DList();
			list.add(0,hashitem);
			listarray.add(indexkey, list);
			System.out.println(toString());
		}
		else
		{
			DList list;
			list =((DList)listarray.get(indexkey));
			list.add(list.size(), hashitem);
		}		
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<listarray.size();i++)
		{
			if (listarray.get(i)== null)
			{
				sb.append(i).append(". ").append("null ");
			}
			else
			{
				DList liste = listarray.get(i);
				for (int e=0;e<liste.size();e++)
				sb.append(i).append(liste.get(e).toString()).append("null ");
			}
		}
		return sb.toString();
	}
	
}
