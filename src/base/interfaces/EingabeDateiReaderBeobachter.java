package base.interfaces;

/**
 * Beobachtet die Eingabedaten und uebermittelt das gelesene.
 * @author Alexei Felberg
 * @author Daniel
 * @version 06.07.2011 18:36:38
 */
public interface EingabeDateiReaderBeobachter extends Beobachter{
	
	public void eingabeDateiReaderReadLine(int linenumber,String line);
	public void eingabeDateiReaderOpen(String file);
	public void eingabeDateiReaderClose(String file);
	public void eingabeDateiReaderReachEOF(String file);
}
