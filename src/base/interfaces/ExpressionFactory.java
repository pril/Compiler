package base.interfaces;


/**
 * Eine Factory, liefert bestimmte Expression Informationen
 * @author Alexei Felberg
 * @author Daniel
 * @version 07.07.2011 06:49:24
 */
public interface ExpressionFactory {

	/**
	 * Returns an MinusExpression
	 * @return
	 */
	public Expression getMinusExpression();
	/**
	 * Returns an PlusExpression
	 * @return
	 */
	public Expression getPlusExpression();
	/**
	 * Returns an MalExpression
	 * @return
	 */
	public Expression getMalExpression();
	/**
	 * Returns an DivisorExpression
	 * @return
	 */
	public Expression getDivisonExpression();
	/**
	 * Return IdentifierExpression
	 * @throws IllegalArgumentExecption
	 * @return
	 * 
	 */
	public Expression getIdentifierExpression(String name) throws IllegalArgumentException;
	/**
	 * Ein Zuordnungsausdruck besteht aus einem Identifier mit namen und einem Wert.
	 * @param name
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Expression getZuordnungExpression(String name,Double value) throws IllegalArgumentException;
	
}
