import java.util.Random;
import java.util.Scanner;

public class Es2
{
	public static void main(String[]args) 
	{
		System.out.print("Inserisci la dimensione dei vettori: ");
		Scanner scan=new Scanner(System.in);
		int d=scan.nextInt();
		
		boolean [] a=new boolean[d];
		boolean [] b=new boolean [d];
		
		Random rnd=new Random();
		
		String veta="a= ";
		String vetb="b= ";
		
		for(int i=0;i<a.length;i++) 
		{
			a[i]=rnd.nextBoolean();
			b[i]=rnd.nextBoolean();
			
			veta+=""+a[i]+", ";
			vetb+=""+b[i]+", ";
		}
		
		System.out.print(veta);
		System.out.print("\n"+vetb);
		
		boolean [] c=new boolean[2*d];
		int contA=0;
		int contB=0;
		String vetC="c= ";
		
		for(int i=0;i<c.length;i++) 
		{
			if(i%2==0) 
			{
				c[i]=a[contA];
				contA++;
				vetC+=""+c[i]+", ";
			}
			else 
			{
				c[i]=b[contB];
				contB++;
				vetC+=""+c[i]+", ";
			}
		}
		System.out.print("\n"+vetC);
	}
}