package expression;

import base.OperatorType;
import base.interfaces.Expression;


public class ExpressionFactory implements base.interfaces.ExpressionFactory {

	@Override
	public Expression getMinusExpression() {
		return new MinusExpression();
	}

	@Override
	public Expression getPlusExpression() {
		return new PlusExpression();
	}

	@Override
	public Expression getMalExpression() {
		return new MalExpression();
	}

	@Override
	public Expression getDivisonExpression() {
		return new GeteiltExpression();
	}

	@Override
	public Expression getIdentifierExpression(String name)
			throws IllegalArgumentException {
		return new IdentifierExpression(name);
	}

	@Override
	public Expression getZuordnungExpression(String name, Double value)
			throws IllegalArgumentException {
		return new ZuordnungExpression(name,value);
	}

	@Override
	public Expression getOperatorExpression(OperatorType operator)
			throws IllegalArgumentException {
		switch(operator)
		{
		case GETEILT:
			return getDivisonExpression();
		case MAL:
			return getMalExpression();
		case MINUS:
			return getMinusExpression();
		case PLUS:
			return getPlusExpression();
		}
		return null;
	}
	
	
	
}
