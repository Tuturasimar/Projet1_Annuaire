package fr.isika.cda23.projet1.annuaire;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.ListeStagiaires;
import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EditScreen extends GridPane {

	public EditScreen() {
		super();

		ListeStagiaires liste = new ListeStagiaires();

		Noeud racine = FichierBinaire.lireNoeud(0);

		Label lblTitre = new Label("Edit");
		HBox hbTitre = new HBox();
		hbTitre.getChildren().add(lblTitre);

		Label lblNom = new Label("Nom");
		Label lblPrenom = new Label("Prenom");
		Label lblPromo = new Label("Promo");
		Label lblAnnee = new Label("Annee");
		Label lblDepartement = new Label("Departement");

		// Labels GridPane central
		this.add(lblNom, 0, 0);
		this.add(lblPrenom, 0, 1);
		this.add(lblPromo, 0, 2);
		this.add(lblAnnee, 0, 3);
		this.add(lblDepartement, 0, 4);

		TextField fldNom = new TextField();
		TextField fldPrenom = new TextField();
		TextField fldPromo = new TextField();
		TextField fldAnnee = new TextField();
		TextField fldDepartement = new TextField();

		this.add(fldNom, 1, 0);
		this.add(fldPrenom, 1, 1);
		this.add(fldPromo, 1, 2);
		this.add(fldAnnee, 1, 3);
		this.add(fldDepartement, 1, 4);

		Button btnValidez = new Button("Validez");
		Button btnRetour = new Button("Retour");

		this.add(btnValidez, 0, 5);
		this.add(btnRetour, 1, 5);

		this.setVgap(10);
		this.setHgap(10);

		btnValidez.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Noeud nouveauStagiaire = new Noeud(new Stagiaire(fldNom.getText().toUpperCase(), fldPrenom.getText(),
						fldDepartement.getText(), fldPromo.getText(), fldAnnee.getText()));
				nouveauStagiaire.ajouterNoeud(nouveauStagiaire, 0);

				racine.parcoursInfixe(0, liste);

//				for (Stagiaire stagiaire : liste.getListe()) {
//					System.out.println(stagiaire);
//				}
				TableauStagiaire tableauStagiaire = new TableauStagiaire();

				Scene scene = new Scene(tableauStagiaire);
				scene.getRoot().setStyle("-fx-font-family: 'serif'");
				Stage stage = (Stage) EditScreen.this.getScene().getWindow();
				stage.setScene(scene);
				
//				scene = btnRetour.getScene();
//				scene.setRoot(new TableauStagiaire(liste.getListe()));

				liste.clearList();

			}

		});

	}
}
