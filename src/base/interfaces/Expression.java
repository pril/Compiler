package base.interfaces;

public interface Expression {

	public boolean isOperator();
	public boolean isIdentifier();
	public boolean isZuordnung();
	public String getObject();
	public Double getValue();
	
}
