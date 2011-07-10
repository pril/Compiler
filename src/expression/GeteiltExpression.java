package expression;

import base.OperatorType;
import base.interfaces.Expression;
import base.interfaces.Operator;

public class GeteiltExpression implements Expression{
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
		return OperatorType.GETEILT.getBezeichnung();
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

}
