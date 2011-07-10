package expression;

import base.OperatorType;

public class PlusExpression implements base.interfaces.Expression{
	private Double value;
	
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
		return OperatorType.PLUS.getBezeichnung();
	}

	@Override
	public boolean isZuordnung() {
		return false;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void setValue(Double newValue) {
		this.value = newValue;
	}
	
	public String toString()
	{
		return getObject();
	}
}
