package base;

import utilities.exceptions.TreeException;
import base.interfaces.Expression;
import base.interfaces.Node;

public class ExpressionNode implements Node<Expression> {
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
