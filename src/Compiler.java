import utilities.exceptions.FileException;
import base.EingabeDateiReader;
import base.Parser;
import base.exceptions.FileReaderException;
import base.interfaces.Beobachter;
import base.interfaces.EingabeDateiReaderBeobachter;
import base.interfaces.ParserBeobachter;
import expression.Expressionlist;


public class Compiler implements ParserBeobachter,EingabeDateiReaderBeobachter,Beobachter{

	
	public Compiler(String args[])
	{
		EingabeDateiReader eingabedateireader = new EingabeDateiReader();
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
					 if (expressionlist.get(i).isOperator() || expressionlist.get(i).isIdentifier())
					 {
						//Arithmetischer ausdruck. 
					 }
					 if (expressionlist.get(i).isZuordnung())
					 {
						 //HashMap??=
					 }
					 
				 }
			 
			 }
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
