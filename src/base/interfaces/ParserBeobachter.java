package base.interfaces;

import expression.Expressionlist;

public interface ParserBeobachter extends Beobachter{
	public void parserRecievedLine(String line);
	public void parserFoundZuordnung(String name,String value);
	public void parserFoundIdentifier(String name);
	public void parserFoundOperator(String name);
	public void parserFoundArithmethicExpression(Expressionlist expressionlist);
}
