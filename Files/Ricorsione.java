package application;

public class Ricorsione 
{
	public static boolean rispettaProprieta(int [] vet)
	{
		int cont=0;
		
		while(cont<(vet.length-1)/2)
		{
			int num=vet[cont];
			int numb=vet[vet.length-cont-1];
			
			if(num==(numb*-1))
				System.out.println(num+" : "+(numb*-1));
			else 
			{
				System.out.println(num+" != "+(numb*-1));
				return false;
			}
				
			
			cont++;
			
		}
		return true;

	}
	
	public static void metodoRicorsivo(int[] vet, int input) 
	{
		int cont=input;
		int[]array=vet;
		
		if(cont>(array.length)/2) 
		{
			System.out.println("Rispetta la proprieta.");
			return;
		}
		
		if(cont==(array.length-cont-1)) 
		{
			System.out.print("Rispetta la prorpieta.");
			return;
		}
		
		int num=array[cont];
		int numb=array[array.length-cont-1];
		
		if(num==(numb*-1)) 
		{
			cont++;
			metodoRicorsivo(vet, cont);
		}
		else 
		{
			System.out.println("Non rispetta la prorpieta.");
			return;
		}
			
		
	}
	
	public static void main(String[]args)
	{
		int [] vet={1,3,-5,2,5,-3,-1};
		
		
		//if(rispettaProprieta(vet))
			//System.out.println("Il vettore rispetta la proprieta.");
		
		metodoRicorsivo(vet,0);
		
		
	}

}
