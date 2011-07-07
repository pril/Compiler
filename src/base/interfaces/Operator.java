package base.interfaces;

import base.OperatorType;

/**
 * Diese Klasse bildet Operatoren ab.
 * Operatoren sind:
 * <table>
 * <tr>
 * <td>+</td><td>-</td><td>*</td><td>/</td><td>[](){}</td>
 * </tr>
 * <tr>
 * <td>Plus</td><td>Minus</td><td>Mal</td><td>Geteilt</td><td>Beliebige Klammerung</td>
 * <tr>
 * </table>
 * @author Daniel Rhein
 * @author Alexei Felberg
 * @version 22.06.2011
 */
public interface Operator<IdentifierType,Resultvalue> {
	//=======================================KONSTANTEN====================================
	public static final String ERROR_IDENTIFYER_NULL = "Identifier darf nicht null sein.";
	public static final String ERROR_IDENTIFYER_EMPTY = "Identifier darf nicht leer sein.";
	//=======================================METHODENRUEMPFE==================================
	/**
	 * Gibt an, ob die Operation Binaer ist
	 * @return
	 */
	public boolean isBinaer();
	/**
	 * Gibt an, ob die Operation Unear ist
	 * @return
	 */
	public boolean isUnear();
	/**
	 * Legt die erste Variable fest
	 */
	public void setFirstIdent(Identifier<IdentifierType> var) throws IllegalArgumentException;
	/**
	 * Berechtnung der angegeben Identifier mit dem festgelegten Operator
	 * @return
	 */
	public Resultvalue calc(); 
	/**
	 * Legt den Operator fest.
	 * @param operatortype
	 */
	public void setOperator(OperatorType operatortype);
}
