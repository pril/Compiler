package expression;

public class IdentifierExpression implements base.interfaces.Expression {

	private String value = "";

	public IdentifierExpression(String value) throws IllegalArgumentException {
		if (value == null)
			throw new IllegalArgumentException("Value can't be null");
		this.value = value;
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
		return value;
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
	public int hashCode() {
		char[] chararray = value.toCharArray();
		int indexkey = 0;
		// Ermittele index-Key fuer Chararray
		for (int i = 0; i < chararray.length; i++) {
			indexkey = indexkey + ((int) chararray[i] * i);
		}

		return indexkey;
	}

}
