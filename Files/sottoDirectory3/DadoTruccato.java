
public class DadoTruccato extends Dado 
{
	private int lancioPrecedente;
	public DadoTruccato(int numFacce)
	{
		super(numFacce);
		lancioPrecedente=0;
	}

	
	public int lancio()
	{
		
		if(lancioPrecedente==0 || lancioPrecedente==getFacce())
		{
			lancioPrecedente=1;
			return 1;
		}
		else
		{
			int x=lancioPrecedente+1;
			lancioPrecedente=x;
			return x;
		}
			
	}

}
