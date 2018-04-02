package com.github.Petrnet.mojeadventura.uiText;

import java.util.Observable;
import java.util.Observer;

import com.github.Petrnet.mojeadventura.logika.IHra;
import com.github.Petrnet.mojeadventura.logika.Lokace;
import com.github.Petrnet.mojeadventura.logika.Predmet;
import com.github.Petrnet.mojeadventura.logika.Dinosaurus;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @Petr Netolický
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private Button odesli;
	@FXML private ListView<Predmet> seznamPredmetuLokace;
	@FXML private ListView<Lokace> seznamVychodu;
	@FXML private ImageView hrac;
	@FXML private ListView<Predmet> inventarBatoh;
	@FXML private ListView<Dinosaurus> seznamDinosauru;
	@FXML private ImageView mapa;
	@FXML private MenuBar menu;
	@FXML private MenuItem novahra;
	@FXML private MenuItem konec;
	private IHra hra;
	
	
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
	  vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		seznamPredmetuLokace.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPredmet());
		seznamDinosauru.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getDinosaury());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		inventarBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getPredmetyBatoh());
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		hrac.setX(hra.getHerniPlan().getAktualniLokace().getX());
		hrac.setY(hra.getHerniPlan().getAktualniLokace().getY());
		hra.getHerniPlan().addObserver(this);
	}
	
	
		public void konecHry() {
			Platform.exit();
		}
		
		public void Hra() {
			seznamVychodu.getItems().clear();
			seznamPredmetuLokace.getItems().clear();
			seznamDinosauru.getItems().clear();
			hra.Hra();
			this.inicializuj(hra);
		}
		
		public void informace() {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Jurský park");
	        alert.setHeaderText("Jurský park - skvělá hra");
	        alert.setContentText("Petr Netolický, VŠE FIS");
	        alert.showAndWait();
		}
		
		public void napoveda() {
			Stage stage = new Stage();
	        stage.setTitle("Nápověda k aplikaci");
	        WebView webview = new WebView();
	        webview.getEngine().load(
	                getClass().getResource("./napoveda.html").toExternalForm()
	        );
	        stage.setScene(new Scene(webview, 500, 500));
	        stage.show();
		
		}
		

	@Override
	public void update(Observable arg0, Object arg1) {
		seznamVychodu.getItems().clear();
		seznamDinosauru.getItems().clear();
		seznamPredmetuLokace.getItems().clear();
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		seznamPredmetuLokace.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPredmet());
		seznamDinosauru.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getDinosaury());
		inventarBatoh.getItems().clear();
		inventarBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getPredmetyBatoh());
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		hrac.setX(hra.getHerniPlan().getAktualniLokace().getX());
		hrac.setY(hra.getHerniPlan().getAktualniLokace().getY());
		
	}

}
