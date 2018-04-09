package com.github.Petrnet.mojeadventura.uiText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.github.Petrnet.mojeadventura.logika.IHra;
import com.github.Petrnet.mojeadventura.logika.Lokace;
import com.github.Petrnet.mojeadventura.logika.Predmet;
import com.github.Petrnet.mojeadventura.logika.Dinosaurus;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author     Petr Netolický
 * @version    LS 2017/2018
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private Button odesli;
	@FXML private ListView<Lokace> seznamVychodu;
	@FXML private ImageView hrac;
	@FXML private ListView<Dinosaurus> seznamDinosauru;
	@FXML private ListView<ImageView> inventarBatoh;
	@FXML private ListView<ImageView> seznamPredmetuLokace;
	@FXML private ImageView mapa;
	@FXML private MenuBar menu;
	@FXML private MenuItem novahra;
	@FXML private MenuItem konec;
	private IHra hra;
	private ObservableList<ImageView> observableList1;
	private ObservableList<ImageView> observableList2;
	
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		
		if(hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
			odesli.setDisable(true);
		}
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 * 
	 * 
	 */
	
	public void inicializuj(IHra hra) {
	
		this.hra = hra;
		List<ImageView> list1 = new ArrayList<ImageView>();
		observableList2 = FXCollections.observableList(list1);
		List<ImageView> list2 = new ArrayList<ImageView>();
		observableList1 = FXCollections.observableList(list2);
	  vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		seznamDinosauru.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getDinosaury());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		upravLokaci();
		upravInventar();
	hra.getHerniPlan().addObserver(this);
		hra.getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		hrac.setX(hra.getHerniPlan().getAktualniLokace().getX());
		hrac.setY(hra.getHerniPlan().getAktualniLokace().getY());
	}
	/**
	 * Metoda pro zpracování konce hry
	 */
	
		public void konecHry() {
			Platform.exit();
		}
		/**
		 * Metoda pro zpracování nové hry
		 */
		public void Hra() {
			seznamVychodu.getItems().clear();
			seznamPredmetuLokace.getItems().clear();
			seznamDinosauru.getItems().clear();
			inventarBatoh.getItems().clear();
			hra.novaHra();
			this.inicializuj(hra);
			vystup.setDisable(false);
			vstupniText.setDisable(false);
			odesli.setDisable(false);
		}
		/**
		 * Metoda pro zpracování nápověda
		 */
		
		public void napoveda() {
			Stage stage = new Stage();
	        stage.setTitle("Nápověda k aplikaci");
	        WebView webview = new WebView();
	        webview.getEngine().load(
	                getClass().getResource("./Napoveda.html").toExternalForm()
	        );
	        stage.setScene(new Scene(webview, 500, 500));
	        stage.show();
		
		}
		
		/**
		 * Metoda slouží k aktualizaci souřadnic i změn v GUI
		 */

	@Override
	public void update(Observable arg0, Object arg1) {
		seznamVychodu.getItems().clear();
		seznamDinosauru.getItems().clear();
		seznamPredmetuLokace.getItems().clear();
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		seznamDinosauru.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getDinosaury());
		upravLokaci();
		upravInventar();
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		hrac.setX(hra.getHerniPlan().getAktualniLokace().getX());
		hrac.setY(hra.getHerniPlan().getAktualniLokace().getY());
		
}
	public void upravLokaci() {
		observableList2.removeAll(observableList2);		
		for (String nazev : hra.getHerniPlan().getAktualniLokace().getVsechnyPredmety().keySet()) {
            	String URI = hra.getHerniPlan().getAktualniLokace().getPredmet(nazev).getOdkazObrazek();
            	Image pic = new Image(getClass().getResourceAsStream(URI));
            	ImageView image = new ImageView(pic);
            	image.setId(nazev);
            	observableList2.add(image);
            }
		seznamPredmetuLokace.setItems(observableList2);
		
	}
	
	public void upravInventar() {
		observableList1.removeAll(observableList1);		
		for (String nazev : hra.getHerniPlan().getBatoh().getSeznamPredmetu().keySet()) {
        	String URI = hra.getHerniPlan().getBatoh().getPredmet(nazev).getOdkazObrazek();
        	Image pic = new Image(getClass().getResourceAsStream(URI));
        	ImageView image = new ImageView(pic);
        	image.setId(nazev);
        	observableList1.add(image);	
    
		}
		inventarBatoh.setItems(observableList1);
	}

}
	
	
	
	
	
	
	
	

