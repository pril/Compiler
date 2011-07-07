package base;

import java.util.Iterator;

import utilities.folz_list_klassen.normal.DList;
import base.interfaces.Beobachtbar;
import base.interfaces.Beobachter;
import base.interfaces.EingabeDateiReaderBeobachter;
import base.interfaces.ParserBeobachter;
import expression.ExpressionFactory;
import expression.Expressionlist;

/**
 * Parsen von Eingabezeilen und Rueckgabe der gefundenen Werte
 * @author Alexei Felberg
 * @author Daniel
 * @version 07.07.2011 06:41:06
 */
public class Parser implements Beobachtbar{

	//=============================Global Variables====================//
	private ExpressionFactory expressionfactory = new ExpressionFactory();
	private DList beobachterliste = new DList();
	//=============================Methoden====================//
	
	/**
	 * Werte einen Ausdruck aus und liefert die enthaltenen Expressions zurueck.
	 * @param value
	 * @return ExpressionList
	 * Liste von Ausdruecken.
	 * @throws IllegalArgumentException
	 */
	public Expressionlist getExpression(String value) throws IllegalArgumentException
	{
		sendeStarteMethode(getClass().getCanonicalName(),"getExpression");
		//Fehlerbehandlung
		if (value == null) return null;
		if (value.isEmpty()) return null;
		if (value.trim().isEmpty()) return null;
		if (value.replace(" ", "").isEmpty()) return null;
		
		//Variablen
		Expressionlist expressionlist = new Expressionlist();
		String minimalvalue = value.replace(" ","").trim();
		char[] valuelist = minimalvalue.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		
		if (minimalvalue.contains("=")) //Zurodnung
		{
			String[] zuordnungsexpression = minimalvalue.split("=");
			//TODO:Verbessern.
			if (zuordnungsexpression.length > 2 || zuordnungsexpression.length < 2) throw new IllegalArgumentException("Fehlerhafter Ausdruck '" + value +"'.");
			sendeFoundZuordnung(zuordnungsexpression[0], zuordnungsexpression[1]);
			expressionlist.add(expressionfactory.getZuordnungExpression(zuordnungsexpression[0], Double.valueOf(zuordnungsexpression[1])));
			return expressionlist;
		}
		
		if (containsOperator(minimalvalue)) //Prueft ob der Ausdruck einen Operator enthaelt.
		{
			for (char c:valuelist) 
			{
				if (equalsOperator(c)) //Ist der Character ein Operator
				{
					if (sb.length()!=0)
					{
						sendeFoundIdentifier(sb.toString());
						expressionlist.add(expressionfactory.getIdentifierExpression(sb.toString())); //Fuegt den gefundenen Identifier zur Liste hinzu
						sb.delete(0, sb.length());
					}
					OperatorType operatortyp= getOperator(c);
					sendeFoundOperator(operatortyp.getBezeichnung());
					expressionlist.add(expressionfactory.getOperatorExpression(operatortyp)); //Operator hinzufuegen.
				}
				else //Kein Operator
				{
					sb.append(c); //Sammele die Informationen.
				}
			}	
		}
		if (sb.length()!=0) //Noch fehlende Operatoren hinzufuegen.
		{
			expressionlist.add(expressionfactory.getIdentifierExpression(sb.toString()));
			sb.delete(0, sb.length());
		}
		sendeEndMethode(getClass().getCanonicalName(),"getExpression");
		return expressionlist;
	}
	
	/**
	 * Entspricht der Charachter einen bestimmten operator
	 * @param c
	 * @return
	 */
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
	
	/**
	 * Liefert zu einem Character einen entsprechenden Operator, falls es diesen gibt.
	 * Andernfalls liefert die Methode null zurueck.
	 * @param c
	 * @return
	 */
	private OperatorType getOperator(char c)
	{
		for (OperatorType o:OperatorType.values())
		{
			if (o.getBezeichnung().charAt(0)==c) return o;
		}
		return null;
	}

	//==================================Beobachter===================================
	@Override
	public void addBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		//TODO:Hier kann es zu ueberschreibungen kommen. Aendern. Daniel
		beobachterliste.add(beobachterliste.size(), beobachter);
	}

	@Override
	public void removeBeobachter(Beobachter beobachter)
			throws IllegalArgumentException {
		beobachterliste.remove(beobachter);
	}
	//============================NACHRICHTEN AN BEOBACHTER=============================

	private void sendeRecieveLine(String line)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			ParserBeobachter beobachter = (ParserBeobachter)iterator.next();
			beobachter.parserRecievedLine(line);
		}
		while(iterator.hasNext());
		
	}

	private void sendeFoundZuordnung(String name,String value)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			ParserBeobachter beobachter = (ParserBeobachter)iterator.next();
			beobachter.parserFoundZuordnung(name, value);
		}
		while(iterator.hasNext());
	}
	
	private void sendeFoundIdentifier(String name)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			ParserBeobachter beobachter = (ParserBeobachter)iterator.next();
			beobachter.parserFoundIdentifier(name);
		}
		while(iterator.hasNext());
	}
	
	private void sendeFoundOperator(String name)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			ParserBeobachter beobachter = (ParserBeobachter)iterator.next();
			beobachter.parserFoundOperator(name);
		}
		while(iterator.hasNext());
	}

	private void sendeStarteMethode(String classname,String methodname)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			Beobachter beobachter = (Beobachter)iterator.next();
			beobachter.startMethod(classname, methodname);
		}
		while(iterator.hasNext());
	}

	private void sendeEndMethode(String classname,String methodname)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			Beobachter beobachter = (Beobachter)iterator.next();
			beobachter.endMethod(classname, methodname);
		}
		while(iterator.hasNext());
	}
	
	private void sendeFoundArithmethicExpression(Expressionlist expressionlist)
	{
		Iterator iterator = beobachterliste.iterator();
		do
		{
			ParserBeobachter beobachter = (ParserBeobachter)iterator.next();
			beobachter.parserFoundArithmethicExpression(expressionlist);
		}
		while(iterator.hasNext());
	}

}
