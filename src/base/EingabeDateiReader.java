package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Iterator;

import utilities.exceptions.FileException;
import utilities.folz_list_klassen.normal.DList;
import base.exceptions.FileReaderException;
import base.interfaces.Beobachtbar;
import base.interfaces.Beobachter;
import base.interfaces.EingabeDateiReaderBeobachter;

/**
 * Liest die Eingabedatei und Parst die Datei.
 * @author Alexei Felberg
 * @author Daniel
 * @version 05.07.2011 20:21:39
 */
public class EingabeDateiReader implements Beobachtbar{
	
	//============================================KONSTANTEN=====================================================/
	private static final String ERROR_READING_LINEREADER_NULL = "Das einlesen einer Datei ist nicht möglich. Da der BufferedReader null ist.";
	private static final String ERROR_READING_FILE_READER_NULL = "Das einlesen einer Datei ist nicht moglich. Da der FileReader null ist.";
	private static final String ERROR_FILE_NOT_OPEN = "Die Datei wurde noch nicht zum lesen geöffnet.";
	private static final String ERROR_FILE_NAME_NULL = "Sie haben noch keinen Dateinamen festgelegt.";
	private static final String ERROR_NO_BUFFEREDREADER = "BufferedReader wurde noch nicht festgelegt.";
	private static final String ERROR_NO_FILEREADER = "FileReader ist noch nicht festgelegt.";
	private static final String ERROR_BEOBACHTER_NULL = "Beobachter darf nicht null sein.";
	private static final String ERROR_PARAMETER_EMPTY = "Parameter darf nicht leer sein";
	private static final String ERROR_PARAMETER_NULL = "Parameter darf nicht null sein.";
	private String filename = "";
	private LineNumberReader bufferedReader = null;
	private FileReader filereader = null;
	private boolean open = false; //Datei geoeffnet oder nicht.
	private boolean eof = false; //End of File.
	private DList beobachterliste; //Liste aller beobachter
	
	//============================================KONSTRUKTOR=====================================================/
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
	 * @throws FileException 
	 * @throws IllegalArgumentException 
	 */
	public EingabeDateiReader(String filename) throws IllegalArgumentException, FileException
	{
		setFileName(filename);
	}
	
	//================================METHODEN==========================================/
	
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
		if (!isOpen()) throw new FileReaderException(ERROR_FILE_NOT_OPEN);
		if (filereader==null) throw new FileReaderException(ERROR_READING_FILE_READER_NULL);
		if (bufferedReader == null) throw new FileReaderException(ERROR_READING_LINEREADER_NULL);
		if (eof) return null;
		String line = "";
		line = bufferedReader.readLine();
		sendeReadLine(bufferedReader.getLineNumber(), line);
		if (line == null) {
			sendeEOF(getFileName());
			eof = true;
		}
		return line;
	}
	
	
/**
 * Prüft ob der Dateiname gesetzt wurde andernfalls wird eine FileReaderException geworfen. 
 * @throws FileReaderException
 */
	private void checkFilename() throws FileReaderException
	{
		if (getFileName()==null) throw new FileReaderException(ERROR_FILE_NAME_NULL);
			
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
	 * Wurde die Datei geschlossen
	 * @return
	 */
	public boolean isClosed() {
		return !open;
	}
	
	/**
	 * Oeffne die Datei
	 * @param open
	 * @throws FileReaderException
	 * @throws FileNotFoundException 
	 * @return true if the file is open.
	 */
	public boolean openFile() throws FileReaderException, FileNotFoundException {
		if (isOpen()) throw new  FileReaderException("Datei" + getFileName() + "ist bereits geoffnet.");
		checkFilename();
		filereader = new FileReader(new File(getFileName()));
		bufferedReader = new LineNumberReader(filereader);
		open = true;
		eof = false;
		return open;
	}
	
	/**
	 * Close the File 
	 * @return
	 * @throws FileReaderException
	 * @throws IOException 
	 */
	public boolean closeFile() throws FileReaderException, IOException
	{
		checkFilename();
		if (!isOpen()) throw new FileReaderException("Datei" + getFileName() + " ist noch nicht geöffnet."); 
		if (filereader == null) throw new FileReaderException(ERROR_NO_FILEREADER);
		if (bufferedReader == null) throw new FileReaderException(ERROR_NO_BUFFEREDREADER);
		eof = false;
		open = false;
		filereader.close();
		bufferedReader.close();
		sendeClose(getFileName());
		open = false;
		return true;
	}
	//==================================Beobachter===================================
	@Override
	public void addBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		if (beobachter == null) throw new IllegalArgumentException(ERROR_BEOBACHTER_NULL);
		beobachterliste.add(beobachterliste.size(), beobachter);
	}

	@Override
	public void removeBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		if (beobachter== null) throw new IllegalArgumentException(ERROR_BEOBACHTER_NULL);
		beobachterliste.remove(beobachter);
	}

	//============================NACHRICHTEN AN BEOBACHTER=============================
	private void sendeReadLine(int number,String line)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			EingabeDateiReaderBeobachter beobachter = (EingabeDateiReaderBeobachter)iterator.next();
			beobachter.readline(number, line);
		}
		while(iterator.hasNext());
		
	}

	private void sendeOpen(String file)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			EingabeDateiReaderBeobachter beobachter = (EingabeDateiReaderBeobachter)iterator.next();
			beobachter.open(file);
		}
		while(iterator.hasNext());
	}
	
	private void sendeClose(String file)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			EingabeDateiReaderBeobachter beobachter = (EingabeDateiReaderBeobachter)iterator.next();
			beobachter.close(file);
		}
		while(iterator.hasNext());
	}
	
	private void sendeEOF(String file)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			EingabeDateiReaderBeobachter beobachter = (EingabeDateiReaderBeobachter)iterator.next();
			beobachter.reachEOF(file);
		}
		while(iterator.hasNext());
	}
	
}
