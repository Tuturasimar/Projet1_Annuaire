package fr.isika.cda23.projet1.annuaire;


import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class EditScreen extends GridPane {

	public EditScreen() {
		super();

		Label lblTitre = new Label("Ajouter un stagiaire");
		lblTitre.getStyleClass().add("lblRole");

		HBox hbTitre = new HBox();
		hbTitre.getChildren().add(lblTitre);

		Label lblNom = new Label("Nom");
		Label lblPrenom = new Label("Prénom");
		Label lblDepartement = new Label("Département");
		Label lblPromo = new Label("Promotion");
		Label lblAnnee = new Label("Année");
	
		// Labels GridPane central
		this.add(hbTitre,1 , 0);
		this.add(lblNom, 0, 2);
		this.add(lblPrenom, 0, 3);
		this.add(lblDepartement, 0, 4);
		this.add(lblPromo, 0, 5);
		this.add(lblAnnee, 0, 6);
		

		TextField fldNom = new TextField();
		TextField fldPrenom = new TextField();
		TextField fldDepartement = new TextField();
		TextField fldPromo = new TextField();
		TextField fldAnnee = new TextField();
		
		
		fldNom.setFont(Font.font("Avenir",16));
		fldNom.setPromptText("Watson");
		fldPrenom.setFont(Font.font("Avenir",16));
		fldPrenom.setPromptText("Bobby");
		fldDepartement.setFont(Font.font("Avenir",16));
		fldDepartement.setPromptText("75");
		fldPromo.setFont(Font.font("Avenir",16));
		fldPromo.setPromptText("CDA 23");
		fldAnnee.setFont(Font.font("Avenir",16));
		fldAnnee.setPromptText("2023");
		


		this.add(fldNom, 2, 2);
		this.add(fldPrenom, 2, 3);
		this.add(fldDepartement, 2, 4);
		this.add(fldPromo, 2, 5);
		this.add(fldAnnee, 2, 6);
		

		Button btnValidez = new Button("Validez");
		Button btnRetour = new Button("Retour");

		this.add(btnValidez, 2, 8);
		this.add(btnRetour, 0, 8);
		
		EditScreen.setHalignment(btnValidez, HPos.RIGHT);

		this.setVgap(20);
		this.setHgap(10);

		btnValidez.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Noeud nouveauStagiaire = new Noeud(new Stagiaire(fldNom.getText().toUpperCase(), fldPrenom.getText(),
						fldDepartement.getText(), fldPromo.getText(), fldAnnee.getText()));
				nouveauStagiaire.ajouterNoeud(nouveauStagiaire, 0);

				TableauStagiaire tableauStagiaire = new TableauStagiaire();
				BorderPane root = (BorderPane) btnValidez.getScene().getRoot();
				root.setCenter(tableauStagiaire);
				tableauStagiaire.setAlignment(Pos.CENTER);


			}

		});
		
		btnRetour.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TableauStagiaire tableauStagiaire = new TableauStagiaire();
				BorderPane root = (BorderPane) btnRetour.getScene().getRoot();
				root.setCenter(tableauStagiaire);
				tableauStagiaire.setAlignment(Pos.CENTER);
				
			}
		});

	}
}
