package expression;

import base.Operator;
import base.OperatorType;
import base.interfaces.Expression;

public class GeteiltExpression implements Expression{

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
		return null;
	}

}
