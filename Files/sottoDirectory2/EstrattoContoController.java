package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EstrattoContoController{

    @FXML
    private ResourceBundle resources;
    
    /*
     * Vengono dichiarate 4 variabili di tipo TableColumn che fanno 
     * riferimento alle 4 colonne della tabella dell'estratto conto
     */

    @FXML
    private TableColumn<Movimenti, String> data;

    @FXML
    private TableColumn<Movimenti, String> descrizione;

    @FXML
    private TableColumn<Movimenti, Double> importo;

    @FXML
    private TableColumn<Movimenti, Double> saldo;

    @FXML
    private URL location;

    @FXML
    private TableView<Movimenti> tblMovimenti;

    @FXML
    private Button btnIndietro;
    
    @FXML
    private Button btnStampa;

    @FXML
    private Button btnEsci;
    
    private int indiceConto;
    
    private Banca banca;
    
    private LoginController logIn;
        
    private ArrayList<Conto>contiCorrenti;
    
    private Alert errore=new Alert(Alert.AlertType.ERROR);
    
   //metodo che modifica il contenuto della varibile indiceConto, 
    //passandogli l'indice che fa riferimento alla posizione del conto del cliente sulla lista
    //viene richiamato ogni volta che viene visualizzata questa finestra in modo tale da saper sempre su quale conto 
    //vengono effettuate le operazioni
    public void passInformationsToEstratto(int indice) 
    {
    	this.indiceConto=indice;
    	
    }
    
    public EstrattoContoController()
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
    void handleStampa(ActionEvent event) 
    {
    	//viene creata la lista di conti correnti
    	contiCorrenti=new ArrayList<Conto>();
    	//viene popolata la lista dei conti correnti richiamando il metodo getter presente nella classe logIn
     	contiCorrenti=logIn.getContiCorrenti();
     	
		//vengono settate le 4 colonne della tabella tramite il metodo "setCellValueFactory"
     	//che accetta come parametro un oggetto PropertyValueFactory dello stesso tipo delle colonne
     	//dichiarate i precedenza
     	data.setCellValueFactory(new PropertyValueFactory<Movimenti, String>("dataMovimento"));
		descrizione.setCellValueFactory(new PropertyValueFactory<Movimenti, String>("descrizione"));
		importo.setCellValueFactory(new PropertyValueFactory<Movimenti, Double>("importo"));
		saldo.setCellValueFactory(new PropertyValueFactory<Movimenti, Double>("saldo"));
		
	
		//viene popolata la tabella mediante il metodo setItems, al quale viene passato come parametro
		//un oggetto di tipo "banca" sul quale viene richiamato il metodo caricaEstratto conto che restituisce 
		//una observableList che poi verra visualizzata sulla tabella.
		//Questa lista contine tutti i movimenti effettuati sul quel determinato conto, e viene memorizzata 
		//in seguito ad una lettura del file relativo ai movimenti del cliente il cui nome,
		//viene passato come parametro del metodo "caricaEstrattoConto".
		tblMovimenti.setItems(banca.caricaEstrattoConto(contiCorrenti.get(indiceConto).getFileMovimenti(),errore));
		

    }

    @FXML
    void initialize() {
        assert tblMovimenti != null : "fx:id=\"tblMovimenti\" was not injected: check your FXML file 'EstrattoConto.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'EstrattoConto.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'EstrattoConto.fxml'.";

    }

	
	
	
}