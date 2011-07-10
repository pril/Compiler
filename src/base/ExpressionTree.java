package base;

import base.interfaces.Expression;
import utilities.exceptions.TreeException;
import utilities.interfaces.BinaryTree;

/**
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.07.2011
 */
public class ExpressionTree implements BinaryTree {
	private static final String EXCEPTION_FALSE_ARITHMETIC_EXPRESSION = "ExpressionTree stellt keinen gueltigen arithmetischen Ausdruck dar!";
	private static final String EXCEPTION_RECEIVED_NULL = "Es wurde null an ExpressionTree uebergeben.";
	private static final String EXCEPTION_IS_EMPTY = "ExpressionTree is empty!";
	private static final String OUT_NEW_LINE = "\n";
	private static final String OUT_DELIMITER = "\t";
	private ExpressionNode root;
	private int size = 0;
	private final static int DEFAUL_STACKSIZES = 256;
	private ListStack optStack;
	private ListStack opndStack;

	/**
	 * Konstruktor fuer den ExpressionTree, mit Begrenzungsangabe fuer die
	 * verwendeten ListStacks.
	 * 
	 * @param maxsize
	 *            die maximale Hoehe des ListStacks. >0 um eine Begrenzung
	 *            festzulegen, -1 wenn man keine Begrenzung moechte.
	 */
	public ExpressionTree(int stacksizes) {
		optStack = new ListStack(stacksizes);
		opndStack = new ListStack(stacksizes);
	}

	/**
	 * Default Konstruktor fuer den ExpressionTree, mit default ListStackgroesse
	 * von 256.
	 */
	public ExpressionTree() {
		this(DEFAUL_STACKSIZES);
	}

	/**
	 * Liefert das Ergebnis des ExpressionTrees.
	 * 
	 * @return Double-Wert als Ergebnis des ExpressionTrees.
	 * @throws TreeException
	 *             wenn der Baum keinen gueltigen Ausdruck darstellt oder er
	 *             leer ist.
	 */
	public Double calc() throws TreeException {
		setRoot();
		return root.getData().getValue();
	}
	
	/**
	 * Setzt root fuer den aktuellen baum und ermittelt ob der arithmetische Ausdruck auswertbar ist.
	 * 
	 * @throws TreeException
	 *             wenn der Baum keinen gueltigen Ausdruck darstellt oder er
	 *             leer ist.
	 */
	private void setRoot() throws TreeException{
		if(size == 0)
			throw new TreeException(EXCEPTION_IS_EMPTY);
		while (!optStack.isEmpty()) {
			ExpressionNode newExpression = (ExpressionNode) optStack.top();
			optStack.pop();
			newExpression.rightChild =(ExpressionNode) opndStack.top();
			opndStack.pop();
			newExpression.leftChild = (ExpressionNode) opndStack.top();
			opndStack.pop();
			opndStack.push(newExpression);
			return;
		}
		if (!optStack.isEmpty() || opndStack.size() > 1)
			throw new TreeException(EXCEPTION_FALSE_ARITHMETIC_EXPRESSION);
		if (opndStack.size() == 1)
			root = (ExpressionNode) opndStack.top();
	}
	
	@Override
	public void insert(Object object) throws TreeException {
		if (object == null)
			throw new TreeException(EXCEPTION_RECEIVED_NULL);
		size++;
		if (!(object instanceof Expression))
			throw new TreeException();
		Expression expression = (Expression) object;
		ExpressionNode newExpression = new ExpressionNode(expression);
		if (expression.isOperator())
			switch (OperatorType.operatorValueOf(expression.getObject())) {
			case KLAMMER_AUF:
				optStack.push(newExpression);
				break;
			case KLAMMER_ZU:
				while (!optStack.isEmpty()) {
					ExpressionNode currentExpression = (ExpressionNode) optStack.top();
					optStack.pop();
					if (OperatorType.valueOf(currentExpression.getData().getObject()).equals(OperatorType.KLAMMER_AUF))
						break;
					else {
						newExpression = (ExpressionNode) optStack.top();
						optStack.pop();
						newExpression.rightChild = (ExpressionNode) opndStack.top();
						opndStack.pop();
						newExpression.leftChild = (ExpressionNode) opndStack.top();
						opndStack.pop();
						opndStack.push(newExpression);
					}
				}
				break;
			default:
				if (optStack.isEmpty())
					optStack.push(newExpression);
				else {
					while (!optStack.isEmpty()) {
						ExpressionNode currentExpression = (ExpressionNode) optStack.top();
						optStack.pop();
						if (OperatorType.operatorValueOf(currentExpression.getData().getObject()) == OperatorType.KLAMMER_AUF)
							optStack.push(currentExpression);
						else if (OperatorType.operatorValueOf(currentExpression.getData().getObject()).getPriority() 
								< OperatorType.operatorValueOf(newExpression.getData().getObject()).getPriority())
							optStack.push(currentExpression);
						else {
							currentExpression.rightChild = (ExpressionNode) opndStack.top();
							opndStack.pop();
							currentExpression.leftChild = (ExpressionNode) opndStack.top();
							opndStack.pop();
							opndStack.push(currentExpression);
						}
						if (OperatorType.operatorValueOf(currentExpression.getData().getObject()).equals(OperatorType.KLAMMER_AUF)
								|| OperatorType.operatorValueOf(currentExpression.getData().getObject()).getPriority() 
								< OperatorType.operatorValueOf(currentExpression.getData().getObject()).getPriority())
							break;
					}
					optStack.push(newExpression);
				}
				break;
			}
		else if (expression.isIdentifier())
			opndStack.push(newExpression);
	}
	
	public String toString() {
		try {
			setRoot();
		} catch (TreeException e) {
			return "";
		}
        StringBuffer sb = new StringBuffer();
        toString(sb, root, " ");
        return sb.toString();
    }

    private void toString(StringBuffer sb, ExpressionNode currentNode, String ausgabe) {
        if (currentNode != null) {
            toString(sb, currentNode.rightChild, ausgabe + OUT_DELIMITER);
            sb.append(ausgabe).append(OUT_DELIMITER + "{" + currentNode.getData() + "; " +currentNode.getValue() + "}" + OUT_NEW_LINE);
            toString(sb, currentNode.leftChild, ausgabe + OUT_DELIMITER);
        }
    }
    
	@Override
	public int size() {
		return size;
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
