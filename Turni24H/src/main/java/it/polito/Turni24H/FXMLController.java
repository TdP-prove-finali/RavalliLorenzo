package it.polito.Turni24H;
/**
 * Sample Skeleton for 'prima.fxml' Controller Class
 */

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.Turni24H.model.Accoppiamenti;
import it.polito.Turni24H.model.Model;
import it.polito.Turni24H.model.Risultati;
import it.polito.Turni24H.model.Staff;
import it.polito.Turni24H.model.TurniAssegnatiSettimanali;
import it.polito.Turni24H.model.Turno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	private Model model;
	private Risultati risultati;
	List<Accoppiamenti> miglioreSol;
	
    @FXML // fx:id="tabPane"
    private TabPane tabPane;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="calcola_btn"
    private Button calcola_btn; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<TurniAssegnatiSettimanali> table; // Value injected by FXMLLoader
    
    @FXML // fx:id="col1"
    private TableColumn<TurniAssegnatiSettimanali, String> col1; // Value injected by FXMLLoader

    @FXML // fx:id="col2"
    private TableColumn<TurniAssegnatiSettimanali, String> col2; // Value injected by FXMLLoader

    @FXML // fx:id="col3"
    private TableColumn<TurniAssegnatiSettimanali, String> col3; // Value injected by FXMLLoader

    @FXML // fx:id="col4"
    private TableColumn<TurniAssegnatiSettimanali, String> col4; // Value injected by FXMLLoader

    @FXML // fx:id="col5"
    private TableColumn<TurniAssegnatiSettimanali, String> col5; // Value injected by FXMLLoader

    @FXML // fx:id="col6"
    private TableColumn<TurniAssegnatiSettimanali, String> col6; // Value injected by FXMLLoader

    @FXML // fx:id="col7"
    private TableColumn<TurniAssegnatiSettimanali, String> col7; // Value injected by FXMLLoader

    @FXML // fx:id="colStaff"
    private TableColumn<TurniAssegnatiSettimanali, Staff> colStaff; // Value injected by FXMLLoader

    @FXML // fx:id="richiestaDayOff_Check"
    private CheckBox richiestaDayOff_Check; // Value injected by FXMLLoader

    @FXML // fx:id="richiestaDayOn_Check"
    private CheckBox richiestaDayOn_Check; // Value injected by FXMLLoader

    @FXML // fx:id="risultati_Tab"
    private Tab risultati_Tab; // Value injected by FXMLLoader

    @FXML // fx:id="settimana_cbx"
    private ComboBox<String> settimana_cbx; // Value injected by FXMLLoader

    @FXML // fx:id="suggerimenti_txt"
    private TextArea suggerimenti_txt; // Value injected by FXMLLoader

    @FXML // fx:id="turniCompl_text"
    private TextField turniCompl_text; // Value injected by FXMLLoader

    @FXML // fx:id="turni_Tab"
    private Tab turni_Tab; // Value injected by FXMLLoader
    
    @FXML // fx:id="generaTabella_btn"
    private Button generaTabella_btn; // Value injected by FXMLLoader
    
    @FXML // fx:id="pieChartTurni"
    private PieChart pieChartTurni;
    
    @FXML // fx:id="turniNonCompl_txt"
    private TextArea turniNonCompl_txt;
    
    @FXML // fx:id="staffOreMeno_txt"
    private TextArea staffOreMeno_txt;

    @FXML // fx:id="staffStraordinario_txt"
    private TextArea staffStraordinario_txt;
    
    @FXML // fx:id="staffOreDisp_txt"

    private TextArea staffOreDisp_txt;
    
    @FXML // fx:id="straord_cbx"

    private ComboBox<Integer> straord_cbx;
    
    @FXML // fx:id="prof_cbx"

    private ComboBox<Integer> prof_cbx;
    
    @FXML // fx:id="weekend_cbx"

    private ComboBox<Integer> weekend_cbx;
    
    @FXML // fx:id="pageVincoli"
    private Tab pageVincoli;
    
    @FXML // fx:id="listaVincoli_txt"
    private TextArea listaVincoli_txt;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert calcola_btn != null : "fx:id=\"calcola_btn\" was not injected: check your FXML file 'prima.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'prima.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'prima.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'prima.fxml'.";
        assert col4 != null : "fx:id=\"col4\" was not injected: check your FXML file 'prima.fxml'.";
        assert col5 != null : "fx:id=\"col5\" was not injected: check your FXML file 'prima.fxml'.";
        assert col6 != null : "fx:id=\"col6\" was not injected: check your FXML file 'prima.fxml'.";
        assert col7 != null : "fx:id=\"col7\" was not injected: check your FXML file 'prima.fxml'.";
        assert colStaff != null : "fx:id=\"colStaff\" was not injected: check your FXML file 'prima.fxml'.";
        assert richiestaDayOff_Check != null : "fx:id=\"richiestaDayOff_Check\" was not injected: check your FXML file 'prima.fxml'.";
        assert richiestaDayOn_Check != null : "fx:id=\"richiestaDayOn_Check\" was not injected: check your FXML file 'prima.fxml'.";
        assert risultati_Tab != null : "fx:id=\"risultati_Tab\" was not injected: check your FXML file 'prima.fxml'.";
        assert settimana_cbx != null : "fx:id=\"settimana_cbx\" was not injected: check your FXML file 'prima.fxml'.";
        assert suggerimenti_txt != null : "fx:id=\"suggerimenti_txt\" was not injected: check your FXML file 'prima.fxml'.";
        assert turniCompl_text != null : "fx:id=\"turniCompl_text\" was not injected: check your FXML file 'prima.fxml'.";
        assert turni_Tab != null : "fx:id=\"turni_Tab\" was not injected: check your FXML file 'prima.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'prima.fxml'.";
        assert generaTabella_btn != null : "fx:id=\"generaTabella_btn\" was not injected: check your FXML file 'prima.fxml'.";
        assert pieChartTurni != null : "fx:id=\"pieChartTurni\" was not injected: check your FXML file 'prima.fxml'.";
        assert turniNonCompl_txt != null : "fx:id=\"turniNonCompl_txt\" was not injected: check your FXML file 'prima.fxml'.";
        assert staffOreMeno_txt != null : "fx:id=\"staffOreMeno_txt\" was not injected: check your FXML file 'prima.fxml'.";
        assert staffStraordinario_txt != null : "fx:id=\"staffStraordinario_txt\" was not injected: check your FXML file 'prima.fxml'.";
        assert prof_cbx != null : "fx:id=\"prof_cbx\" was not injected: check your FXML file 'prima.fxml'.";
        assert weekend_cbx != null : "fx:id=\"weekend_cbx\" was not injected: check your FXML file 'prima.fxml'.";
        assert straord_cbx != null : "fx:id=\"straord_cbx\" was not injected: check your FXML file 'prima.fxml'.";
        assert staffOreDisp_txt != null : "fx:id=\"staffOreDisp_txt\" was not injected: check your FXML file 'prima.fxml'.";
        assert pageVincoli != null : "fx:id=\"pageVincoli\" was not injected: check your FXML file 'turni.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'turni.fxml'.";
        tabPane.getTabs().remove(pageVincoli);
        assert listaVincoli_txt != null : "fx:id=\"listaVincoli_txt\" was not injected: check your FXML file 'turni.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		for(int i = 1; i<8; i++) {
			this.weekend_cbx.getItems().add(i);
		}
		for(int i = 0; i<4; i++) {
			this.straord_cbx.getItems().add(i);
		}
		for(int i = 1; i<11; i++) {
			this.prof_cbx.getItems().add(i);
		}
    	
	}
	
	 @FXML // ResourceBundle that was given to the FXMLLoader
	void calcola_btn(ActionEvent event) {
		pulisci();
		boolean richiestaDayOff = this.richiestaDayOff_Check.isSelected();
		boolean richiestaDayOn = this.richiestaDayOn_Check.isSelected();
		int straordinari = straord_cbx.getValue();
		int weekend = this.weekend_cbx.getValue();
		int profondita = this.prof_cbx.getValue();
		
		miglioreSol = model.cercaLista(profondita, richiestaDayOff, richiestaDayOn, weekend, straordinari);
		this.risultati = new Risultati(miglioreSol);
		
		int turniNonCompletati = risultati.getTurniNonCompletati();
		Map<Turno, Integer> mapTurniNonCompl = risultati.getMapTurniIncompleti();
		int turniTotaliRichiesti = model.getTurniTotaliRichiesti();
		this.turniCompl_text.appendText(turniTotaliRichiesti-turniNonCompletati + " su " + turniTotaliRichiesti);
		for(Turno t : mapTurniNonCompl.keySet()) {
			this.turniNonCompl_txt.appendText(t.toStringCompleto() + " --- " + mapTurniNonCompl.get(t) + " ancora da aggiungere\n");
		}
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Turni completati", turniTotaliRichiesti-turniNonCompletati),
                new PieChart.Data("Turni NON completati", turniNonCompletati)
        );
        pieChartTurni.setData(pieChartData);
       
        
		Map<Staff, Integer> staffStraordinario = risultati.getStaffStraordinario();
		
		for(Staff s : staffStraordinario.keySet()) {
			this.staffStraordinario_txt.appendText(s + " con " + staffStraordinario.get(s) + " turni di straordinario\n");
		}
		Map<Staff, Integer> staffOreInMeno = risultati.getStaffOreInMeno();
		for(Staff s : staffOreInMeno.keySet()) {
			this.staffOreMeno_txt.appendText(s + " con " + staffOreInMeno.get(s) + " turni in meno da assegnare\n");
		}
		Map<Staff, Integer> staffConOreDisponibili = risultati.getStaffConOreDisponibili();
		for(Staff s : staffConOreDisponibili.keySet()) {
			this.staffOreDisp_txt.appendText(s + " con " + staffConOreDisponibili.get(s) + " turni disponibili\n");
		}
		
		
		this.suggerimenti_txt.appendText("Non sei soddisfatto del risultato? --- Prova a ricalcolare i turni! \n\n"
				+ "Ricorda alzando il numero di PROFONDITA' aumenta il tempo di calcolo ma anche la precisione! \n\n"
				+ "Ricorda se aumenti gli STRAORDINARI possibili diminuirà la felicità dei dipendenti!\n\n"
				+ "Ricorda più WEEKEND garantisci più si complica trovare la soluzione ottimale\n\n"
				+ "Ricorda accettare le RICHIESTE dei dipendente aumenterà la felicita e semplificherà l'algoritmo");
		
		this.settimana_cbx.getItems().add("Prima settimana");
		this.settimana_cbx.getItems().add("Seconda settimana");
		this.settimana_cbx.getItems().add("Terza settimana");
		this.settimana_cbx.getItems().add("Quarta settimana");
		
		this.risultati_Tab.setDisable(false);
		this.turni_Tab.setDisable(false);
		
		this.listaVincoli_txt.appendText(vincoli());
	}

	private String vincoli() {
		String s = "Ecco la lista dei vincoli già considerati: \n\n"
				+ "1 -- Non si superano le ore massimo a meno di straordinario\n\n"
				+ "2 -- Massimo 5 giorni di fila, e massimo 5 giorni in una settimana\n\n"
				+ "3 -- Massimo 2 notti di fila, e dopo notte non posso fare la mattina o il pomeriggio dopo (smonto) \n\n"
				+ "4 -- Se faccio pomeriggio il giorno dopo non posso fare mattina\n\n"
				+ "5 -- Non si possono superare 1/3 delle ore in notti\n\n"
				+ "6 -- Non si possono siperare 1/2 delle ore in mattine o pomeriggi\n\n";
		return s;
	}

	private void pulisci() {
	this.suggerimenti_txt.clear();
	this.turniCompl_text.clear();
	this.turniNonCompl_txt.clear();
	this.staffStraordinario_txt.clear();
	this.staffOreMeno_txt.clear();
	this.settimana_cbx.getItems().clear();
	table.getItems().clear();
		
	}
	
	 @FXML // ResourceBundle that was given to the FXMLLoader
    void generaTabella_btn(ActionEvent event) {
    	ObservableList<TurniAssegnatiSettimanali> lista = FXCollections.observableArrayList();
   
    	if(this.settimana_cbx.getValue().compareTo("Prima settimana") == 0)
    	{
    		lista.addAll(model.generaListaSettimana(1));
    	}
    	if(this.settimana_cbx.getValue().compareTo("Seconda settimana") == 0)
    	{
    		lista.addAll(model.generaListaSettimana(2));
    	}
    	if(this.settimana_cbx.getValue().compareTo("Terza settimana") == 0)
    	{
    		lista.addAll(model.generaListaSettimana(3));
    	}
    	if(this.settimana_cbx.getValue().compareTo("Quarta settimana") == 0)
    	{
    		lista.addAll(model.generaListaSettimana(4));
    	}
    	
    	colStaff.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, Staff>("s"));
    	col1.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg1"));
    	col2.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg2"));
    	col3.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg3"));
    	col4.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg4"));
    	col5.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg5"));
    	col6.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg6"));
    	col7.setCellValueFactory(new PropertyValueFactory<TurniAssegnatiSettimanali, String>("gg7"));
    	table.setItems(lista);
    	
    }
	    @FXML // ResourceBundle that was given to the FXMLLoader
	    void apriFinestraVincoli(ActionEvent event) {
	    	this.listaVincoli_txt.clear();
	    	this.tabPane.getTabs().add(pageVincoli);
	    	this.listaVincoli_txt.appendText(vincoli());
	    	this.tabPane.getSelectionModel().select(pageVincoli);;
	    }


}
