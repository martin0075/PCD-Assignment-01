import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BinarioToTxt 
{
	public static void main(String []args) 
	{
		System.out.println("Inserisci il nome del file binario da leggere: ");
		Scanner scan=new Scanner(System.in);
		
		String fileInput=scan.nextLine();
		System.out.println("Inserisci il nome del file binario da scrivere: ");
		String fileOutput=scan.nextLine();
		
		try 
		{
			leggiIntODouble(fileInput, fileOutput);
		}
		catch(FileNotFoundException e) 
		{
			System.out.print("Errore nell'apertura del file da leggere.");
		}
		catch(IOException e) 
		{
			System.out.print("Errore nell'apertura del file da scrivere.");
		}
	}
	
	static void leggiIntODouble(String fileInput,String fileOutput) throws FileNotFoundException, IOException 
	{
		ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(fileInput));
		
		PrintWriter outputStream=new PrintWriter(new File(fileOutput));
		
		String sequenza=inputStream.readUTF();
		
		int num=0;
		double val=0;
		
		for(char c:sequenza.toCharArray()) 
		{
			System.out.print(c+" ");
			if(c=='i') 
			{
				num=inputStream.readInt();
				outputStream.println(num);
				System.out.print(num+", ");
			}
			else if(c=='d') 
			{
				val=inputStream.readDouble();
				outputStream.println(val);
				System.out.print(val+", ");
			}
			
		}
		
		inputStream.close();
		outputStream.close();
		
	}

}
