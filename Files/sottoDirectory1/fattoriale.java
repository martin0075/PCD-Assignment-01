public class fattoriale
{
	public static int Fattoriale(int n, int fattoriale) 
	{
		if(n>0) 
		{
			fattoriale=fattoriale*(n);
			n=n-1;
			return Fattoriale(n,fattoriale);
		}
		return fattoriale;
		
	}
	
	public static void main(String[] args) 
	{
		int n=5;
		System.out.println(Fattoriale(n,1));
		
	}
}