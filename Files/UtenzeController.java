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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UtenzeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblAziende;

    @FXML
    private MenuButton menuAziende;

    @FXML
    private Label lblNumBolletta;

    @FXML
    private TextField txtNumBolletta;

    @FXML
    private Label lblImporto;

    @FXML
    private TextField txtImporto;

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
    
    private Alert errore=new Alert(Alert.AlertType.ERROR);
    
    public void passInformationToController(int indice) 
    {
    	this.indiceConto=indice;
    	
    }
    
    public UtenzeController() 
    {
    	banca=new Banca();
    	logIn=new LoginController();
    	
    }

    @FXML
    void handleEnelEnergia(ActionEvent event) 
    {
    	menuAziende.setText("ENEL ENERGIA");

    }

    @FXML
    void handleEni(ActionEvent event) 
    {
    	menuAziende.setText("ENI");

    }

    @FXML
    void handleHera(ActionEvent event) 
    {
    	menuAziende.setText("HERA");

    }
    
    @FXML
    void handleSorgenia(ActionEvent event) 
    {
    	menuAziende.setText("SORGENIA");

    }

    @FXML
    void handleItalGas(ActionEvent event) 
    {
    	menuAziende.setText("ITALGAS");

    }

    @FXML
    void handleOk(ActionEvent event) 
    {
    	//si verifica che l'utente selezioni un determinato campo per entrambi i menu
    	if(menuAziende.getText()=="") 
    	{
    		errore.setContentText("Selezioni una delle aziende che erogano gas o luce per effettuare la ricarica.");
    		errore.show();
    	}
    	else if(txtNumBolletta.getLength()!=9) 
    	{
    		errore.setContentText("Inserisca correttamente il numero della bolletta, deve essere di 9 cifre.");
    		errore.show();
    	}
    	else 
    	{
    		String azienda=menuAziende.getText();
    		double importo=Double.parseDouble(txtImporto.getText());
    		
    		ArrayList<Conto>contiCorrenti=new ArrayList<Conto>();
        	
        	contiCorrenti=logIn.getContiCorrenti();
        	
        	////si verifica che la somma da pagare per le utenze non sia maggiore del saldo a disposizione
        	if(contiCorrenti.get(indiceConto).prelievo(importo)) 
        	{
        		//viene aggiornato il conto corrente con il nuovo saldo
        		banca.aggiornaContoCorrente(contiCorrenti, "ContiCorrenti.txt",errore);
        		
        		String descrizione="effettuato pagamento bolletta "+azienda;
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
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuOperations.fxml"));
		BorderPane root=(BorderPane)loader.load();
		OperationsController p=loader.getController();
		
		p.passInformationToController(indiceConto);
		
		
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();

    }

    @FXML
    void initialize() {
        assert lblAziende != null : "fx:id=\"lblAziende\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert menuAziende != null : "fx:id=\"menuAziende\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert lblNumBolletta != null : "fx:id=\"lblNumBolletta\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert txtNumBolletta != null : "fx:id=\"txtNumBolletta\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert lblImporto != null : "fx:id=\"lblImporto\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert txtImporto != null : "fx:id=\"txtImporto\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert lblInformation != null : "fx:id=\"lblInformation\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert btnEsci != null : "fx:id=\"btnEsci\" was not injected: check your FXML file 'Utenze.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'Utenze.fxml'.";
        lblInformation.setText("Selezioni l'azienda intestataria della bolletta, \ninserisca il numero e successivamente l'importo\ne poi clicchi su 'OK' per confermare.");
    }
}
