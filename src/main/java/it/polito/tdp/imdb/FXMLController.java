/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Actor;
import it.polito.tdp.imdb.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimili"
    private Button btnSimili; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimulazione"
    private Button btnSimulazione; // Value injected by FXMLLoader

    @FXML // fx:id="boxGenere"
    private ComboBox<String> boxGenere; // Value injected by FXMLLoader

    @FXML // fx:id="boxAttore"
    private ComboBox<Actor> boxAttore; // Value injected by FXMLLoader

    @FXML // fx:id="txtGiorni"
    private TextField txtGiorni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAttoriSimili(ActionEvent event) {
    	txtResult.clear();
        Actor actor = this.boxAttore.getValue();
    	if(actor==null) {
    		txtResult.appendText("Iserisci un attore.");
    		return;
    	}
    	List<Actor> simili = this.model.getSimili(actor);
    	for(Actor a : simili) {
    		txtResult.appendText(a+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
        this.txtResult.clear();
    	String genre = this.boxGenere.getValue();
    	if(genre==null) {
    		txtResult.appendText("Iserisci un genere.");
    		return;
    	}
    	this.model.creaGrafo(genre);
    	txtResult.appendText("GRAFO CREATO!\n");
    	txtResult.appendText("#VERTICI : "+this.model.getNumVertici());
    	txtResult.appendText("\n#ARCHI : "+this.model.getNumArchi());
    	
    	this.boxAttore.getItems().addAll(this.model.getAttori());
    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	this.txtResult.clear();
     	String genre = this.boxGenere.getValue();
     	if(genre==null) {
     		txtResult.appendText("Iserisci un genere.");
     		return;
     	}
    	String numeroGiorni = this.txtGiorni.getText();
    	try {
    		int days = Integer.parseInt(numeroGiorni);
    		if(days<=0) {
        		this.txtResult.setText("inserisci un numero di giorni maggiore di 0!");
        		return;
    		}
    		this.model.getAttoriIntervistati(genre,days);
    		Collection<Actor> intervistati = this.model.getInterviste();
    		int ferie = this.model.getFerie();
    		this.txtResult.setText("giorni ferie : "+ferie+" intervistati: \n");
    		for(Actor a : intervistati) {
        		txtResult.appendText(a+"\n");
        	}
    	}catch (NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.setText("inserisci un numero di giorni maggiore di 0!");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimili != null : "fx:id=\"btnSimili\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimulazione != null : "fx:id=\"btnSimulazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGenere != null : "fx:id=\"boxGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAttore != null : "fx:id=\"boxAttore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGiorni != null : "fx:id=\"txtGiorni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.boxGenere.getItems().addAll(this.model.getGenres());
    }
}
