package fr.isika.cda23.projet1.annuaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.FileDriver;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {

		// recine de ma fenetre
		BorderPane root = new BorderPane();

		// Header
		HBox hbLogo = new HBox();
		root.setTop(hbLogo);
		try {
			Image image = new Image(new FileInputStream("LogoCarré.png"));
			ImageView imageView = new ImageView(image);
			imageView.setX(50);
			imageView.setY(25);

			// setting the fit height and width of the image view
			imageView.setFitHeight(100);
			imageView.setFitWidth(300);

			Group grpTest = new Group(imageView);
			hbLogo.getChildren().add(grpTest);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		root.setTop(hbLogo);
		hbLogo.setAlignment(Pos.CENTER);
		hbLogo.setStyle("-fx-background-color: bisque");

		// GridPane central

		
		HomeScreen homeScreen = new HomeScreen();

		root.setCenter(homeScreen);
		homeScreen.setAlignment(Pos.CENTER);

		// Footer
		HBox hbFooter = new HBox();
		Label lblFooter = new Label("Projet 1 - Annuaire - Groupe de travail : Arbam, Senkoun, Trévor");
		hbFooter.getChildren().add(lblFooter);
		root.setBottom(hbFooter);
		hbFooter.setAlignment(Pos.CENTER);
		hbFooter.setStyle("-fx-background-color: bisque");
		hbFooter.setPrefHeight(100);
		hbFooter.setMinHeight(100);
		hbFooter.setMaxHeight(100);

		// Scene
		Scene scene = new Scene(root, 900, 700);
		scene.getRoot().setStyle("-fx-font-family: 'serif'");
		scene.getStylesheets().add(getClass().getResource("/ressources/css/style.css").toString());
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		// Si le fichier binaire n'existe pas encore ou n'est pas rempli
		if (FichierBinaire.lastIndex() == 0) {
			// On récupère l'ensemble des données du fichier texte pour les écrire dans le
			// fichier BIN
			FileDriver.readTextFile();
		}

		launch();
	}

}