package base.interfaces;
/**
 * Macht eine Klasse beobachtbar und erlaubt an eine Klasse bestimmte Beobachter(Observer) anzumelden.
 * @author Alexei Felberg
 * @author Daniel
 * @version 06.07.2011 18:28:00
 */
public interface Beobachtbar {

	/**
	 * Fuegt einen Beobachter hinzu.
	 * @param beobachter
	 * @throws IllegalArgumentException
	 */
	public void addBeobachter(Beobachter beobachter) throws IllegalArgumentException;
	/**
	 * Entfernt einen Beobachter.
	 * @param beobachter
	 * @throws IllegalArgumentException
	 */
	public void removeBeobachter(Beobachter beobachter) throws IllegalArgumentException;
	
}
