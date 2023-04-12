import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SenzaDuplicati 
{
	public static void main(String[]args)  
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("Inserire il nome del file dove leggere i dati: ");
		String nomeFileIniziale=scan.nextLine();
		System.out.println("Inserire il nome del file dove scrivere i dati: ");
		String nomeFileFinale=scan.nextLine();
		
		try 
		{
			scriviSenzaDuplicati(nomeFileIniziale, nomeFileFinale);
		}
		catch(FileNotFoundException e) 
		{
			System.out.print("Errore nel caricamento del file.");
		}
		catch(IOException e) 
		{
			System.out.print("Errore nel carimento del file su cui scrivere.");
		}
	}
	
	static void scriviSenzaDuplicati(String nomeFileIniziale, String nomeFileFinale) throws FileNotFoundException, IOException 
	{
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeFileIniziale));
		
		File outFile=new File(nomeFileFinale);
		File parent=outFile.getParentFile();
		if(parent!=null && !parent.exists())
			parent.mkdirs();
		
		ArrayList<Integer>list=new ArrayList<Integer>();
		
		System.out.print("File di input contiene: \n");
		while(inputStream.available()>0) 
		{
			Integer numero=inputStream.readInt();
			System.out.println(numero);
			if(!list.contains(numero))
				list.add(numero);
			
		}
		inputStream.close();
		
		ObjectOutputStream outPutStream=new ObjectOutputStream(new FileOutputStream(outFile));
		System.out.print("\nFile di input contiene: ");
		for(Integer numbers:list) 
		{
			System.out.println(numbers);
			outPutStream.writeInt(numbers);
		}
		
		
		
		outPutStream.close();
		
		
		
	}

}
