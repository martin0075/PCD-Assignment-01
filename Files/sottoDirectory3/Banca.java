package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/* Classe Banca si occupa di gestire le operazioni eseguite sui conti correnti:
 * -inseriemento dei conti correnti in una lista(lettura da file), 
 * -trovare il conto del cliente che ha effetuato l'accesso,
 * -salvare il nuovo saldo del cliente in seguito ad una determinata operazione.
 * La classe in oltre si occupa di gestire i movimenti effettuati sul conto allo stesso modo definito in perecedenza ovvero:
 * -inserimento di tutti i movimenti su una lista(lettura da file)
 * -salavataggio dei nuovi movimenti effettuati sul file.
 */

public class Banca 
{
	
	private int contoAttuale;
	
	public Banca() 
	{
		
	}
	
	public int getContoAttuale() {return contoAttuale;}
	
	/*
	 * metodo che si occupa di popolare una lista, con tutti i conti
	 * memorizzati in un file di testo;
	 * inoltre, insieme al nomeFile e lista, viene passato come parametro anche una variabile di tipo alert
	 * utilizzata per comunicare all'utente eventuali errori sulla lettura/caricamento del file
	 */
	public void insertContiCorrenti(String nomeFile, ArrayList<Conto> listaConti, Alert errore)
	{
		
		BufferedReader inputStream=null;
		try 
		{
			inputStream=new BufferedReader(new FileReader(nomeFile));
		}
		catch(FileNotFoundException e) 
		{
			errore.setContentText("Errore nel caricamento del database");
    		errore.show();
		}
		
		boolean letturaRighe=false;
		String codiceConto=""; 
		int	pin=0;
		double saldo=0;
		String fileMovimenti="";
		
		try 
		{
			while(!letturaRighe) 
			{
				String line=inputStream.readLine();
				if(line==null)
					letturaRighe=true;
				if(line!=null)
				{
					//l'oggetto di tipo StringTokenizer è necessario per suddividere la stringa passata come argomento del costruttore
					//in più parole(token) in base al tipo di seperatore, che se viene omesso nel costruttore, di default
					//è " "
					StringTokenizer token=new StringTokenizer(line);
					codiceConto=token.nextToken();
					pin=Integer.parseInt(token.nextToken());
					saldo=Double.parseDouble(token.nextToken());
					fileMovimenti=token.nextToken();
					Conto conto=new Conto(codiceConto, pin, saldo,fileMovimenti);
					listaConti.add(conto);
				}
			
			}
			
			inputStream.close();
		}
		catch(IOException e) 
		{
			errore.setContentText("Errore nella lettura dei dati dal database.");
    		errore.show();
		}
	}
	
	
	/* metodo che si occupa di restituire il conto associato al cliente, 
	 * viene richiamato il metodo "verificaCliente" della classe conto che restituisce true se
	 * credenziali inserite dall'utente corrispondono ad uno dei conti memorizzati dul database(file di testo).
	 * Nel caso in cui metodo "verifiCliente" restituisca true il metodo "trovaConto" restiuisce il conto sul quale
	 * è stato richiamato il metodo citato in precendenza altrimenti se non trova nessuno conto restituisce null
	 * e ciò significa che l'utente non ha inserito in modo corretto le credenziali.
	 * 
	 */
	public Conto trovaConto(String codiceConto, int pin, ArrayList<Conto> listaConti) 
	{
		for(int i=0;i<listaConti.size();i++) 
		{
			Conto conto=listaConti.get(i);
			if(conto.verificaCliente(codiceConto, pin)) 
			{
				contoAttuale=i;
				return conto;
			}
			
		}
		return null;
		
	}
	
	
	/* metodo che si occupa di effettura l'aggiornamento del saldo del conto ogni volta che viene 
	 * eseguita qualsiasi tipo di operazione.
	 * inoltre, insieme al nomeFile e lista, viene passato come parametro anche una variabile di tipo alert
	 * utilizzata per comunicare all'utente eventuali errori sulla scrittura del file (salvataggio dei dati)
	 */
	public void aggiornaContoCorrente(ArrayList<Conto> listaConto, String nomeFile, Alert errore) 
	{
		
		PrintWriter outputStream=null;
		try 
		{
			outputStream=new PrintWriter(nomeFile);
		}
		catch(FileNotFoundException e) 
		{
			errore.setContentText("Errore nel salvatggio dei dati sul database");
    		errore.show();
		}
		
		
		for(int i=0; i<listaConto.size();i++) 
		{
			outputStream.print(listaConto.get(i).getContoCorrente()+" "+listaConto.get(i).getPin()+" "+listaConto.get(i).getSaldo()+" "+listaConto.get(i).getFileMovimenti()+"\n");
		}
		
		outputStream.close();
	}
	
	/* metodo che restitusce una "ObservableList", ovvero una lista che permette di visualizzare il suo contenuto all'interno di una tabella.
	 * Ho optato per questa scelta per rappresentare l'estratto di una banca in modo abbsatanza fedele alla realtà.
	 * Per il resto il metodo si occupa di caricare tutti i movimenti, che sono presenti sul file (lettura), sulla lista 
	 * che poi verrà restituita dal metodo.
	 * Anche in questo caso è presente un variabile Alert, che in caso di errore nella lettura/caricamento del file,
	 * comunica l'utente l'accaduto.
	 * 
	 */
	public ObservableList<Movimenti> caricaEstrattoConto(String nomeFile, Alert errore) 
	{
		BufferedReader inputStream=null;
		try 
		{
			inputStream=new BufferedReader(new FileReader(nomeFile));
		}
		catch(FileNotFoundException e) 
		{
			errore.setContentText("Errore nel caricamento del database");
    		errore.show();
		}
		
		boolean letturaRighe=false;
		String data;
		String descrizione="";
		double importo=0;
		double saldo=0;
		
		ObservableList<Movimenti> list=FXCollections.observableArrayList();
		
		try 
		{
			while(!letturaRighe) 
			{
				String line=inputStream.readLine();
				if(line==null)
					letturaRighe=true;
				if(line!=null)
				{
					//in questo caso è stato necessario specificare il tipo di separatore, per permettere
					//di memmorizzare anche la descrione del movimento che può essere costituita da spazi
					StringTokenizer token=new StringTokenizer(line,"/");
					data=token.nextToken();
					descrizione=token.nextToken();
					importo=Double.parseDouble(token.nextToken());
					saldo=Double.parseDouble(token.nextToken());
					
					list.add(new Movimenti(data,descrizione,importo,saldo));
					
				}
			
			}
			
			inputStream.close();
		}
		catch(IOException e) 
		{
			errore.setContentText("Errore nella lettura del database.");
    		errore.show();
		}
		

        
       
		return list;
	}
	
	/* metodo che provvede all'aggiornamento del file sul quale sono memorizzati tutti i movimenti effettuati sul conto.
	 * Ogni volta che viene richiamato viene aggiunta una riga al file, che individua quel determinato movimento, per questo 
	 * è necessario passare come parametro del metodo la data dell'operazione, una breve descrizione, l'importo, il saldo attuale,
	 * il nome del file relativo ai movimenti di quel determinato conto e una varibile di tipo Alert per comunicare all'utente 
	 * eventuali errori nella scrittura del file. 
	 */
	public void aggiornaEstrattoConto(String data, String descrizione, double importo, double prelievo, String nomeFile, Alert errore) 
	{
		PrintWriter outputStream=null;
		try 
		{
			outputStream=new PrintWriter(new FileWriter(nomeFile,true));
		}
		catch (IOException e)
		{
			errore.setContentText("Errore nel salvataggio dei dati sul database.");
    		errore.show();
		}
		
		String dataMovimento=data.toString();
		
		
		outputStream.println(dataMovimento+"/"+descrizione+"/"+importo+"/"+prelievo);
		
		outputStream.close();
	}

		

}
