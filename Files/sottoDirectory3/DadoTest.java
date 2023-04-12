public class DadoTest {

	public static void main(String[] args) {
		Dado dado = new Dado(6);
		DadoTruccato dadoTruccato = new DadoTruccato(6);
		
		for(int i=0; i<10; i++) {
			int dado1 = dado.lancia();
			int dado2 = dado.lancia();
			
			System.out.println("Dado: lancio 1 " + dado1 + ", lancio 2 " + dado2);
			
			dado1 = dadoTruccato.lancia();
			dado2 = dadoTruccato.lancia();
			
			System.out.println("Dado truccato: lancio 1 " + dado1 + ", lancio 2 " + dado2);
		}
	}
}
