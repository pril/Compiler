package base.interfaces;

/**
 * Ein Fehlerbeobachter beobachtet Fehler und Empfaengt den entsprechenden Fehler.
 * @author Alexei Felberg
 * @author Daniel
 * @version 06.07.2011 18:35:37
 */
public interface ExceptionBeobachter extends Beobachter {
	
	
	public void reciveErrorMessage(Exception exception);
}
