package expression;

public class IdentifierExpression implements base.interfaces.Expression {

	private Double value;
	private String name;

	public IdentifierExpression(String name) throws IllegalArgumentException
	{
		if (name == null) throw new IllegalArgumentException("Value can't be null");
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

	@Override
	public int hashCode() {
		char[] chararray = name.toCharArray();
		int indexkey = 0;
		// Ermittele index-Key fuer Chararray
		for (int i = 0; i < chararray.length; i++) {
			indexkey = indexkey + ((int) chararray[i] * i);
		}

		return indexkey;
	}

}
