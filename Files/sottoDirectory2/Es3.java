import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Es3
{
	public static void main(String []args) 
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
			vet[i]=ThreadLocalRandom.current().nextInt(-100,100);
			System.out.print(vet[i]+", ");
			somma+=vet[i];
		}
		double media=somma/vet.length;
		
		System.out.print("\n");
		
		
		int tmp=d+1;
		int cont=1;
		int numSomma=0;
		int max=0;
		do 
		{
			somma=0;
			for(int l=numSomma;l<(cont+numSomma);l++) 
			{
				somma+=vet[l];
				if(somma>max)
					max=somma;
			}
			System.out.print("Somma valori: "+somma+"\n");
			tmp--;
			numSomma++;
			
			if(tmp<=cont && numSomma<=d && cont!=d-1) 
			{
				tmp=d+1;
				numSomma=0;
				cont++;
				System.out.print("\n");
			}
			
		}while(tmp>cont);
		System.out.print("Max: "+max+"\n");
	}	
}