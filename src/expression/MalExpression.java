package expression;

import base.OperatorType;
import base.interfaces.Expression;

public class MalExpression implements Expression{

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
		return null;
	}

}
