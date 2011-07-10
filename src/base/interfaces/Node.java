package base.interfaces;

/**
 * Konoten eines Baumes
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.07.2011
 */
public interface Node<T> {

	/**
	 * Liefert das Datenelement des Knotens zurueck.
	 * 
	 * @return
	 */
	public T getData();

	/**
	 * Linkes Kind
	 * 
	 * @return
	 */
	public Node<T> getLeftChild();

	/**
	 * Rechtes Kind
	 * 
	 * @return
	 */
	public Node<T> getRightChild();

	/**
	 * Handelt es sich um einen Root.
	 * 
	 * @return
	 */
	public boolean isRoot();

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