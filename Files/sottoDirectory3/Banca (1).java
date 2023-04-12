/*
  nome: Martin
  cognome: Marcolini
  matriola: 0000921984
*/

import java.util.Scanner;
import java.util.*;

public class Banca
{
	
	static Scanner scan;
	static ArrayList<Conto> conti=new ArrayList<Conto>();//ArrayList che contiene i conti dei clienti
	static Conto c;
	static boolean verifica=false;//variabile necessaria per verificare che il cliente abbia inserito correttamentei suoi dati
	
	
	public static void main(String[]args)
	{
		scan=new Scanner(System.in);
		//creazione de conti e popolamento della lista
		Conto c1= new Conto("00008769",67854,15000);
		conti.add(c1);
		Conto c2= new Conto("00003456",56783,16000);
		conti.add(c2);
		Conto c3= new Conto("00002890",98345,17000);
		conti.add(c3);
		Conto c4= new Conto("00001589",25890,18000);
		conti.add(c4);
		Conto c5= new Conto("00009873",31098,19000);
		conti.add(c5);
		
		boolean continua=true;
		
		//ciclo while che consente al cliente, di effuattuare operazioni successive 
		//nel caso di errori non termina subito, ma bensi viene data la possibilita all'utente di inserire i dati corretti
		//il programma termina quando il cliente scrive "Q"
		while(continua)
		{
			try
			{
				
				int posConto;//variabile che indica il numero del conto del cliente nella lista
				
				System.out.print("\nCiao, \nche cosa vuoi fare:\n**** STAMPA SALDO >> S\n\n**** PRELIEVO >> P\n\n**** RICARICA CARTA >> R\n\n**** ESCI >> Q\n ");
				String v=scan.next();
				
				//lo switch verifica quale operazione vuole effuattuareil cliente
				switch(v.toUpperCase())
				{
					case "S":
						posConto=autenticazione();
						if(verifica)
							System.out.println("\nSaldo attuale: "+conti.get(posConto).getSaldo());
						break;
					case "P":
						posConto=autenticazione();
						if(verifica)
						{
							System.out.print("\nQuanto vuoi prelevare: ");
							double prelievo=scan.nextDouble();
							conti.get(posConto).prelievo(prelievo);
						}
						break;
					case "R":
						posConto=autenticazione();
						if(verifica)
						{
							System.out.print("\nQuanto vuoi ricaricare: ");
							double ricarica=scan.nextDouble();	
							conti.get(posConto).ricaricaConto(ricarica);
						}
						break;
					case "Q":
						continua=false;
						break;
					default:
						break;
				}
					
				verifica=false;67
			}
			catch(InputMismatchException e)
			{
				System.out.print("\nErrore inserisci correttamente il pin, e' composto dolo da cifre.\n");
				scan.nextLine();
			}
			
		}
		
		
	}
	
	//metodo che provvede all'aggiunta degli 0 davanti al numero del conto
	//qualora, non sia già costituito da 8 valori
	public static String aggiuntaZeri(String iban)
	{
		int length=iban.length();
		for(int i=0;i<8-length;i++)
		{
			iban="0"+iban;
		}
		return iban;
	}
	
	//metodo che verifica che il cliente abbia inserito correttamente i suoi dati
	//in caso contrario viene comunicato l'errore al cliente con un messaggio
	//nel caso in cui dati siano corretti restituisce il numero del conto del cliente presente all'interno della lista
	public static int autenticazione()
	{
		System.out.print("\nInserisci l'iban: ");
		String iban=scan.next();
		System.out.print("Inserisci il pin: ");
		int pin=scan.nextInt();
		int indice=0;
		
		iban=aggiuntaZeri(iban);
		
		
		for(int i=0; i<conti.size();i++)
		{
			if(iban.equals(conti.get(i).getIban()) && pin==conti.get(i).getPin())
			{
				verifica=true;
				indice=i;
			}
		}
		if(!verifica)
				System.out.print("\nErrore inserisci correttamente l'iban o il pin.\n");
		
		return indice;
	}
}