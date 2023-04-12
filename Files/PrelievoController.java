package application;

import java.io.FileNotFoundException;
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

public class PrelievoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPrelievo;

    @FXML
    private Button btn2;

    @FXML
    private Button btn9;

    @FXML
    private Button btn8;

    @FXML
    private Button btn6;

    @FXML
    private Button btn5;

    @FXML
    private Button btn3;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnPoint;

    @FXML
    private Button btn1;

    @FXML
    private Button btn4;

    @FXML
    private Button btn0;

    @FXML
    private Button btn7;

    @FXML
    private Label lblInformation;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnIndietro;

    @FXML
    private Button btnEsci;
    
    private double prelievo;
    
    private Banca banca;
    private LoginController logIn;
    
    private int indiceConto;
    
    //variabile che consente di mostrare a video un messaggio che comunica all'utente un determinato errore verrificatosi (per esempio errore nella lettura del file)
    private Alert errore=new Alert(Alert.AlertType.ERROR);
    
    public PrelievoController() 
    {
    	banca=new Banca();
    	logIn=new LoginController();
    	
    	
    	
    }
    
    //metodo che modifica il contenuto della varibile indiceConto, 
    //passandogli l'indice che fa riferimento alla posizione del conto del cliente sulla lista
    //viene richiamato ogni volta che viene visualizzata questa finestra in modo tale da saper sempre su quale conto 
    //vengono effettuate le operazioni
    public void passInformationsToController(int indice) 
    {
    	this.indiceConto=indice;
    }
    
    

    @FXML
    void btnIndietro(ActionEvent event) throws IOException 
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
    void handleEsci(ActionEvent event) throws IOException 
    {
    	Parent tableViewParent=FXMLLoader.load(getClass().getResource("LogIn.fxml"));
    	Scene tableViewScene=new Scene(tableViewParent);
    	
    	Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewScene);
    	window.show();

    }

    @FXML
    void handleOk(ActionEvent event) throws FileNotFoundException 
    {
    	//verifica se l'utente ha digitato l'importo
    	if(txtPrelievo.getLength()>0) 
    	{
    		prelievo=Double.parseDouble(txtPrelievo.getText());
        	
        	//viene creata la lista che conterrà tutti i conti correnti
    		ArrayList<Conto>contiCorrenti=new ArrayList<Conto>();
        	
        	
        	//viene popolata la lista richiamando il metodo getter presente nella classe logIn
        	contiCorrenti=logIn.getContiCorrenti();
        	
        	//viene richiamato il metodo "prelievo" della classe conto che verifica se il cliente
        	//dispone del saldo necessario per effettuare l'operazione
        	if(contiCorrenti.get(indiceConto).prelievo(prelievo)) 
        	{
        		//viene aggiornato il conto corrente con il nuovo saldo
        		banca.aggiornaContoCorrente(contiCorrenti, "ContiCorrenti.txt",errore);
        		
        		String descrizione="prelievo";
            	Date today=new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            	//viene aggiornato l'estratto conto con i dati del nuovo movimento effettuato
            	banca.aggiornaEstrattoConto(sdf.format(today), descrizione, prelievo, contiCorrenti.get(indiceConto).getSaldo(), contiCorrenti.get(indiceConto).getFileMovimenti(),errore);
            	
        		txtPrelievo.clear();
        		lblInformation.setText("Operazione effettuata correttamente,\nper verificare il saldo e i movimenti sul conto\nclicchi sul pulsante 'INDIETRO' e poi \n'ESTRATTO CONTO',altrimenti clicchi 'ESCI' se ha \nterminato tutte le operazioni.");
        		
        	}
        	else
        		lblInformation.setText("Non può prelevare una somma maggiore\n al suo saldo.");
    	}
    		
    }

    @FXML
    void handlePad(ActionEvent event) 
    {
    	if(event.getSource()== btn1) 
    		txtPrelievo.setText(txtPrelievo.getText()+"1");
    	else if(event.getSource()== btn2) 
    		txtPrelievo.setText(txtPrelievo.getText()+"2");
    	else if(event.getSource()== btn3) 
    		txtPrelievo.setText(txtPrelievo.getText()+"3");
    	else if(event.getSource()== btn4) 
    		txtPrelievo.setText(txtPrelievo.getText()+"4");
    	else if(event.getSource()== btn5) 
    		txtPrelievo.setText(txtPrelievo.getText()+"5");
    	else if(event.getSource()== btn6) 
    		txtPrelievo.setText(txtPrelievo.getText()+"6");
    	else if(event.getSource()== btn7) 
    		txtPrelievo.setText(txtPrelievo.getText()+"7");
    	else if(event.getSource()== btn8) 
    		txtPrelievo.setText(txtPrelievo.getText()+"8");
    	else if(event.getSource()== btn9) 
    		txtPrelievo.setText(txtPrelievo.getText()+"9");
    	else if(event.getSource()== btn0) 
    		txtPrelievo.setText(txtPrelievo.getText()+"0");
    	else if(event.getSource()== btnPoint) 
    		txtPrelievo.setText(txtPrelievo.getText()+".");
    	else if(event.getSource()== btnClear) 
    		txtPrelievo.setText("");

    }

    @FXML
    void initialize() {
        assert txtPrelievo != null : "fx:id=\"txtPrelievo\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn9 != null : "fx:id=\"btn9\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn8 != null : "fx:id=\"btn8\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn6 != null : "fx:id=\"btn6\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn5 != null : "fx:id=\"btn5\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn3 != null : "fx:id=\"btn3\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btnPoint != null : "fx:id=\"btnPoint\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn4 != null : "fx:id=\"btn4\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn0 != null : "fx:id=\"btn0\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btn7 != null : "fx:id=\"btn7\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'Prelievo.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'Prelievo.fxml'.";
        lblInformation.setText("Inserisca l'importo della cifra da prelevare,\nin seguito clicchi su 'OK' per confermare;\naltrimenti clicchi su 'INDIETRO' per tornare \nalla schermata precedente,\noppure clicchi su 'ESCI' se ha terminato \ntutte le operazioni.");
        

    }

	
}





