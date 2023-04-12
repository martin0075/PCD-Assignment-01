
public class StringRicorsione 
{
	public static String togliSpazi(String stringa)
	{
		if(stringa.length()==0)
			return stringa;
		else if(stringa.charAt(0)==' ')
		{
			return togliSpazi(stringa.substring(1, stringa.length()));
		}
		else
		{
			return stringa.charAt(0)+togliSpazi(stringa.substring(1, stringa.length()));
		}
			
			
	}
	
	public static boolean contiene(String stringa, char carattere)
	{	
		if(stringa.length()==0)
			return false;
		else if(stringa.charAt(0)==carattere)
		{
			return true;
		}
		else return contiene(stringa.substring(1,stringa.length()), carattere);
		
	}
	
	public static int contaRipetizioni(String stringa, char carattere, int cont)
	{
		
		if(stringa.length()==0)
			return cont;
		else if(stringa.charAt(0)==carattere)
		{
			cont++;
			return contaRipetizioni(stringa.substring(1,stringa.length()),carattere, cont);
		}
		else return contaRipetizioni(stringa.substring(1,stringa.length()),carattere,cont);
	}
	
	public static boolean palindroma(String stringa, boolean verifica)
	{
		if(stringa.length()<=1)
			return verifica=true;
		else if(stringa.charAt(0)==stringa.charAt(stringa.length()-1))
		{
			verifica=true;
			return palindroma(stringa.substring(1,stringa.length()-1),verifica);
		}
		else return verifica;
	}
	
	
	public static void main(String []args) 
	{
		System.out.println(togliSpazi("bella raga ciao"));
		
		System.out.println("numeri di caratteri: "+ contaRipetizioni("bella raga ciao", 'a',0));
		
		System.out.print("Parola: bestia");
		if(palindroma("bestia",false))
			System.out.print("---> Palindroma");
		else
			System.out.print("--->non Palindroma");
	}

}
