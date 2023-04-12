package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PagamentiRicaricheController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBonifico;

    @FXML
    private Button btnBollettino;

    @FXML
    private Button btnMavRav;

    @FXML
    private Button btnRicaricaTel;
    
    @FXML
    private Button btnUtenze;
    
    private int indiceConto;
    
    //metodo che modifica il contenuto della varibile indiceConto, 
    //passandogli l'indice che fa riferimento alla posizione del conto del cliente sulla lista
    //viene richiamato ogni volta che viene visualizzata questa finestra in modo tale da saper sempre su quale conto 
    //vengono effettuate le operazioni
    public void passInformationToController(int indice) 
    {
    	this.indiceConto=indice;
    	
    }
    
    public PagamentiRicaricheController() {}

    @FXML
    void handelRicaricaTel(ActionEvent event) throws IOException  
    {
    	//viene creato un nuovo oggetto di tipo "FXMLoader" che rappresenta la nuova finestra che verra visualizzata
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("RicaricaTelefonica.fxml"));
		AnchorPane root= (AnchorPane)loader.load();
		
		//viene creato un oggetto che ha come tipo la classe che fa riferimento alla nuova finestra visualizzata
		//e viene invocato un metodo setter che consente di passare il valore di una determinata variabile
		//tra finestre diverse
		RicaricaTelefonicaController p=loader.getController();		
		p.passInformationToController(indiceConto);

		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void handleBollettino(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Bollettino.fxml"));
		AnchorPane root= (AnchorPane)loader.load();
		
		BollettinoController p=loader.getController();
		p.passInformationsToController(indiceConto);

		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void handleBonifco(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Bonifico.fxml"));
		AnchorPane root= (AnchorPane)loader.load();
		
		BonificoController p=loader.getController();
		p.passInformationToController(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void handleMavRav(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("MavRav.fxml"));
		AnchorPane root= (AnchorPane)loader.load();
		
		MavRavController p=loader.getController();
		p.passInformationToController(indiceConto);

		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }
    
    @FXML
    void handleUtenze(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Utenze.fxml"));
		AnchorPane root= (AnchorPane)loader.load();
		
		UtenzeController p=loader.getController();
		p.passInformationToController(indiceConto);

		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void initialize() {
        assert btnBonifico != null : "fx:id=\"btnBonifico\" was not injected: check your FXML file 'PagamentiRicariche.fxml'.";
        assert btnBollettino != null : "fx:id=\"btnBollettino\" was not injected: check your FXML file 'PagamentiRicariche.fxml'.";
        assert btnMavRav != null : "fx:id=\"btnMavRav\" was not injected: check your FXML file 'PagamentiRicariche.fxml'.";
        assert btnRicaricaTel != null : "fx:id=\"btnRicaricaTel\" was not injected: check your FXML file 'PagamentiRicariche.fxml'.";

    }
}