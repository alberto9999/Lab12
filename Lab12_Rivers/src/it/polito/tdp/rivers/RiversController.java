package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import it.polito.tdp.rivers.Model.Model;
import it.polito.tdp.rivers.Model.River;


public class RiversController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;
    
    
    Model model;
	public void setModel(Model model) {
		this.model=model;
		boxRiver.getItems().addAll(model.getAllRivers());		
	}
	
    @FXML
    void doInfo(ActionEvent event) {
       River r= boxRiver.getValue();
       txtStartDate.setText( model.getPrimaData(r).toString());
       txtEndDate.setText( model.getUltimaData(r).toString());
       txtNumMeasurements.setText(String.valueOf(model.getNumeroMisure(r)));
       txtFMed.setText(String.valueOf(model.getAVGFlusso(r)));
    }
   
    
    @FXML
    void doSimula(ActionEvent event) {
    	try{
   River r= boxRiver.getValue();
   if(Float.parseFloat(txtK.getText())<=0){
	   txtResult.setText("k deve essere >0");
   }
   else{
   model.simula(r,Float.parseFloat(txtK.getText()),Float.parseFloat(txtFMed.getText()));
   txtResult.setText("NO EROGAZIONE: "+model.getStat().getNoErogazione()+"\nOCCUPAZIONE MEDIA: "+model.getStat().getOccupazioneMedia());
   }}
    	catch (RuntimeException re) {
    		txtResult.setText("FORMATO INPUT NON VALIDO ");
    	}
    	}

	
    

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }


}
