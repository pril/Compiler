package base.interfaces;
/**
 * Beschreibt die Oeffentlichen Methodenruempfe eines Identifiers.
 * 
 * @author Daniel Rhein
 * @author Alexei Felberg
 * @version 22.06.2011
 */
public interface Identifier<T> {
	//=======================================KONSTANTEN====================================
	public static final String ERROR_VALUE_NULL = "Der Variablenwert darf nicht null sein.";
	public static final String ERROR_VALUE_EMPTY = "Der Variablenwert darf nicht empty sein.";
	
	//=======================================METHODENRUEMPFE==================================
	/**
	 * Liefert zum Identifier zugehoerigen Wert.
	 * @return
	 */
	public T getValue();
	/**
	 * Legt den jeweiligen Wert zum Identifier fest.
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public void setValue(T value) throws IllegalArgumentException;
	
	/**
	 * Legt den Namen des Identifiers fest
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException;
	/**
	 * Gibt den Namen des jeweiligen Identifiers zurueck.
	 * @return
	 */
	public String getName();
}
