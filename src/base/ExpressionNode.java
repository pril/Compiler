package base;

import utilities.exceptions.TreeException;
import base.interfaces.Expression;
import base.interfaces.Node;

public class ExpressionNode implements Node<Expression> {
	private static final String EXCEPTION_INCALCULABLE = "Fehlerhafter arithmetischer Ausdruck, oder nicht genug Operanden!";
	private static final String EXCEPTION_BRACKET = "Klammer liefert keine Ergebnisse!";
	private static final String EXCEPTION_DIVISION_ZERO = "Division durch Null!";
	private static final String EXCEPTION_DATA_NULL = "ExpressionNodeConstructor received null as data";
	protected Expression data;
	protected ExpressionNode leftChild;
	protected ExpressionNode rightChild;
	protected ExpressionNode father;

	public ExpressionNode(Expression data) throws TreeException {
		if (data == null)
			throw new TreeException(EXCEPTION_DATA_NULL);
		this.data = data;
	}

	@Override
	public Expression getData() {
		return data;
	}

	public Double getValue() {
		return data.getValue();
	}

	public void calc() {
		if (rightChild != null && leftChild != null){
			leftChild.calc();
			rightChild.calc();
			switch (OperatorType.operatorValueOf(data.getObject())) {
			default:
			case GETEILT:
				if (rightChild.getValue() == 0)
					throw new RuntimeException(EXCEPTION_DIVISION_ZERO);
				data.setValue(leftChild.getValue() / rightChild.getValue());
				break;
			case MAL:
				data.setValue(leftChild.getValue() * rightChild.getValue());
				break;
			case MINUS:
				data.setValue(leftChild.getValue() - rightChild.getValue());
				break;
			case PLUS:
				data.setValue(leftChild.getValue() + rightChild.getValue());
				break;
			}
		}
		else if(!data.isIdentifier())
			throw new RuntimeException(EXCEPTION_INCALCULABLE);
	}

	@Override
	public Node<Expression> getLeftChild() {
		return leftChild;
	}

	@Override
	public Node<Expression> getRightChild() {
		return rightChild;
	}

	@Override
	public boolean isRoot() {
		return father == null;
	}

	@Override
	public boolean isLeave() {
		return leftChild == null && rightChild == null;
	}

	@Override
	public boolean isNode() {
		return !isLeave();
	}
}
