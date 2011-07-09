package expression;


public class IdentifierExpression implements base.interfaces.Expression{

	private Double value;
	private String name;

	public IdentifierExpression(String name) throws IllegalArgumentException
	{
		if (value == null) throw new IllegalArgumentException("Value can't be null");
		this.name = name;
	}
	
	@Override
	public boolean isOperator() {
		return false;
	}

	@Override
	public boolean isIdentifier() {
		return true;
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
		return value;
	}

	@Override
	public void setValue(Double newValue) {
		this.value = newValue;
	}
	
}
