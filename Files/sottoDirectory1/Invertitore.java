public class Invertitore {

	public String inverti(String testo) {
		
		String risultato = "";
		
		char[] caratteri = testo.toCharArray();
		for(int i=caratteri.length-1; i>=0; i--) {
			risultato += caratteri[i];
		}
		
		return risultato;
	}
	
	public static void main(String[] args) {
		Invertitore invertitore = new Invertitore();
		String codifica = invertitore.inverti("Questo è il messaggio");
		System.out.println(codifica);
		String decodifica = invertitore.inverti(codifica);
		System.out.println(decodifica);
	}
}
