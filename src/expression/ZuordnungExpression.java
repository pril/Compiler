package expression;

import base.interfaces.Expression;


public class ZuordnungExpression implements Expression{

	private String name = "";
	private Double value;
	
	public ZuordnungExpression(String name,Double value) throws IllegalArgumentException
	{
		if (name == null) throw new IllegalArgumentException("Name darf nicht null sein.");
		if (value == null) throw new IllegalArgumentException("Value darf nicht null sein.");
		if (name.isEmpty()) throw new IllegalArgumentException("Name darf nicht leer sein.");
		if (value.isInfinite()) throw new IllegalArgumentException("Value darf nicht unendlich sein.");
		if (value.isNaN()) throw new IllegalArgumentException("Value muss eine Nummer sein.");
		this.name = name;
		this.value = value;
	}
	
	@Override
	public boolean isOperator() {
		return false;
	}

	@Override
	public boolean isIdentifier() {
		return false;
	}

	@Override
	public boolean isZuordnung() {
		return true;
	}

	@Override
	public String getObject() {
		return name;
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
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IdentifierExpression)
		{
			return getObject().equals(((IdentifierExpression)obj).getObject());	
		}
		return getObject().equals(obj);
	}

	public String toString()
	{
		return getObject();
	}
}
