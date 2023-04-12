package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInsertDati;

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
    
    public int pin;
    
    private String codiceConto;
    
    //variabile che memorizza l'indice del conto del cliente sulla lista dei conti correnti 
    private int indiceConto;
    
    //variabile necessaria per fare inserire inialmente il codice del conto e poi il pin
    private int inserimentoConto=0;
    
    private Banca banca;
    
    private ArrayList<Conto>contiCorrenti;
    
    public ArrayList<Conto> getContiCorrenti(){return contiCorrenti;}
    
    //variabile che consente di mostrare a video un messaggio che comunica all'utente un determinato errore verrificatosi (per esempio errore nella lettura del file)
    private Alert errore=new Alert(Alert.AlertType.ERROR);
    
    public LoginController() 
    {
    	banca=new Banca();//viene creato l'oggetto della classe banca
    	contiCorrenti=new ArrayList<Conto>();
    	banca.insertContiCorrenti("ContiCorrenti.txt", contiCorrenti,errore);//viene inizializzata la lista dei conti correnti
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException
    {
    	//quando l'utente effettua l'accesso la prima viene richiesto il codice del conto corrente
    	if(inserimentoConto==0) 
    	{
    		if(txtInsertDati.getLength()>0) 
    		{
    			//se l'utenete digita il codice, questo viene memorizzato in una variabile, si incrementa
    			//il contatore "inserimentoConto" e viene data la possibilita di digitare il pin una volta cliccato su 'OK'
    			codiceConto=(txtInsertDati.getText());
        		txtInsertDati.setText("");
        		inserimentoConto++;
        		lblInformation.setText("Digiti il pin e clicchi su 'OK' per proseguire");
    		}
    	}
    	else if(inserimentoConto==1)
    	{
    		
    		if(txtInsertDati.getLength()>0) 
    		{
    			pin=Integer.parseInt(txtInsertDati.getText());
    			//viene richiamato il metodo "trovaConto" della classe banca per verificare che i dati
    			//inseriti dall'utenete siano correti
    			if(banca.trovaConto(codiceConto,pin,contiCorrenti)!=null) 
        		{
        			//indice conto assume il valore che fa riferimento alla posizione del conto all'interno della lista
        			indiceConto=banca.getContoAttuale();
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
        			
        	    	inserimentoConto=0;
        	    	
        		}
        		else 
        		{
        			lblInformation.setText("Inserisci correttamente i dati,\ncodice del conto corrente o pin errati.");
        			txtInsertDati.setText("");
        			inserimentoConto=0;
        		}
    		}
    	}
    }
    
    @FXML
    void handleTastierino(ActionEvent event) 
    {
    	if(event.getSource()== btn1) 
    		txtInsertDati.setText(txtInsertDati.getText()+"1");
    	else if(event.getSource()== btn2) 
    		txtInsertDati.setText(txtInsertDati.getText()+"2");
    	else if(event.getSource()== btn3) 
    		txtInsertDati.setText(txtInsertDati.getText()+"3");
    	else if(event.getSource()== btn4) 
    		txtInsertDati.setText(txtInsertDati.getText()+"4");
    	else if(event.getSource()== btn5) 
    		txtInsertDati.setText(txtInsertDati.getText()+"5");
    	else if(event.getSource()== btn6) 
    		txtInsertDati.setText(txtInsertDati.getText()+"6");
    	else if(event.getSource()== btn7) 
    		txtInsertDati.setText(txtInsertDati.getText()+"7");
    	else if(event.getSource()== btn8) 
    		txtInsertDati.setText(txtInsertDati.getText()+"8");
    	else if(event.getSource()== btn9) 
    		txtInsertDati.setText(txtInsertDati.getText()+"9");
    	else if(event.getSource()== btn0) 
    		txtInsertDati.setText(txtInsertDati.getText()+"0");
    	else if(event.getSource()== btnPoint) 
    		txtInsertDati.setText(txtInsertDati.getText()+".");
    	else if(event.getSource()== btnClear) 
    		txtInsertDati.setText("");

    }

    @FXML
    void initialize() {
        assert txtInsertDati != null : "fx:id=\"txtInsertDati\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn3 != null : "fx:id=\"btn3\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn4 != null : "fx:id=\"btn4\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn5 != null : "fx:id=\"btn5\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn6 != null : "fx:id=\"btn6\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn7 != null : "fx:id=\"btn7\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn8 != null : "fx:id=\"btn8\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn9 != null : "fx:id=\"btn9\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btn0 != null : "fx:id=\"btn0\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btnPoint != null : "fx:id=\"btnPoint\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'LogIn.fxml'.";
        lblInformation.setText("Salve,\ndigiti il codice cel conto corrente,\nsuccessivamente clicchi sul pulsante 'OK',\nper proseguire con l'inserimento del pin. ");
        

    }
    
}

