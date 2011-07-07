package expression;

import java.util.Iterator;

import base.interfaces.Expression;
import utilities.folz_list_klassen.normal.DList;

/**
 * Liste von Expressions
 * @author Alexei Felberg
 * @author Daniel
 * @version 07.07.2011 08:23:48
 */
public class Expressionlist {

	private static final String ERROR_INDEX_OUT_OF_BOUNDS = "Index ist groesser als die Feldgroesse der Liste";
	private static final String ERROR_INDEX_NEGATIV = "Index muss positiv sein.";
	private static final String ERROR_EXPRESSION_NULL = "Expression darf nicht null sein.";
	DList expressionlist;
	private boolean operator=false;
	
	
	public Expressionlist()
	{
		expressionlist = new DList();
	}
	
	public void add(Expression expression) throws IllegalArgumentException
	{
		checkExpression(expression);
		if (expression.isOperator()) operator = true;
		expressionlist.add(expressionlist.size(), expression);
	}
	
	private void checkExpression(Expression expression) throws IllegalArgumentException
	{
		if (expression == null) throw new IllegalArgumentException(ERROR_EXPRESSION_NULL);
	}
	
	public void remove(Expression expression) throws IllegalArgumentException
	{
		checkExpression(expression);
		expressionlist.remove(expression);
	}
	
	/**
	 * Liefert eine Expression in der liste
	 * @param index
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Expression get(int index) throws IllegalArgumentException
	{
		if (index < 0) throw new IllegalArgumentException(ERROR_INDEX_NEGATIV);
		if (index == expressionlist.size()) throw new IllegalArgumentException(ERROR_INDEX_OUT_OF_BOUNDS);
		return (Expression)expressionlist.get(index);
	}
	
	public int size()
	{
		return expressionlist.size();
	}
	/**
	 * Gibt an ob ein Operator vorhanden ist oder nicht.
	 * @return
	 */
	public boolean isOperator() {
		return operator;
	}
	/**
	 * 
	 */
	public String toString()
	{
		Iterator iterator = expressionlist.iterator();
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext())
		{
			Expression expression = (Expression)iterator.next();
			if (expression.isZuordnung())
			{
				sb.append(expression.getObject());
				sb.append("=");
				sb.append(expression.getValue());
			}
			else
			{
				sb.append(expression.getObject());
			}
		}
		return sb.toString();
	}
	
}
