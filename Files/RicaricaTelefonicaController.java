package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RicaricaTelefonicaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblGestrore;

    @FXML
    private MenuButton menuGestori;

    @FXML
    private Label lblImporto;

    @FXML
    private MenuButton menuImporto;

    @FXML
    private Label lblInformation;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnEsci;

    @FXML
    private Button btnIndietro;
    
    private int indiceConto;
    
    private Banca banca;
    private LoginController logIn;
    
    //variabile che consente di mostrare a video un messaggio che comunica all'utente un determinato errore verrificatosi (per esempio errore nella lettura del file)
    private Alert errore=new Alert(Alert.AlertType.ERROR);
    
   //metodo che modifica il contenuto della varibile indiceConto, 
    //passandogli l'indice che fa riferimento alla posizione del conto del cliente sulla lista
    //viene richiamato ogni volta che viene visualizzata questa finestra in modo tale da saper sempre su quale conto 
    //vengono effettuate le operazioni
    public void passInformationToController(int indice) 
    {
    	this.indiceConto=indice;
    	
    }
    
    public RicaricaTelefonicaController() 
    {
    	banca=new Banca();
    	logIn=new LoginController();
    }
    
   
    /*
     * I seguenti metodi cosentono di visualizzare sul menu relativo all'importo e al gestore telefonico
     * il campo selezionato.
     */
    @FXML
    void handleImporto15(ActionEvent event) 
    {
    	menuImporto.setText("15");

    }

    @FXML
    void handleImporto20(ActionEvent event) 
    {
    	menuImporto.setText("20");

    }

    @FXML
    void handleImporto25(ActionEvent event) 
    {
    	menuImporto.setText("25");

    }

    @FXML
    void handleImportoIliad(ActionEvent event) 
    {
    	menuGestori.setText("ILIAD");

    }

    @FXML
    void handleImportoTim(ActionEvent event) 
    {
    	menuGestori.setText("TIM");

    }

    @FXML
    void handleImportoTre(ActionEvent event) 
    {
    	menuGestori.setText("TRE");

    }

    @FXML
    void handleImportoWind(ActionEvent event) 
    {
    	menuGestori.setText("WIND");

    }
    
    @FXML
    void handleImportoVodafone(ActionEvent event) 
    {
    	menuGestori.setText("VODAFONE");

    }

    @FXML
    void handleEsci(ActionEvent event) throws IOException 
    {
    	Parent tableViewParent=FXMLLoader.load(getClass().getResource("LogIn.fxml"));
    	Scene tableViewScene=new Scene(tableViewParent);
    	
    	Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewScene);
    	window.show();

    }

    @FXML
    void handleIndietro(ActionEvent event) throws IOException 
    {
    	//viene creato un nuovo oggetto di tipo "FXMLoader" che rappresenta la nuova finestra che verra visualizzata
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuOperations.fxml"));
		BorderPane root=(BorderPane)loader.load();
		
		//viene creato un oggetto che ha come tipo la classe che fa riferimento alla nuova finestra visualizzata
		//e viene invocato un metodo setter che consente di passare il valore di una determinata variabile
		//tra finestre diverse
		OperationsController p=loader.getController();
		p.passInformationToController(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void handleOk(ActionEvent event) 
    {
    	//si verifica che l'utente selezioni un determinato campo per entrambi i menu
    	if(menuGestori.getText()=="") 
    	{
    		errore.setContentText("Selezioni uno dei gestori per effettuare la ricarica.");
    		errore.show();
    	}
    	else if(menuImporto.getText()=="") 
    	{
    		errore.setContentText("Selezioni uno dei seguenti importi, per effettuare la ricarica.");
    		errore.show();
    	}
    	else 
    	{
    		String gestore=menuGestori.getText();
    		double importo=Double.parseDouble(menuImporto.getText());
    		
    		ArrayList<Conto>contiCorrenti=new ArrayList<Conto>();
        	
        	contiCorrenti=logIn.getContiCorrenti();
        	
        	//si verifica che la somma da pagare per la ricarica telefonica non sia maggiore del saldo a disposizione
        	if(contiCorrenti.get(indiceConto).prelievo(importo)) 
        	{
        		//viene aggiornato il conto corrente con il nuovo saldo
        		banca.aggiornaContoCorrente(contiCorrenti, "ContiCorrenti.txt",errore);
        		
        		String descrizione="effettuata ricarica telefonica "+gestore;
            	Date today=new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            	//viene aggiornato l'estratto conto con i dati del nuovo movimento effettuato
            	banca.aggiornaEstrattoConto(sdf.format(today), descrizione, importo, contiCorrenti.get(indiceConto).getSaldo(), contiCorrenti.get(indiceConto).getFileMovimenti(),errore);
            	
        		
        		lblInformation.setText("Operazione effettuata correttamente,\nper verificare il saldo e i movimenti sul conto\nclicchi sul pulsante 'INDIETRO' e poi \n'ESTRATTO CONTO',altrimenti clicchi 'ESCI' se ha \nterminato tutte le operazioni.");
        		btnOK.setDisable(true);
        	}
        	else 
        	{
        		errore.setContentText("Attenzione, ha inserito un importo maggiore rispetto al saldo a disposizone.");
        		errore.show();
        	}
    	}

    }

    @FXML
    void initialize() {
        assert lblGestrore != null : "fx:id=\"lblGestrore\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert menuGestori != null : "fx:id=\"menuGestori\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert lblImporto != null : "fx:id=\"lblImporto\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert menuImporto != null : "fx:id=\"menuImporto\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'RicaricaTelefonica.fxml'.";
        lblInformation.setText("Selezioni il gestore e l'importo desiderato \nper effettuare la ricarica telefonica.");

    }
}