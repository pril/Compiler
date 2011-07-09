package base.interfaces;

/**
 * Ein Fehlerbeobachter beobachtet Fehler und Empfaengt den entsprechenden Fehler.
 * @author Alexei Felberg
 * @author Daniel
 * @version 07.07.2011 21:42:48
 */
public interface ExceptionBeobachter extends Beobachter {
	
	
	public void receiveErrorMessage(Exception exception);
}
