package fr.isika.cda23.projet1.annuaire;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HomeScreen extends GridPane {

	public HomeScreen() {
		super();

		Label lblTitre = new Label("Bienvenue");
		HBox hbTitre = new HBox();
		hbTitre.getChildren().add(lblTitre);
		lblTitre.getStyleClass().add("lblTitre");
		hbTitre.setAlignment(Pos.CENTER);
		

		Label lblRole = new Label("Choisissez votre rôle :");
		lblRole.getStyleClass().add("lblRole");
		Button btnStagiaire = new Button("Stagiaire");
		Button btnAdmin = new Button("Administrateur");
		HBox hbRole = new HBox();

		hbRole.getChildren().addAll(lblRole, btnAdmin, btnStagiaire);

//	Labels GridPane central
		this.add(hbTitre,1,0);
		this.add(lblRole, 1, 1);
		this.add(btnStagiaire, 0, 3);
		this.add(btnAdmin, 2, 3);
		this.setVgap(40);
		this.setHgap(40);
		//this.setGridLinesVisible(true);

		btnAdmin.setOnAction(event -> {
			AdminLoginScreen adminLoginScreen = new AdminLoginScreen();
			BorderPane root = (BorderPane) btnAdmin.getScene().getRoot();
			root.setCenter(adminLoginScreen);
			adminLoginScreen.setAlignment(Pos.CENTER);
		});

		btnStagiaire.setOnAction(event -> {
			TableauStagiaire tableau = new TableauStagiaire();
			BorderPane root = (BorderPane) btnStagiaire.getScene().getRoot();
			root.setCenter(tableau);
			tableau.setAlignment(Pos.CENTER);
		});
	}
}