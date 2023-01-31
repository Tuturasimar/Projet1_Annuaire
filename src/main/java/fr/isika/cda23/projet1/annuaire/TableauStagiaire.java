package fr.isika.cda23.projet1.annuaire;

import java.util.List;

import fr.isika.cda23.projet1.models.Stagiaire;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class TableauStagiaire extends GridPane {

	public TableauStagiaire(List<Stagiaire> list) {
		super();

		TableView<Stagiaire> tableStagiaire = new TableView<Stagiaire>();
		tableStagiaire.setEditable(true);

		// Creation des colonnes

		TableColumn<Stagiaire, String> nomStagiaire = new TableColumn<Stagiaire, String>("Nom");
		nomStagiaire.setMinWidth(100);

		// Spécifier comment remplir la donnée pour chaque cellule de cette colonne
		// Ceci se fait en specifiant un "cell value factory" pour cette colonne.

		nomStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> prenomStagiaire = new TableColumn<Stagiaire, String>("Prénom");
		prenomStagiaire.setMinWidth(100);
		prenomStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		nomStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> promoStagiaire = new TableColumn<Stagiaire, String>("Promotion");
		promoStagiaire.setMinWidth(100);
		promoStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));

		TableColumn<Stagiaire, String> anneeStagiaire = new TableColumn<Stagiaire, String>("Année");
		anneeStagiaire.setMinWidth(100);
		anneeStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

		TableColumn<Stagiaire, String> dptStagiaire = new TableColumn<Stagiaire, String>("Département");
		dptStagiaire.setMinWidth(100);
		dptStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		tableStagiaire.getColumns().addAll(nomStagiaire, prenomStagiaire, promoStagiaire, anneeStagiaire, dptStagiaire);

		// On rempli la table avec la liste observable
		tableStagiaire.setItems(FXCollections.observableArrayList(list));
		this.add(tableStagiaire, 0, 0);

	}
}
