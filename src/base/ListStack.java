package base;

import utilities.folz_list_klassen.normal.DList;
import utilities.interfaces.Stack;

/**
 * Stackklasse basierend auf einer doppeltverketteten Liste.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 07.07.2011
 */
public class ListStack extends Stack {
	private static final String EXCEPTION_MAXSIZE = "Maximale Groesse muss groesser 0 sein! Oder keine Begrenzung: -1";
	private int maxsize;
	private DList dlist;

	/**
	 * Konstruktor fuer den ListStack, mit Begrenzungsangabe.
	 * 
	 * @param maxsize
	 *            die maximale Hoehe des ListStacks. >0 um eine Begrenzung
	 *            festzulegen, -1 wenn man keine Begrenzung moechte.
	 */
	public ListStack(int maxsize) {
		if (maxsize < 1 && maxsize != -1)
			throw new IllegalArgumentException(EXCEPTION_MAXSIZE);
		this.maxsize = maxsize;
		dlist = new DList();
	}

	/**
	 * Methode sagt ob ListStack voll ist.
	 */
	@Override
	public boolean full() {
		if (maxsize == -1)
			return false;
		else
			return dlist.size() >= maxsize;
	}

	/**
	 * Legt ein Objekt auf den ListStack.
	 */
	@Override
	public void push(Object o) {
		if (maxsize == -1 || dlist.size() < maxsize)
			dlist.addLast(o);
	}

	/**
	 * Entfernt das oberste Objekt von dem ListStack.
	 */
	@Override
	public void pop() {
		dlist.removeLast();
	}

	/**
	 * Liefert das oberste Objekt, dass auf dem ListStack liegt.
	 */
	@Override
	public Object top() {
		return dlist.getLast();
	}

	/**
	 * Liefert die Anzahl der Objekte im Stack.
	 */
	@Override
	public int size() {
		return dlist.size();
	}

}
