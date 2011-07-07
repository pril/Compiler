package base;

public enum OperatorType {
	PLUS("+",1),
	MINUS("-",1),
	MAL("*",0),
	GETEILT("/",0);
	
	private String bezeichnung="";
	private int priority = 0;
	private OperatorType(String bezeichnung,int priotrity)
	{
		if (bezeichnung == null) throw new IllegalArgumentException("Bezeichnung darf nich null sein.");
		if (bezeichnung.isEmpty()) throw new IllegalArgumentException("Bezeichnung darf nich null sein.");
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
	 * Gibt an ob der Opeartor binaer ist.
	 * @return
	 */
	public boolean isBinaer()
	{
		return true;
	}
	

}
