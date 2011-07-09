package base;

public enum OperatorType {
	PLUS("+",2),
	MINUS("-",2),
	MAL("*",1),
	GETEILT("/",1),
	KLAMMER_AUF("(",0),
	KLAMMER_ZU(")",0);
	
	private String bezeichnung="";
	private int priority = 0;
	private OperatorType(String bezeichnung,int priotrity)
	{
		if (bezeichnung == null) throw new IllegalArgumentException("Bezeichnung darf nich null sein.");
		if (bezeichnung.trim().isEmpty()) throw new IllegalArgumentException("Bezeichnung darf nich null sein.");
		if (priority < 0 ) throw new IllegalArgumentException("Prioritaet muss immer positiv sein.");
		this.bezeichnung = bezeichnung;
		this.priority = priotrity; 
	}
	
	public String toString()
	{
		return bezeichnung;
	}
	/**
	 * Liefert die Bezeichnung als String
	 * @return
	 */
	public String getBezeichnung()
	{
		return bezeichnung;
	}
	/**
	 * Liefert die Prioritaet des Operatoren
	 * @return Operatorprioritaet als int, stehts >=0
	 */
	public int getPriority(){
		return priority;
	}
	/**
	 * Gibt an ob der Opeartor binaer ist.
	 * @return
	 */
	public boolean isBinaer()
	{
		return true;
	}
	

}
