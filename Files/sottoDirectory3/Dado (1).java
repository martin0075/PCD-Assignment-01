import java.util.Random;

public class Dado {
	
	protected int n;
	private Random random;
	
	public Dado(int n) {
		this.n = n;
		this.random = new Random();
	}
	
	public int lancia() {
		return random.nextInt(n)+1;
	}

}
