package application;

public class EsProf 
{
	public static int function (int n, int count) 
	{
		int quadrato=count*count;
		int somma=0;
		
		if(quadrato<=n) 
		{
			count++;
			somma=function(n, count)+quadrato;
		} 
		System.out.println(somma);
	
		return somma;
	}
	
	public static void main(String[]args) 
	{
		System.out.println(function(5,0));
	}

}
