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

public class VersamentoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtVersamento;

    @FXML
    private Button btn1;

    @FXML
    private Button btn3;

    @FXML
    private Button btn2;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button btn0;

    @FXML
    private Button btnPoint;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblInformation;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnIndietro;

    @FXML
    private Button btnEsci;
    
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
    
    public VersamentoController() 
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
    	//verifica se l'utente ha digitato l'importo da versare
    	if(txtVersamento.getLength()>0) 
    	{
    		double versamento=Double.parseDouble(txtVersamento.getText());
    		//viene creata la lista di conti correnti
        	ArrayList<Conto>contiCorrenti=new ArrayList<Conto>();
        	//viene popolata la lista dei conti correnti richiamando il metodo getter presente nella classe logIn
        	contiCorrenti=logIn.getContiCorrenti();
        	//viene richiamato il metodo "ricaricaConto" della classe conto che incrementa il saldo dell'importo specificato dal cliente
        	contiCorrenti.get(indiceConto).ricaricaConto(versamento);
        	//viene aggiornato il conto corrente con il nuovo saldo
        	banca.aggiornaContoCorrente(contiCorrenti, "ContiCorrenti.txt",errore);
        	
        	String descrizione="versamento";
        	Date today=new Date();
        	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        	//viene aggiornato l'estratto conto con i dati del nuovo movimento effettuato
        	banca.aggiornaEstrattoConto(sdf.format(today), descrizione, versamento, contiCorrenti.get(indiceConto).getSaldo(), contiCorrenti.get(indiceConto).getFileMovimenti(),errore);
        	
        	txtVersamento.clear();
        	lblInformation.setText("Operazione effettuata correttamente,\nper verificare il saldo e i movimenti sul conto\nclicchi sul pulsante 'INDIETRO' e poi \n'ESTRATTO CONTO',altrimenti clicchi 'ESCI' se \nha terminato tutte le operazioni.");
    	}

    }

    @FXML
    void handleTastierino(ActionEvent event) 
    {
    	if(event.getSource()== btn1) 
    		txtVersamento.setText(txtVersamento.getText()+"1");
    	else if(event.getSource()== btn2) 
    		txtVersamento.setText(txtVersamento.getText()+"2");
    	else if(event.getSource()== btn3) 
    		txtVersamento.setText(txtVersamento.getText()+"3");
    	else if(event.getSource()== btn4) 
    		txtVersamento.setText(txtVersamento.getText()+"4");
    	else if(event.getSource()== btn5) 
    		txtVersamento.setText(txtVersamento.getText()+"5");
    	else if(event.getSource()== btn6) 
    		txtVersamento.setText(txtVersamento.getText()+"6");
    	else if(event.getSource()== btn7) 
    		txtVersamento.setText(txtVersamento.getText()+"7");
    	else if(event.getSource()== btn8) 
    		txtVersamento.setText(txtVersamento.getText()+"8");
    	else if(event.getSource()== btn9) 
    		txtVersamento.setText(txtVersamento.getText()+"9");
    	else if(event.getSource()== btn0) 
    		txtVersamento.setText(txtVersamento.getText()+"0");
    	else if(event.getSource()== btnPoint) 
    		txtVersamento.setText(txtVersamento.getText()+".");
    	else if(event.getSource()== btnClear) 
    		txtVersamento.setText("");

    }

    @FXML
    void initialize() {
        assert txtVersamento != null : "fx:id=\"txtInsertDati\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn3 != null : "fx:id=\"btn3\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn4 != null : "fx:id=\"btn4\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn5 != null : "fx:id=\"btn5\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn6 != null : "fx:id=\"btn6\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn7 != null : "fx:id=\"btn7\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn8 != null : "fx:id=\"btn8\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn9 != null : "fx:id=\"btn9\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btn0 != null : "fx:id=\"btn0\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btnPoint != null : "fx:id=\"btnPoint\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'Versamento.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'Versamento.fxml'.";
        lblInformation.setText("Inserisca l'importo della cifra da versare,\nin seguito clicchi su 'OK' per confermare;\naltrimenti clicchi su 'INDIETRO' per tornare \nalla schermata precedente,\noppure clicchi su 'ESCI' se ha terminato \ntutte le operazioni.");
        
    }
}

