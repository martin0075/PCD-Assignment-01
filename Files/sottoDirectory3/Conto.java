package application;

/*Classe che memorizza le credenziali per ogni singolo conto corrente:
 * -codice del conto
 * -pin
 * -saldo attuale
 * -file di testo, all'interno del quale sono presenti tutti i movimenti effettuati sul conto
 */

public class Conto 
{
	private String codiceContoCorrente;
	private int pin;
	private double saldo;
	private String movimenti;
	
	public Conto(String codice, int pin, double saldo, String fileMovimenti)
	{
		this.codiceContoCorrente=codice;
		this.pin=pin;
		this.saldo=saldo;
		this.movimenti=fileMovimenti;
	}
	
	public double getSaldo(){return saldo;}
	public String getContoCorrente(){return codiceContoCorrente;}
	public int getPin(){return pin;}
	
	public String getFileMovimenti() {return this.movimenti;}
	
	//metodo che verifica se l'utente ha inserito correttamente tutti i dati
	//in caso positivo restituisce true, altrimenti false.
	public boolean verificaCliente(String contoCorrente,int pin) 
	{
		if(this.codiceContoCorrente.equals(contoCorrente) && this.pin==pin)
			return true;
		else
			return false;
	}
	
	
	//metodo che effettua il prelievo dal saldo, della somma passata come parametro,
	//nel caso in cui questa sia maggiore del saldo a disposizione restituisce false,
	//altrimenti true.
	public boolean prelievo(double sommaDaPrelevare)
	{
		
		boolean verifica=false;
		if(saldo>=sommaDaPrelevare)
		{
			saldo-=sommaDaPrelevare;
			verifica=true;
			return verifica;
			
		}
		else
		{
			return verifica;
		}
		
	}
	
	//metodo che incrementa il saldo, nela caso in cui l'utente desideri effetuare un versamento
	public void ricaricaConto(double sommaDaRicaricare)
	{
		saldo+=sommaDaRicaricare;
	}
	

}
