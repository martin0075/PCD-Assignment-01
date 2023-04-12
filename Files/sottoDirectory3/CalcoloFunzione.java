import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CalcoloFunzione 
{
	public static void main(String[]args) 
	{
		try 
		{
			CalcolaFunzione(1,10,"bellaRaga.txt");
		}
		catch(FileNotFoundException e) 
		{
			System.out.print("Errore nella scrittura del file");
		}
	}
	
	public static void CalcolaFunzione(double xStart, double xEnd, String nomeFile) throws FileNotFoundException 
	{
		PrintWriter outputStream=null;
		
		outputStream=new PrintWriter(nomeFile);
		
		while(xStart<=xEnd)
		{
			double result=(xStart*xStart)-xStart+1;
			outputStream.println(xStart+" "+result);
			xStart+=0.5;
		}
		
		outputStream.close();
	}

}
