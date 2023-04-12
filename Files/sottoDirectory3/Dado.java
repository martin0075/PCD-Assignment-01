import java.util.Random;

public class Dado 
{
	private int numeroFacce;
	private Random random;
	
	public Dado(int facce)
	{
		this.numeroFacce=facce;
		this.random=new Random();
	}

	

	public int lancia()
	{
		
		return random.nextInt(numeroFacce)+1;
	}
	
	public int getFacce(){return numeroFacce;}
	
	/*int x = (lancioPrecedente % getFacce()) + 1;
	lancioPrecedente = x;
	return x;*/
		

}
