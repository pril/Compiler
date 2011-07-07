package base.interfaces;

/**
 * Beobachtet die Eingabedaten und uebermittelt das gelesene.
 * @author Alexei Felberg
 * @author Daniel
 * @version 06.07.2011 18:36:38
 */
public interface EingabeDateiReaderBeobachter extends Beobachter{
	
	public void readline(int linenumber,String line);
	public void open(String file);
	public void close(String file);
	public void reachEOF(String file);
}
