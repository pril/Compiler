import base.EingabeDateiReader;
import base.Parser;
import base.interfaces.Beobachter;
import base.interfaces.EingabeDateiReaderBeobachter;
import base.interfaces.ParserBeobachter;
import expression.Expressionlist;


public class Compiler implements ParserBeobachter,EingabeDateiReaderBeobachter,Beobachter{

	
	public Compiler(String args[])
	{
		EingabeDateiReader eingabedateireader = new EingabeDateiReader();
		Parser parser = new Parser();
		if (args.length>=1)
		{
			for (String argumente:args)
			{
			
			eingabedateireader.addBeobachter(this);
			eingabedateireader.setFileName(argumente);
			
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMethod(String classname, String methodname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eingabeDateiReaderReadLine(int linenumber, String line) {
		// TODO Auto-generated method stub
		
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
