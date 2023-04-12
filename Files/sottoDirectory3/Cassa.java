import java.util.ArrayList;
import java.util.Scanner;

public class Cassa {
	
	public static void main(String[] args) {
		
		Carrello carrello = new Carrello();
		Scanner scan = new Scanner(System.in);
		
		boolean finito = false;
		while(!finito) {
			System.out.print("Inserisci nome prodotto: ");
	        String nome = scan.next();
	        
	        System.out.print("Inserisci il suo prezzo: ");
	        double prezzo = scan.nextDouble();
	        
	        System.out.print("Inserisci la quantità desiderata: ");
	        int quantita = scan.nextInt();
	        
	        Prodotto prodotto = new Prodotto(nome, prezzo);
	        carrello.aggiungiProdotto(prodotto, quantita);
	        
	        System.out.print("Vuoi continuare? [s,n]: ");
	        String continua = scan.next();
	        if (continua.equals("n")) {
	        	finito=true;
	        	scan.close();
	        }
		}
		
		double costoTotale = 0;
		ArrayList<Acquisto> acquisti = carrello.getAcquisti();
		for(int i = 0; i<acquisti.size(); i++) {
			Acquisto acquisto = acquisti.get(i);
			Prodotto prodotto = acquisto.getProdotto();
			double prezzo = prodotto.getPrezzo();
			int quantita = acquisto.getQuantita();
			
			double costo = prezzo * ((double) quantita);
			System.out.println(prodotto.getNome() + " - " + prezzo + " * " + quantita + " = " + costo);
			costoTotale += costo;
		}
		
		System.out.println("Costo totale: " + costoTotale);
	}

}
