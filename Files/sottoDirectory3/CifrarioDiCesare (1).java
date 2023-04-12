public class CifrarioDiCesare {
	
	int scorrimento;
	
	public CifrarioDiCesare(int scorrimento) {
		this.scorrimento = scorrimento;
	}
	
	public int lunghezzaAlfabeto() {
		return 'z' - 'a' + 1;
	}

	public String cifra(String testo) {
		String codifica = "";
		
		char[] caratteri = testo.toCharArray();
		
		for(int i=0; i<caratteri.length; i++) {
			char carattere = caratteri[i];
		/* alternativa con for-each
		for(char carattere : caratteri) {
		*/
			if((carattere >= 'a' && carattere <= 'z') || (carattere >= 'A' && carattere <= 'Z')) {
				int nuovoCarattere = (carattere + scorrimento);
				if((carattere <= 'z' && nuovoCarattere > 'z') || (carattere <= 'Z' && nuovoCarattere > 'Z'))
					nuovoCarattere -= lunghezzaAlfabeto();
				
				codifica += (char) nuovoCarattere;
			}
			else codifica += carattere;
			
		}
		
		return codifica;
	}
	
	public String decifra(String testo) {
		
		String decodifica = "";
		
		char[] caratteri = testo.toCharArray();
		
		for(int i=0; i<caratteri.length; i++) {
			char carattere = caratteri[i];
			if((carattere >= 'a' && carattere <= 'z') || (carattere >= 'A' && carattere <= 'Z')) {
				int nuovoCarattere = (carattere - scorrimento);
				
				if((carattere >= 'a' && nuovoCarattere < 'a') || (carattere >= 'A' && nuovoCarattere < 'A'))
					nuovoCarattere += lunghezzaAlfabeto();
				
				decodifica += (char) nuovoCarattere;
			}
			else decodifica += carattere;
		}
		
		return decodifica;
	}
	
	public static void main(String[] args) {
		CifrarioDiCesare cifrarioDiCesare = new CifrarioDiCesare(5);
		String codifica = cifrarioDiCesare.cifra("Questo è il MEssaggio");
		System.out.println(codifica);
		String decodifica = cifrarioDiCesare.decifra(codifica);
		System.out.println(decodifica);
	}
	
}
