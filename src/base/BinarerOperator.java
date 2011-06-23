package base;
/**
 * BinaereOperatoren sind Operatoren die mindestens zwei Identifier besitzen.
 * Die Identifier sind + - * /
 * @author Daniel Rhein
 * @author Alexei Felberg
 * @version 22.06.2011
 */
public interface BinarerOperator<Identifiertype,Resultvalue> extends Operator<Identifiertype,Resultvalue> {
	//=======================================KONSTANTEN====================================
	
	//=======================================METHODENRUEMPFE===============================
	/**
	 * Legt die zweite Variable fest
	 */
	public void setSecondIdent(Identifier<Identifiertype> var) throws IllegalArgumentException;
	
	
}
