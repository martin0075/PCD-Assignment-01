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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BollettinoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnIndietro;

    @FXML
    private ImageView ImageView;

    @FXML
    private Label lblContoCorrente;

    @FXML
    private Label lblBollettino;

    @FXML
    private TextField txtImporto;

    @FXML
    private Label lblNumeroBollettino;

    @FXML
    private TextField txtNumBollettino;

    @FXML
    private Label lblTipoBollettino;

    @FXML
    private TextField txtTipoBollettino;
    
    @FXML
    private TextField txtContoCorrente;

    @FXML
    private Label lblInformation;

    @FXML
    private Button btnEsci;

    @FXML
    private Button btnOk;
    
    private int indiceConto;
    
    private Banca banca;
    
    private LoginController logIn;
    
    //variabile che consente di mostrare a video un messaggio che comunica all'utente un determinato errore verrificatosi (per esempio errore nella lettura del file)
    private Alert errore=new Alert(Alert.AlertType.ERROR);
    
    //metodo che modifica il contenuto della varibile indiceConto, 
    //passandogli l'indice che fa riferimento alla posizione del conto del cliente sulla lista
    //viene richiamato ogni volta che viene visualizzata questa finestra in modo tale da saper sempre su quale conto 
    //vengono effettuate le operazioni
    public void passInformationsToController(int indice) 
    {
    	this.indiceConto=indice;
    }
    
    public BollettinoController() 
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
    void handleIdietro(ActionEvent event) throws IOException 
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
    void handleOk(ActionEvent event) 
    {
    	/*
    	 * Viene effettuato una verfica che tutti campi necessari per il pagamento di un bollettino
    	 * postale siano corretti, in caso contrario per ogni campo viene mostrato un messaggio di errore
    	 * se quel determinato campo non è stato compilato oppure è stato compilato in modo sbagliato
    	 */
    	
    	if(txtContoCorrente.getLength()!=8) 
    	{
    		errore.setContentText("Inserirsci correttamente il numero del conto corrente, è formato da 8 cifre.");
    		errore.show();
    	}
    	else if(!tryParseDouble(txtImporto.getText()))
    	{
    		errore.setContentText("Inserisci correttamente l'importo.");
    		errore.show();
    	}
    	else if(txtNumBollettino.getLength()!=18) 
    	{
    		errore.setContentText("Inserisci correttamente il numero del bollettino, è formato\n da 18 cifre.");
    		errore.show();
    	}
    	else if(txtTipoBollettino.getLength()!=3) 
    	{
    		errore.setContentText("Inserisci correttamente il numero corrispondente al tipo di bollettino, è formato da 3 cifre.");
    		errore.show();
    	}
    	else 
    	{
    		
    		
    		double importo=Double.parseDouble(txtImporto.getText());
        	
    		ArrayList<Conto>contiCorrenti=new ArrayList<Conto>();
        	
        	contiCorrenti=logIn.getContiCorrenti();
        	
        	//si verifica che la somma da pagare del bollettino postale non sia maggiore del saldo a disposizione
        	if(contiCorrenti.get(indiceConto).prelievo(importo)) 
        	{
        		//viene aggiornato il conto corrente con il nuovo saldo
        		banca.aggiornaContoCorrente(contiCorrenti, "ContiCorrenti.txt",errore);
        		
        		String descrizione="pagamento bollettino postale";
            	Date today=new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            	//viene aggiornato l'estratto conto con i dati del nuovo movimento effettuato
            	banca.aggiornaEstrattoConto(sdf.format(today), descrizione, importo, contiCorrenti.get(indiceConto).getSaldo(), contiCorrenti.get(indiceConto).getFileMovimenti(),errore);
            	
        		
        		lblInformation.setText("Operazione effettuata correttamente,\nper verificare il saldo clicchi sul pulsante\n'INDIETRO' e poi 'ESTRATTO CONTO',\naltrimenti clicchi 'ESCI' se ha terminato \ntutte le operazioni.");
        		btnOk.setDisable(true);
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
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert ImageView != null : "fx:id=\"ImageView\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert lblContoCorrente != null : "fx:id=\"lblContoCorrente\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert lblBollettino != null : "fx:id=\"lblBollettino\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert txtImporto != null : "fx:id=\"txtImporto\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert lblNumeroBollettino != null : "fx:id=\"lblNumeroBollettino\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert txtNumBollettino != null : "fx:id=\"txtNumBollettino\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert lblTipoBollettino != null : "fx:id=\"lblTipoBollettino\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert txtTipoBollettino != null : "fx:id=\"txtTipoBollettino\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'Bollettino.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'Bollettino.fxml'.";
        lblInformation.setText("Compili correttamente tutti i campi,\ne clicchi su 'OK' per confermare l'operazione.");
        //viene creata una variabile di tipo Image alla quale viene come costruttore il percorso dell'immagine 
        Image image=new Image("/Image/EsBollettino.jpg");
        //viene caricata l'immagine sulla finestra
        ImageView.setImage(image);
    }
}

