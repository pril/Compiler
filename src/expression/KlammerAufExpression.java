package expression;

import base.interfaces.Expression;

public class KlammerAufExpression implements Expression {

	private String name = "(";

	@Override
	public boolean isOperator() {
		return true;
	}
	
	@Override
	public boolean isIdentifier() {
		return false;
	}

	@Override
	public String getObject() {
		return name;
	}

	@Override
	public boolean isZuordnung() {
		return false;
	}

	@Override
	public Double getValue() {
		return null;
	}

	@Override
	public void setValue(Double newValue) {
	}

}