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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MavRavController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCodiceMavRav;

    @FXML
    private TextField txtMavRav;

    @FXML
    private Label lblImporto;

    @FXML
    private TextField txtImporto;

    @FXML
    private Label lblInformation;

    @FXML
    private Button btnOk;

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
    
    public MavRavController() 
    {
    	banca=new Banca();
    	logIn=new LoginController();
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
    	//si verifica che l'utente inserisca correttamente il codice mav/rav e l'importo da pagare
    	if(txtMavRav.getLength()!=17) 
    	{
    		errore.setContentText("Inserirsci correttamente il codice mav/rav, è formato da almeno 17 caratteri.");
    		errore.show();
    	}
    	else if(!tryParseDouble(txtImporto.getText()))
    	{
    		errore.setContentText("Inserisci correttamente l'importo.");
    		errore.show();
    	}
    	else 
    	{
    		double importo=Double.parseDouble(txtImporto.getText());
    		ArrayList<Conto>contiCorrenti=new ArrayList<Conto>();
        	contiCorrenti=logIn.getContiCorrenti();
        	
        	//si verifica che la somma da pagare del bollettino mav/rav non sia maggiore del saldo a disposizione
        	if(contiCorrenti.get(indiceConto).prelievo(importo)) 
        	{
        		//viene aggiornato il conto corrente con il nuovo saldo
        		banca.aggiornaContoCorrente(contiCorrenti, "ContiCorrenti.txt",errore);
        		
        		String descrizione="effettuato pagamento mav|rav";
            	Date today=new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            	//viene aggiornato l'estratto conto con i dati del nuovo movimento effettuato
            	banca.aggiornaEstrattoConto(sdf.format(today), descrizione, importo, contiCorrenti.get(indiceConto).getSaldo(), contiCorrenti.get(indiceConto).getFileMovimenti(),errore);
            	
        		
        		lblInformation.setText("Operazione effettuata correttamente,\nper verificare il saldo e i movimenti sul conto\nclicchi sul pulsante 'INDIETRO' e poi \n'ESTRATTO CONTO',altrimenti clicchi 'ESCI' se ha \nterminato tutte le operazioni.");
        		btnOk.setDisable(true);
        	}
        	else 
        	{
        		errore.setContentText("Attenzione, ha inserito un importo maggiore rispetto al saldo a disposizone.");
        		errore.show();
        	}
    	}

    }
    
    //metodo che effettua la conversione della stringa passata in input in un double
    //nela caso in cui l'utente non inserisca un valore di tipo double il metodo restiuisce false.
    public static boolean tryParseDouble(String value) 
    {  
        try {  
            Double.parseDouble(value);  
            return true;  
         } catch (NumberFormatException e) {  
            return false;  
         }  
    }

    @FXML
    void initialize() {
        assert lblCodiceMavRav != null : "fx:id=\"lblCodiceMavRav\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert txtMavRav != null : "fx:id=\"txtMavRav\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert lblImporto != null : "fx:id=\"lblImporto\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert txtImporto != null : "fx:id=\"txtImporto\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'MavRav.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'MavRav.fxml'.";
        lblInformation.setText("Inserisci correttamente il codice Mav/Rav, \nin seguito clicchi sul 'OK'\nper confermare l'operazione.");
    }
}
