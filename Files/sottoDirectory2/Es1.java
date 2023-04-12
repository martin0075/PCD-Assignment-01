import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Es1
{
	public static void main(String[]args) 
	{
		
		Scanner scan=new Scanner(System.in);
		int[]vet;
		System.out.print("Inserisci la dim del vettore: ");
		int d=scan.nextInt();
		vet=new int[d];
		
		System.out.print("vet= ");
		int somma=0;
		for(int i=0;i<vet.length;i++) 
		{
			vet[i]=ThreadLocalRandom.current().nextInt(2,10);
			System.out.print(vet[i]+", ");
			somma+=vet[i];
		}
		double media=somma/vet.length;
		
		System.out.print("\nMedia: "+media);
		
		//secondo punto
		
		System.out.print("\ninserisci il valore dell'indice i, deve essere maggiore di 0 e minore della dim del vettore: ");
		int i=scan.nextInt()-1;
		System.out.print("inserisci il valore dell'indice j, deve essere maggiore del precedente e minore della dim del vettore: ");
		int j=scan.nextInt()-1;
		somma=0;
		
		for(int l=i;l<=(j-i)+1;l++)
		{
			somma+=vet[l];
		}
		
		System.out.print("Somma valori: "+somma);
		
		//terzo punto
		
		System.out.print("\ninserisci il valore dell'indice k, deve essere maggiore di 0 e minore della dim del vettore: ");
		int k=scan.nextInt();
		
		
		int numSomma=0;
		do 
		{
			somma=0;
			for(int l=numSomma;l<(k+numSomma);l++) 
			{
				somma+=vet[l];
			}
			System.out.print("Somma valori: "+somma+"\n");
			d--;
			numSomma++;
			
		}while(d>=k);
		
		
		//quarto punto
		
		int inverso[]=new int[vet.length];
		System.out.print("\ninverso= ");
		for(int q=vet.length-1, m=0;q>=0;q--, m++) 
		{
			inverso[m]=vet[q];
			System.out.print(inverso[m]+", ");
		}
	}
}