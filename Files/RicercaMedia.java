import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class RicercaMedia 
{
	public static void main(String[]args) 
	{
		Scanner scan=new Scanner(System.in);
		System.out.print("Inserisci il nome del file di testo da leggere: ");
		String nomeFile=scan.nextLine();
		
		System.out.print("Inserisca il nome del file binario da scrivere: ");
		String nomeFileBinario=scan.nextLine();
		
		try 
		{
			operazioniFiletxt(nomeFile,nomeFileBinario);
		}
		catch(FileNotFoundException e) 
		{
			System.out.print("Errore nell'apertura del file.");
		}
		catch(IOException e) 
		{
			System.out.print("errore nell'apertura del file binario.");
		}
		
		
	}
	
	static void operazioniFiletxt(String nomeFile, String nomeFileBinario) throws IOException, FileNotFoundException
	{
		System.out.print("Leggo file di testo in input: "+nomeFile);
		File fileBin=new File(nomeFileBinario);
		
		File parentFolder=fileBin.getParentFile();
		if(parentFolder!=null && !parentFolder.exists())
			parentFolder.mkdirs();
		
		Scanner inputStream=new Scanner(new File(nomeFile));
		ObjectOutputStream outPutStream= new ObjectOutputStream(new FileOutputStream(fileBin));
		
		double max=Double.MIN_VALUE;
		double min=Double.MAX_VALUE;
		double somma=0;
		int n=0;
		
		
		
		while(inputStream.hasNextLine()) 
		{
			double numero=inputStream.nextDouble();
			if(numero>max)
				max=numero;
			if(numero<min)
				min=numero;
			somma+=numero;
			n++;
			outPutStream.writeDouble(numero);
			
		}
		
		double media=somma/n;
		inputStream.close();
		outPutStream.close();
		
		System.out.print("\nmin: "+min+"\n");
		System.out.print("max: "+max+"\n");
		System.out.print("media: "+media+"\n");
		
		
	}
	
	static void operazioniFileBinario(String nomeFile, String nomeFileBinario) throws IOException, FileNotFoundException
	{
		System.out.print("Leggo file binario in input: "+nomeFileBinario);
		File fileTxt=new File(nomeFile);
		
		File parentFolder = fileTxt.getParentFile();
		if(parentFolder != null && !parentFolder.exists()){
			parentFolder.mkdirs();
		}
		
		PrintWriter outPutStream=new PrintWriter(nomeFile);
		ObjectInputStream inputStream = 
				new ObjectInputStream(new FileInputStream(nomeFileBinario));
		
		double max=Double.MIN_VALUE;
		double min=Double.MAX_VALUE;
		double somma=0;
		int n=0;
		
		
		
		while(inputStream.available()>0) 
		{
			double numero=inputStream.readDouble();
			if(numero>max)
				max=numero;
			if(numero<min)
				min=numero;
			somma+=numero;
			n++;
			outPutStream.println(numero);
			
		}
		
		double media=somma/n;
		inputStream.close();
		outPutStream.close();
		
		System.out.print("\nmin: "+min+"\n");
		System.out.print("max: "+max+"\n");
		System.out.print("media: "+media+"\n");
		
		
	}

}
