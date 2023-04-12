package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OperationsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVersamento;

    @FXML
    private Button btnPrelievo;

    @FXML
    private Button btnEstrattoConto;
    
    @FXML
    private Button btnPagamentiRicariche;

    @FXML
    private Label lblInformation;
    
    private int indiceConto;
    
    //metodo che modifica il contenuto della varibile indiceConto, 
    //passandogli l'indice che fa riferimento alla posizione del conto del cliente sulla lista
    //viene richiamato ogni volta che viene visualizzata questa finestra in modo tale da saper sempre su quale conto 
    //vengono effettuate le operazioni
    public void passInformationToController(int indice) 
    {
    	this.indiceConto=indice;
    	
    }
    
  

    @FXML
    void handleEstrattoConto(ActionEvent event) throws IOException 
    {
    	//viene creato un nuovo oggetto di tipo "FXMLoader" che rappresenta la nuova finestra che verra visualizzata
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("EstrattoConto.fxml"));
		AnchorPane root=(AnchorPane)loader.load();
		
		//viene creato un oggetto che ha come tipo la classe che fa riferimento alla nuova finestra visualizzata
		//e viene invocato un metodo setter che consente di passare il valore di una determinata variabile
		//tra finestre diverse
		EstrattoContoController p=loader.getController();
		p.passInformationsToEstratto(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void handlePrelievo(ActionEvent event) throws IOException 
    {
    	
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Prelievo.fxml"));
		AnchorPane root=(AnchorPane)loader.load();
		PrelievoController p=loader.getController();
		
		p.passInformationsToController(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();
    	

    }
    
   

    @FXML
    void handleVersamento(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Versamento.fxml"));
		AnchorPane root=(AnchorPane)loader.load();
		
		VersamentoController p=loader.getController();
		p.passInformationsToController(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();	

    }
    
    @FXML
    void handlePagamentiRicariche(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("PagamentiRicariche.fxml"));
		Parent root=(Parent) loader.load();
		
		PagamentiRicaricheController p=loader.getController();
		p.passInformationToController(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void initialize() {
        assert btnVersamento != null : "fx:id=\"btnVersamento\" was not injected: check your FXML file 'prelievoVersamento.fxml'.";
        assert btnPrelievo != null : "fx:id=\"btnPrelievo\" was not injected: check your FXML file 'prelievoVersamento.fxml'.";
        assert btnEstrattoConto != null : "fx:id=\"btnEstrattoConto\" was not injected: check your FXML file 'prelievoVersamento.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'prelievoVersamento.fxml'.";
        lblInformation.setText("Benvenuto, quale operazione desidera effettuare?\n-Clicchi sul pulsante 'VERSAMENTO',\n per proseguire con l'operazione;\n-Clicchi sul pulsante 'PRELIEVO',\n per proseguire con l'operazione;\n-Clicchi sul pulsante 'ESTRATTO CONTO',\n per ottenere una stampa di tutti i movimenti effettuati\n sul proprio conto;\n-Clicchi sul pulsante 'RICARICHE/PAGAMENTI'\nper effettuare pagamenti di:\nbonifici, bollettini postali, mav/rav;\noppure ricariche telelefoniche e postepay.");
    	
    }

	
}
