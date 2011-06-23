package utilities.interfaces;


/**
 * Konoten eines Baumes
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public interface NodeInterface {

	/**
	 * Liefert das Datenelement des Knotens zurueck.
	 * 
	 * @return
	 */
	public Object getData();

	/**
	 * Linkes Kind
	 * 
	 * @return
	 */
	public Object getLeftChild();

	/**
	 * Rechtes Kind
	 * 
	 * @return
	 */
	public Object getRightChild();

	/**
	 * Handel es sich um ein Blatt.
	 * 
	 * @return
	 */
	public boolean isLeave();

	/**
	 * Handelt es sich um einen Knoten.
	 * 
	 * @return
	 */
	public boolean isNode();
}
