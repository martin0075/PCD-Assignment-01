package application;



import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * classe che consente di memorizzare i dati dei movimenti effettuati sul conto ovver:
 * -data del movimento
 * -una breve descrizione del movimento effettuato
 * -l'importo del movimento
 * -il saldo attuale
 */
public class Movimenti 
{
	//il tipo delle varibili è "SimpleStringProperty" e "SimpleDoubleProperty" per consentire 
	//in seguito di caricare ogni movimento all'interno di una ObservableList
	private SimpleStringProperty dataMovimento;
	private SimpleStringProperty descrizione;
	private SimpleDoubleProperty importo;
	private SimpleDoubleProperty saldo;
	
	public Movimenti(String dataMovimento, String descrizione, double importo, double saldo) 
	{
		this.dataMovimento=new SimpleStringProperty(dataMovimento);
		this.descrizione=new SimpleStringProperty(descrizione);
		this.importo=new SimpleDoubleProperty(importo);
		this.saldo=new SimpleDoubleProperty(saldo);
	}
	
	public String getDataMovimento() {return this.dataMovimento.get();}
	public String getDescrizione() {return this.descrizione.get();}
	public Double getImporto() {return this.importo.get();}
	public Double getSaldo() {return this.saldo.get();}
	
	

}
