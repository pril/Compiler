package utilities;

import utilities.exceptions.NodeException;
import utilities.interfaces.NodeInterface;


/**
 * A node for the class {@link Tree}.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 31.05.2011
 */
public class Node implements NodeInterface {
	protected static final String EXCEPTION_NOT_NODE = "leftChild and rightChild must be a node.";
	protected static final String EXCEPTION_DATA_NULL = "null is not allowed as data!";
	protected Object key; // data item (key)
	protected Object data; // data item
	protected Node leftChild; // this node's left child
	protected Node rightChild; // this node's right child

	/**
	 * Full constructor for this class. It covers all attributes of this class.
	 * 
	 * @param key
	 *            This values represents the data object in the tree. Also the
	 *            node will be sorted after this value.
	 * @param data
	 *            the data that be hold by this node.
	 * @param leftChild
	 *            left node after this one.
	 * @param rightChild
	 *            right node after this one.
	 * @throws NodeException
	 *             if received null instead of an Object.
	 */
	public Node(Object key, Object data, Object leftChild, Object rightChild)
			throws NodeException {
		if ((leftChild instanceof NodeInterface || leftChild == null)
				&& (rightChild instanceof NodeInterface || rightChild == null)) {
			this.leftChild = (Node) leftChild;
			this.rightChild = (Node) rightChild;
			this.key = key;
			setData(data);
			return;
		}
		throw new NodeException(EXCEPTION_NOT_NODE);
	}

	/**
	 * Tiny constructor of the class Node. This node will be created with null
	 * as right and left node. For further informations take a look at the
	 * constructor {@link #Node(int, Object, Node, Node)}.
	 */
	public Node(Object key, Object data) throws NodeException {
		this(key, data, null, null);
	}

	public Object displayNode() { // display ourself
		return '{' + key.toString() + ", " + data + "}";
	}

	/**
	 * Returns the data of this node.
	 * 
	 * @return data Object of this node.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Return the key of this node.
	 * 
	 * @return key as an int of this node.
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * Set data to a new value.
	 * 
	 * @throws NodeException
	 *             if received null instead of an Object.
	 */
	public void setData(Object data) throws NodeException {
		if (data == null)
			throw new NodeException(EXCEPTION_DATA_NULL);
		this.data = data;
	}

	@Override
	public Object getLeftChild() {
		return leftChild;
	}

	@Override
	public Object getRightChild() {
		return rightChild;
	}

	@Override
	public boolean isLeave() {
		if (rightChild != null || leftChild != null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isNode() {
		return !isLeave();
	}
} // end class Node