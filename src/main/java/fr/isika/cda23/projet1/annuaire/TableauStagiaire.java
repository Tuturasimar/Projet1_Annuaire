package fr.isika.cda23.projet1.annuaire;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.ListeStagiaires;
import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;
import fr.isika.cda23.projet1.models.Utilisateur;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellEditEvent;

public class TableauStagiaire extends GridPane {
	

	@SuppressWarnings("unchecked")
	public TableauStagiaire() {
		super();
		

		TableView<Stagiaire> tableStagiaire = new TableView<Stagiaire>();
		tableStagiaire.setEditable(true);

		ChoiceBox<String> ChoiceBoxTri = new ChoiceBox<>();
		ChoiceBoxTri.getItems().addAll("Trier par:", "Nom", "Prenom", "Departement", "Promotion", "Annee");
		ChoiceBoxTri.getSelectionModel().select(0);
		ChoiceBoxTri.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (ChoiceBoxTri.getItems().size() == 6) {
					ChoiceBoxTri.getItems().remove(0);
				}
			}
		});

		HBox hb = new HBox(30);
		TextField txtRecherche = new TextField();
		txtRecherche.setMinHeight(30);

		Button btnRechercher = new Button("RECHERCHER");
		btnRechercher.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String value = txtRecherche.getText();
				String filtre = ChoiceBoxTri.getValue();
				if (!value.equals("") && !filtre.equals("Trier par:")) {

					tableStagiaire.setItems(FXCollections
							.observableArrayList(ListeStagiaires.listFilterInitialisation(filtre, value).getListe()));

				}

			}
		});

		Button btnAddStagiaire = new Button("AJOUTER STAGIAIRE");
		hb.getChildren().addAll(txtRecherche, ChoiceBoxTri, btnRechercher, btnAddStagiaire);
		btnAddStagiaire.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				EditScreen editScreen = new EditScreen();
				// Scene scene = new Scene(editScreen);
				BorderPane root = (BorderPane) btnAddStagiaire.getScene().getRoot();
				root.setCenter(editScreen);
				editScreen.setAlignment(Pos.CENTER);
			}
		});

		HBox hbox = new HBox();
		hbox.getChildren().add(hb);
		hbox.setAlignment(Pos.CENTER);

		this.add(hbox, 0, 0);

		// Creation des colonnes

		TableColumn<Stagiaire, String> nomStagiaire = new TableColumn<Stagiaire, String>("Nom");

		// Spécifier comment remplir la donnée pour chaque cellule de cette colonne
		// Ceci se fait en specifiant un "cell value factory" pour cette colonne.
		nomStagiaire.setMinWidth(100);
		nomStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		nomStagiaire.setStyle("-fx-alignment: center");
		nomStagiaire.setPrefWidth(150);

		TableColumn<Stagiaire, String> prenomStagiaire = new TableColumn<Stagiaire, String>("Prénom");
		prenomStagiaire.setMinWidth(100);
		prenomStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		prenomStagiaire.setStyle("-fx-alignment: center");
		prenomStagiaire.setPrefWidth(150);

		TableColumn<Stagiaire, String> dptStagiaire = new TableColumn<Stagiaire, String>("Département");
		dptStagiaire.setMinWidth(85);
		dptStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		dptStagiaire.setStyle("-fx-alignment: center");

		TableColumn<Stagiaire, String> promoStagiaire = new TableColumn<Stagiaire, String>("Promotion");
		promoStagiaire.setMinWidth(100);
		promoStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
		promoStagiaire.setStyle("-fx-alignment: center");

		TableColumn<Stagiaire, String> anneeStagiaire = new TableColumn<Stagiaire, String>("Année");
		anneeStagiaire.setMinWidth(85);
		anneeStagiaire.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));
		anneeStagiaire.setStyle("-fx-alignment: center");

		final TableColumn<Stagiaire, Void> deleteCol = new TableColumn<>("Supprimer");
		deleteCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Stagiaire, Void>, ObservableValue<Void>>() {

					@Override
					public ObservableValue<Void> call(TableColumn.CellDataFeatures<Stagiaire, Void> param) {
						return null;
					}
				});
		deleteCol.setStyle("-fx-alignment: center");
		deleteCol.setCellFactory(new Callback<TableColumn<Stagiaire, Void>, TableCell<Stagiaire, Void>>() {

			@Override
			public TableCell<Stagiaire, Void> call(TableColumn<Stagiaire, Void> param) {

				return new TableCell<Stagiaire, Void>() {
					
					
					private Button buttonDelete = new Button("Supprimer");

					{
						buttonDelete.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								final Stagiaire stagiaire = (Stagiaire) getTableRow().getItem();
								Noeud noeudASupprimer = new Noeud(stagiaire);
								FichierBinaire.lireNoeud(0).rechercheSupprimer(noeudASupprimer, 0, 0);
								tableStagiaire.setItems(FXCollections
										.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
							}

						});
					}

					@Override
					protected void updateItem(Void value, boolean empty) {
						super.updateItem(value, empty);
						final String text = null;
						setText(null);
						final Node graphic = (empty) ? null : buttonDelete;
						setGraphic(graphic);
					}
				};
			}

		});

		if (Utilisateur.isAdmin) {
			nomStagiaire.setCellFactory(TextFieldTableCell.<Stagiaire>forTableColumn());
			nomStagiaire.setOnEditCommit((CellEditEvent<Stagiaire, String> event) -> {
				TablePosition<Stagiaire, String> pos = event.getTablePosition();
				String oldNom = event.getOldValue();
				String newNom = event.getNewValue();
				int row = pos.getRow();
				Stagiaire stagiaire = event.getTableView().getItems().get(row);
				stagiaire.setNom(newNom.toUpperCase());
				Noeud noeudModifie = new Noeud(stagiaire);
				Noeud ancienNoeud = new Noeud(new Stagiaire(oldNom.toUpperCase(), stagiaire.getPrenom(),
						stagiaire.getDepartement(), stagiaire.getPromotion(), stagiaire.getAnnee()));
				noeudModifie.modifier(noeudModifie, ancienNoeud);
				tableStagiaire
						.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));

			});
			prenomStagiaire.setCellFactory(TextFieldTableCell.<Stagiaire>forTableColumn());
			prenomStagiaire.setOnEditCommit((CellEditEvent<Stagiaire, String> event) -> {
				TablePosition<Stagiaire, String> pos = event.getTablePosition();
				String oldPrenom = event.getOldValue();
				String newPrenom = event.getNewValue();
				int row = pos.getRow();
				Stagiaire stagiaire = event.getTableView().getItems().get(row);
				stagiaire.setPrenom(newPrenom);
				Noeud noeudModifie = new Noeud(stagiaire);
				Noeud ancienNoeud = new Noeud(new Stagiaire(stagiaire.getNom(), oldPrenom, stagiaire.getDepartement(),
						stagiaire.getPromotion(), stagiaire.getAnnee()));
				noeudModifie.modifier(noeudModifie, ancienNoeud);
				tableStagiaire
						.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));

			});
			dptStagiaire.setCellFactory(TextFieldTableCell.<Stagiaire>forTableColumn());
			dptStagiaire.setOnEditCommit((CellEditEvent<Stagiaire, String> event) -> {
				TablePosition<Stagiaire, String> pos = event.getTablePosition();
				String oldDepartement = event.getOldValue();
				String newDepartement = event.getNewValue();
				int row = pos.getRow();
				Stagiaire stagiaire = event.getTableView().getItems().get(row);
				stagiaire.setDepartement(newDepartement);
				Noeud noeudModifie = new Noeud(stagiaire);
				Noeud ancienNoeud = new Noeud(new Stagiaire(stagiaire.getNom(), stagiaire.getPrenom(), oldDepartement,
						stagiaire.getPromotion(), stagiaire.getAnnee()));
				noeudModifie.modifier(noeudModifie, ancienNoeud);
				tableStagiaire
						.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
			});
			promoStagiaire.setCellFactory(TextFieldTableCell.<Stagiaire>forTableColumn());
			promoStagiaire.setOnEditCommit((CellEditEvent<Stagiaire, String> event) -> {
				TablePosition<Stagiaire, String> pos = event.getTablePosition();
				String oldPromo = event.getOldValue();
				String newPromo = event.getNewValue();
				int row = pos.getRow();
				Stagiaire stagiaire = event.getTableView().getItems().get(row);
				stagiaire.setPromotion(newPromo);
				Noeud noeudModifie = new Noeud(stagiaire);
				Noeud ancienNoeud = new Noeud(new Stagiaire(stagiaire.getNom(), stagiaire.getPrenom(),
						stagiaire.getDepartement(), oldPromo, stagiaire.getAnnee()));
				noeudModifie.modifier(noeudModifie, ancienNoeud);
				tableStagiaire
						.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
			});
			anneeStagiaire.setCellFactory(TextFieldTableCell.<Stagiaire>forTableColumn());
			anneeStagiaire.setOnEditCommit((CellEditEvent<Stagiaire, String> event) -> {
				TablePosition<Stagiaire, String> pos = event.getTablePosition();
				String oldAnnee = event.getOldValue();
				String newAnnee = event.getNewValue();
				int row = pos.getRow();
				Stagiaire stagiaire = event.getTableView().getItems().get(row);
				stagiaire.setAnnee(newAnnee);
				Noeud noeudModifie = new Noeud(stagiaire);
				Noeud ancienNoeud = new Noeud(new Stagiaire(stagiaire.getNom(), stagiaire.getPrenom(),
						stagiaire.getDepartement(), stagiaire.getPromotion(), oldAnnee));
				noeudModifie.modifier(noeudModifie, ancienNoeud);
				tableStagiaire
						.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
			});

		}

		tableStagiaire.getColumns().addAll(nomStagiaire, prenomStagiaire, dptStagiaire, promoStagiaire, anneeStagiaire);
		if (Utilisateur.isAdmin) {
			tableStagiaire.getColumns().add(deleteCol);
		}
		// On rempli la table avec la liste observable
		tableStagiaire.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
		this.add(tableStagiaire, 0, 1);
		this.setVgap(20);

	}

}
