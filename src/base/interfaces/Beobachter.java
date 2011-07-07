package base.interfaces;
/**
 * Ein Beobachter legt eine Nachricht fest.
 * Dies ist das Basis interface eines Beobachters.
 * @author Alexei Felberg
 * @author Daniel
 * @version 06.07.2011 18:30:39
 */
public interface Beobachter {

	public void startMethod(String classname,String methodname);
	public void endMethod(String classname,String methodname);
}
