package fr.isika.cda23.projet1.annuaire;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HomeScreen extends GridPane {

	public HomeScreen() {
		super();

		Label lblTitre = new Label("Acceuil");
		HBox hbTitre = new HBox();
		hbTitre.getChildren().add(lblTitre);

		Label lblRole = new Label("Choisissez votre rôle");
		Button btnStagiaire = new Button("Stagiaire");
		Button btnAdmin = new Button("Admin");
		HBox hbRole = new HBox();

		hbRole.getChildren().addAll(lblRole, btnAdmin, btnStagiaire);

//	Labels GridPane central
		this.add(lblTitre, 0, 0);
		this.add(lblRole, 0, 1);
		this.add(btnStagiaire, 0, 2);
		this.add(btnAdmin, 1, 2);
		this.setVgap(10);
	}

}