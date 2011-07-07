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
		System.out.println("Ende in " + classname + " de Methode " + methodname);
	}

	@Override
	public void eingabeDateiReaderReadLine(int linenumber, String line) {
		System.out.println(linenumber + ":" + line);
	}

	@Override
	public void eingabeDateiReaderOpen(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eingabeDateiReaderClose(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eingabeDateiReaderReachEOF(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parserRecievedLine(String line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parserFoundZuordnung(String name, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parserFoundIdentifier(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parserFoundOperator(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parserFoundArithmethicExpression(Expressionlist expressionlist) {
		// TODO Auto-generated method stub
		
	}

}
