package expression;

import base.OperatorType;
import base.interfaces.Expression;

public class MalExpression implements Expression{
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
		return OperatorType.MAL.getBezeichnung();
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
