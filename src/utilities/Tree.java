package utilities;

// tree.java
// demonstrates binary tree

/*                        #===============#
 *                        H  ANMERKUNGEN  H
 *                        #===============#
 *
 * Die vorliegenden Klassen entsprechen nicht den Anforderungen, die
 * wir an "ordentliche" Java-Programmierung stellen !!!
 *
 * Passen Sie den Quellcode dementsprechend an unsere bekannten
 * Programmierrichtlinien an !!!
 *
 *                        #===============#
 *                        H  ANMERKUNGEN  H
 *                        #===============#
 */

import java.util.*; // for Stack class

import utilities.exceptions.NodeException;
import utilities.exceptions.TreeException;


/**
 * A binary tree class with iterative methods.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 31.05.2011
 */
public class Tree implements utilities.interfaces.TreeInterface{
	protected static final String EXCEPTION_NOT_COMPARABLE = "not comparable object received.";
	protected static final String EXCEPTION_NODE_NOT_FOUND = "no node could be found with specified key!";
	protected static final String EXCEPTION_IS_EMPTY = "tree is empty!";
	protected static final String EXCEPTION_DATA_NULL = "null is not allowed as data!";
	protected Node root; // first node of tree
	protected int size; // number of elements in the tree

	// -------------------------------------------------------------
	/**
	 * Defaul constructor of the Tree class.
	 */
	public Tree() { // constructor
		root = null;
		size = 0;
	} // no nodes in tree yet
		// -------------------------------------------------------------

	/**
	 * Searches for a node with the specified key and returns the first found.
	 * There might be more nodes with specified key, but they won't be returned
	 * by this method.
	 * 
	 * @return the first node with the specified key.
	 * @throws TreeException
	 *             if tree is empty.<br>
	 *             if no node could be found.
	 */
	public Node find(Object key) throws TreeException { // find node with given key
		if (size == 0)
			throw new TreeException(EXCEPTION_IS_EMPTY);
		if(key instanceof Comparable){
			Comparable compare = (Comparable)key;
			Node current = root; // start at root
			while (current.getKey().equals(key)) // while no match,
			{
				int vergleich = compare.compareTo(current.getKey());
				if (vergleich < 0) // go left?
					current = current.leftChild;
				else
					// or go right?
					current = current.rightChild;
				if (current == null) // if no child,
					throw new TreeException(EXCEPTION_NODE_NOT_FOUND); // didn't find it
			}
			return current; // found it
		}
		throw new TreeException(EXCEPTION_NOT_COMPARABLE);
	} // end find()
	
	// -------------------------------------------------------------
	/**
	 * This method inserts an object into the tree.
	 */
	public void insert(Object key, Object data) throws TreeException, NodeException {
		if(key instanceof Comparable){
			Comparable compare = (Comparable)key;
			Node newNode = new Node(key, data); // make new node
			if (root == null) // no node in root
				root = newNode;
			else { // root occupied
				Node current = root; // start at root
				Node parent;
				while (true) { // (exits internally)
					parent = current;
					int vergleich = compare.compareTo(current.getKey());
					if (vergleich < 0) { // go left?
						current = current.leftChild;
						if (current == null) { // if end of the line,
							parent.leftChild = newNode; // insert on left
							return;
						}
					} // end if go left
					else { // or go right?
						current = current.rightChild;
						if (current == null) { // if end of the line
							// insert on right
							parent.rightChild = newNode;
							return;
						}
					} // end else go right
				} // end while
			} // end else not root
		}
		throw new TreeException(EXCEPTION_NOT_COMPARABLE);
	} // end insert()

	// -------------------------------------------------------------
	/**
	 * Delete the first found node with specified key.
	 */
	public boolean delete(Object key) throws TreeException { // delete node with given key
		if(size == 0)
			throw new TreeException(EXCEPTION_IS_EMPTY);
		if(key instanceof Comparable){
			Comparable compare = (Comparable)key;
			Node current = root;
			Node parent = root;
			boolean isLeftChild = true;
			while (current.getKey().equals(key)) { // search for node
				parent = current;
				int vergleich = compare.compareTo(current.getKey());
				if (vergleich < 0) { // go left?
					isLeftChild = true;
					current = current.leftChild;
				} else { // or go right?
					isLeftChild = false;
					current = current.rightChild;
				}
				if (current == null) // end of the line,
					return false; // didn't find it
			} // end while
				// found node to delete
	
			// if no children, simply delete it
			if (current.leftChild == null && current.rightChild == null) {
				if (current == root) // if root,
					root = null; // tree is empty
				else if (isLeftChild)
					parent.leftChild = null; // disconnect
				else
					// from parent
					parent.rightChild = null;
			}
	
			// if no right child, replace with left subtree
			else if (current.rightChild == null)
				if (current == root)
					root = current.leftChild;
				else if (isLeftChild)
					parent.leftChild = current.leftChild;
				else
					parent.rightChild = current.leftChild;
	
			// if no left child, replace with right subtree
			else if (current.leftChild == null)
				if (current == root)
					root = current.rightChild;
				else if (isLeftChild)
					parent.leftChild = current.rightChild;
				else
					parent.rightChild = current.rightChild;
	
			else {// two children, so replace with inorder successor
				// get successor of node to delete (current)
				Node successor = getSuccessor(current);
	
				// connect parent of current to successor instead
				if (current == root)
					root = successor;
				else if (isLeftChild)
					parent.leftChild = successor;
				else
					parent.rightChild = successor;
	
				// connect successor to current's left child
				successor.leftChild = current.leftChild;
			} // end else two children
				// (successor cannot have a left child)
			return true; // success
		}
		throw new TreeException(EXCEPTION_NOT_COMPARABLE);
	} // end delete()
	
	// -------------------------------------------------------------

	/**
	 * returns node with next-highest value after delNode goes to right child,
	 * then right child's left descendents
	 * 
	 * @param delNode
	 *            from this node will be the next higher node searched
	 * @return next higher node of delNode
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; // go to right child
		while (current != null) { // until no more
			// left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if (successor != delNode.rightChild) { // right child,
			// make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	// -------------------------------------------------------------
	/**
	 * There are 3 possible kinds of traversing the tree.
	 * <li><b>1</b> for preorder</li>
	 * <li><b>2</b> for inorder</li>
	 * <li><b>2</b> for postorder</li>
	 */
	public String traverse(int traverseType) {
		StringBuilder treeString = new StringBuilder();
		switch (traverseType) {
		case 1:
			treeString.append("\nPreorder traversal: ");
			treeString.append(preOrder(root));
			break;
		case 2:
			treeString.append("\nInorder traversal:  ");
			treeString.append(inOrder(root));
			break;
		case 3:
			treeString.append("\nPostorder traversal: ");
			treeString.append(postOrder(root));
			break;
		}
		treeString.append("\n");
		return treeString.toString();
	}

	// -------------------------------------------------------------
	/**
	 * Returns a string object which represents the tree traversal in preorder.
	 */
	private String preOrder(Node localRoot) {
		StringBuilder nodeString = new StringBuilder();
		if (localRoot != null) {
			nodeString.append(localRoot.displayNode());
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
		return nodeString.toString();
	}

	// -------------------------------------------------------------
	/**
	 * Returns a string object which represents the tree traversal in inorder.
	 */
	private String inOrder(Node localRoot) {
		StringBuilder nodeString = new StringBuilder();
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			nodeString.append(localRoot.displayNode());
			inOrder(localRoot.rightChild);
		}
		return nodeString.toString();
	}

	// -------------------------------------------------------------
	/**
	 * Returns a string object which represents the tree traversal in postorder.
	 */
	private String postOrder(Node localRoot) {
		StringBuilder nodeString = new StringBuilder();
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			nodeString.append(localRoot.displayNode());
		}
		return nodeString.toString();
	}

	// -------------------------------------------------------------
	/**
	 * Returns a string object which imitates the shape of the tree. 
	 */
	public String displayTree() {
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		StringBuilder treeString = new StringBuilder();
		treeString.append("......................................................\n");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				treeString.append(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					treeString.append(temp.getKey());
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					treeString.append("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					treeString.append(' ');
			} // end while globalStack not empty
			treeString.append("\n");
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		treeString.append("......................................................\n");
		return treeString.toString();
	} // end displayTree()
		// -------------------------------------------------------------

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
} // end class Tree