package base;

import expression.ExpressionFactory;
import expression.Expressionlist;

/**
 * Parsen von Eingabezeilen und Rueckgabe der gefundenen Werte
 * @author Alexei Felberg
 * @author Daniel
 * @version 07.07.2011 06:41:06
 */
public class Parser {

	private ExpressionFactory expressionfactory = new ExpressionFactory();
	
	public Expressionlist getExpression(String value) throws IllegalArgumentException
	{
		if (value == null) return null;
		if (value.isEmpty()) return null;
		if (value.trim().isEmpty()) return null;
		if (value.replace(" ", "").isEmpty()) return null;

		Expressionlist expressionlist = new Expressionlist();
		String minimalvalue = value.replace(" ","");
		char[] valuelist = minimalvalue.toCharArray();
		StringBuilder sb = new StringBuilder();
		if (minimalvalue.contains("="))
		{
			String[] zuordnungsexpression = minimalvalue.split("=");
			if (zuordnungsexpression.length > 1) throw new IllegalArgumentException("Fehlerhafter Ausdruck '" + value +"'.");
			expressionlist.add(expressionfactory.getZuordnungExpression(zuordnungsexpression[0], Double.valueOf(zuordnungsexpression[1])));
			return expressionlist;
		}
		if (containsOperator(minimalvalue))
		{
			for (char c:valuelist)
			{
				if (equalsOperator(c))
				{
					if (sb.length()!=0)
					{
						expressionlist.add(expressionfactory.getIdentifierExpression(sb.toString()));
						sb.delete(0, sb.length());
					}
					expressionlist.add(expressionfactory.getOperatorExpression(getOperator(c)));
				}
				else
				{
					sb.append(c);
				}
			}	
		}
		if (sb.length()!=0)
		{
			expressionlist.add(expressionfactory.getIdentifierExpression(sb.toString()));
			sb.delete(0, sb.length());
		}
		return expressionlist;
	}
	
	
	private boolean equalsOperator(char c)
	{
		for (OperatorType o:OperatorType.values())
		{
			if (o.getBezeichnung().charAt(0)==c) return true;
		}
		return false;
	}
	
	/**
	 * Prueft ob ein String einen Operator enthält.
	 * @param value
	 * @return
	 */
	private boolean containsOperator(String value)
	{
		char[] valuelist =  value.toCharArray();
		for (char character:valuelist)
		{
			if (equalsOperator(character)) return true;
		}
		return false;
	}
	
	private OperatorType getOperator(char c)
	{
		for (OperatorType o:OperatorType.values())
		{
			if (o.getBezeichnung().charAt(0)==c) return o;
		}
		return null;
	}
}
