public class MaiuscoloMinuscolo {

	public String converti(String messaggio) {
		String codifica = "";
		char[] caratteri = messaggio.toCharArray();
		
		/* ALTERNATIVA CON FOR-EACH
		*
		* for(char carattere : caratteri) {
		*/
		for(int i=0; i<caratteri.length; i++) {
			char carattere = caratteri[i];
			
			char nuovoCarattere;
			
			if(Character.isLowerCase(carattere))
				nuovoCarattere = Character.toUpperCase(carattere);
			else if(Character.isUpperCase(carattere))
				nuovoCarattere = Character.toLowerCase(carattere);
			else nuovoCarattere = carattere;
			
			codifica += nuovoCarattere;
		}
		
		return codifica;
	}
	
	public static void main(String[] args) {
		MaiuscoloMinuscolo maiuscoloMinuscolo = new MaiuscoloMinuscolo();
		String codifica = maiuscoloMinuscolo.converti("Questo è il messaggio");
		System.out.println(codifica);
		String decodifica = maiuscoloMinuscolo.converti(codifica);
		System.out.println(decodifica);
	}
	
}
