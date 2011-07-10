import utilities.exceptions.FileException;
import utilities.exceptions.TreeException;
import base.EingabeDateiReader;
import base.ExpressionTree;
import base.HashItem;
import base.HashTableImpl;
import base.Parser;
import base.exceptions.CompilerExceptions;
import base.exceptions.FileReaderException;
import base.exceptions.HashItemException;
import base.exceptions.HashTableException;
import base.interfaces.Beobachter;
import base.interfaces.EingabeDateiReaderBeobachter;
import base.interfaces.Expression;
import base.interfaces.ParserBeobachter;
import expression.Expressionlist;


public class Compiler implements ParserBeobachter,EingabeDateiReaderBeobachter,Beobachter{

	
	public Compiler(String args[])
	{
		boolean zuordnung =false;
		boolean arithmetischerausdruck = false;
		EingabeDateiReader eingabedateireader = new EingabeDateiReader();
		ExpressionTree expressiontree = new ExpressionTree();
		HashTableImpl<Expression,Double> hashtable = new HashTableImpl<Expression, Double>(100);
		Parser parser = new Parser();
		String line = "";
		Expressionlist expressionlist = new Expressionlist();
		eingabedateireader.addBeobachter(this);
		parser.addBeobachter(this);
		if (args.length>=1)
		{
			for (String argumente:args)
			{
			try
			{
			eingabedateireader.setFileName(argumente);
			eingabedateireader.openFile();
			while(!eingabedateireader.isEof())
			{
			 line = eingabedateireader.readLine();
			 expressionlist = parser.getExpression(line);
			 if (expressionlist != null)
			 {
				 for (int i =0;i<expressionlist.size();i++)
				 {
					 if (expressionlist.isOperator())
					 {
						 
						 if (expressionlist.get(i).isOperator())
						 {
							 expressiontree.insert(expressionlist.get(i));
							 
						 }
						 if (expressionlist.get(i).isIdentifier())
						 {
							 if (!hashtable.containsKey(expressionlist.get(i)))
								 throw new CompilerExceptions("Identifier " + expressionlist.get(i).getObject() + " ist unbekannt.");
							 else
							 {
								 expressiontree.insert(hashtable.get(expressionlist.get(i)).getKey());
							 }
						 }
						 arithmetischerausdruck=true;
					 }
					 if (expressionlist.get(i).isZuordnung())
					 {
						 if (!hashtable.containsKey(expressionlist.get(i)))
						 	throw new CompilerExceptions("Identifier " + expressionlist.get(i).getObject() + " ist unbekannt.");
						 hashtable.get(expressionlist.get(i)).getKey().setValue(expressionlist.get(i).getValue());
						 //hashitem.setValue(expressionlist.get(i).getValue());
							 zuordnung = true;
					 }
					 if (expressionlist.get(i).isIdentifier())
					 {
						 hashtable.insert(expressionlist.get(i));
						 
					 }
				 }
				 if (arithmetischerausdruck==true)
				 {
					 System.out.println("Inhalt der Hashtable:"+hashtable.toString());
					 System.out.println("Expression Tree ausgabe:\n"+ expressiontree.toString());
					 arithmetischerausdruck=false;
				 }
				 
			 }
			 else
			 {
				 if (zuordnung==true)
				 {
				 System.out.println("Inhalt der Hashtable:"+hashtable.toString());
				 System.out.println("Expression Tree ausgabe:\n"+ expressiontree.toString());
				 System.out.println("Auswertung des Expressiontrees ergibt:" + expressiontree.calc());
				 zuordnung = false;
				 }
				 
			 }
			}
			if (zuordnung==true)
			 {
			 System.out.println("Expression Tree ausgabe:\n"+ expressiontree.toString());	
			 System.out.println("Auswertung des Expressiontrees ergibt:" + expressiontree.calc());
			 zuordnung = false;
			 }
			}
			catch(FileReaderException exception)
			{
				exception.printStackTrace();
			}
			catch(FileException exception)
			{
				exception.printStackTrace();
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			catch(HashItemException exception)
			{
				exception.printStackTrace();
			}
			catch(HashTableException exception)
			{
				exception.printStackTrace();
			}
			catch(CompilerExceptions exception)
			{
				exception.printStackTrace();
			}
			catch(TreeException exception)
			{
				exception.printStackTrace();
			}
		}
		}
	}
	
	/**
	 * Main Methode
	 * @param args
	 */
	public static void main(String args[])
	{
		Compiler compiler = new Compiler(args);
	}
	
	@Override
	public void startMethod(String classname, String methodname) {
		System.out.println("Starte in " + classname + " bei methode " + methodname);
	}

	@Override
	public void endMethod(String classname, String methodname) {
		System.out.println("Ende in " + classname + " der Methode " + methodname);
	}

	@Override
	public void eingabeDateiReaderReadLine(int linenumber, String line) {
		System.out.println("Lese");
		if (line == null)
		{
			System.out.println(linenumber + ": Leerzeile");
		}
		else
		{
			System.out.println(linenumber + ":" + line);
		}
	}

	@Override
	public void eingabeDateiReaderOpen(String file) {
		System.out.println("Oeffne Datei:" + file);
	}

	@Override
	public void eingabeDateiReaderClose(String file) {
		System.out.println("Schliese Datei:" + file);
	}

	@Override
	public void eingabeDateiReaderReachEOF(String file) {
		System.out.println("Erreiche Dateiende von Datei " + file);
	}

	@Override
	public void parserReceivedLine(String line) {
		System.out.println("Parser liest " + line);
		
	}

	@Override
	public void parserFoundZuordnung(String name, String value) {
		System.out.println("Parser findet Zuordnung " + name + "=" + value);
	}

	@Override
	public void parserFoundIdentifier(String name) {
		System.out.println("Parser findet Identifier " + name );
	}

	@Override
	public void parserFoundOperator(String name) {
		System.out.println("Parser findet Operator " + name );
	}

	@Override
	public void parserFoundArithmethicExpression(Expressionlist expressionlist) {
		System.out.println("Finde Arithmetischen Ausdruck" + expressionlist );
	}

}
