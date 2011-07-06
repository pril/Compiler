package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utilities.exceptions.FileException;
import base.exceptions.FileReaderException;

/**
 * Liest die Eingabedatei und Parst die Datei.
 * @author Alexei Felberg
 * @author Daniel
 * @version 05.07.2011 20:21:39
 */
public class EingabeDateiReader {
	
	
	private static final String ERROR_PARAMETER_EMPTY = "Parameter darf nicht leer sein";
	private static final String ERROR_PARAMETER_NULL = "Parameter darf nicht null sein.";
	private String filename = "";
	private BufferedReader bufferedReader = null;
	private FileReader filereader = null;
	private boolean open = false; 
	private boolean eof = false; //End of File.
	
	
	/**
	 * Konstruktor
	 */
	public EingabeDateiReader()
	{
		filename = null;
	}
	
	/**
	 * Konstruktor
	 * @param filename
	 * Absoluter Pfad zur Datei.
	 */
	public EingabeDateiReader(String filename)
	{
		filename = null;
	}
	
	/**
	 * Legt den Dateinamen fest.
	 * @param filename
	 * @throws IllegalArgumentException
	 * @throws FileException
	 */
	public void setFileName(String filename) throws IllegalArgumentException,FileException
	{
		if (filename == null) throw new IllegalArgumentException(ERROR_PARAMETER_NULL);
		if (filename.trim().isEmpty()) throw new IllegalArgumentException(ERROR_PARAMETER_EMPTY);
		File file = new File(filename);
		if (!file.exists()) throw new FileException("Datei " + filename +" existiert nicht.");
		if (!file.canRead()) throw new FileException("Datei " + filename + " kann nicht gelesen werden.");
		if (!file.isFile()) throw new FileException("Datei " + filename + " ist keine Datei.");
		this.filename = filename;
	}
	
	public String getFileName()
	{
		return filename;
	}
	
	/**
	 * Liest eine Zeile aus der Datei. 
	 * Vorausgesetzt die Datei wurde geoeffnet und der Dateiname wurde angegeben.
	 * @return
	 * @throws IOException 
	 * @throw FileReaderException
	 */
	public String readLine() throws FileReaderException, IOException
	{
		checkFilename();
		if (!isOpen()) throw new FileReaderException("Die Datei wurde noch nicht zum lesen geöffnet.");
		if (filereader==null) throw new FileReaderException("Das einlesen einer Datei ist nicht moglich. Da der FileReader null ist.");
		if (bufferedReader == null) throw new FileReaderException("Das einlesen einer Datei ist nicht möglich. Da der BufferedReader null ist.");
		if (eof) return null;
		String line = "";
		line = bufferedReader.readLine();
		if (line == null) eof = true; 
		return line;
	}
	
	
/**
 * Prüft ob der Dateiname gesetzt wurde andernfalls wird eine FileReaderException geworfen. 
 * @throws FileReaderException
 */
	private void checkFilename() throws FileReaderException
	{
		if (getFileName()==null) throw new FileReaderException("Sie haben noch keinen Dateinamen festgelegt.");
			
	}
	
	
	/**
	 * Ist das Ende der Datei erreicht?
	 * @return
	 */
	public boolean isEof() {
		return eof;
	}

	/**
	 * Wurde die Datei geoeffnet
	 * @return
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Oeffne die Datei
	 * @param open
	 */
	public void openFile() throws FileReaderException {
		if (open) throw new  FileReaderException("Datei" + getFileName() + "ist bereits geoffnet.");
		if (getFileName()== null)  
	}


}
