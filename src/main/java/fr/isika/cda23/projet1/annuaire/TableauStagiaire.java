package fr.isika.cda23.projet1.annuaire;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.ListeStagiaires;
import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;
import fr.isika.cda23.projet1.models.Utilisateur;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellEditEvent;

public class TableauStagiaire extends GridPane {

	@SuppressWarnings("unchecked")
	public TableauStagiaire() {
		super();
		TableView<Stagiaire> tableStagiaire = new TableView<Stagiaire>();
		tableStagiaire.setEditable(true);

		ChoiceBox<String> ChoiceBoxTri = new ChoiceBox<>();
		ChoiceBoxTri.getItems().addAll("Trier par:", "Nom", "Prénom", "Département", "Promotion", "Année");
		ChoiceBoxTri.getSelectionModel().select(0);
		ChoiceBoxTri.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (ChoiceBoxTri.getItems().size() == 6) {
					ChoiceBoxTri.getItems().remove(0);
				}
			}
		});
		ChoiceBoxTri.setPrefWidth(130);
		
		HBox hboxBoutons = new HBox(30);
		TextField txtRecherche = new TextField();

		txtRecherche.setPromptText("Recherche...");
		txtRecherche.setMinHeight(40);
		txtRecherche.setFont(Font.font("Avenir", 16));
		hboxBoutons.setFillHeight(true);
		hboxBoutons.setAlignment(Pos.CENTER_RIGHT);

		HBox errorBox = new HBox();
		Label errorLabel = new Label("");
		errorLabel.getStyleClass().add("incorrect-label");
		errorLabel.setVisible(false);
		errorBox.getChildren().add(errorLabel);
		errorBox.setPrefWidth(400);

		Button btnRechercher = new Button("Rechercher");
		btnRechercher.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String value = txtRecherche.getText();
				String filtre = ChoiceBoxTri.getValue();
				if (!value.equals("") && !filtre.equals("Trier par:")) {
					errorLabel.setVisible(false);
					errorLabel.setText("");
					tableStagiaire.setItems(FXCollections
							.observableArrayList(ListeStagiaires.listFilterInitialisation(filtre, value).getListe()));

				} else {
					errorLabel.setVisible(true);
					String errorMessageString = "";
					if (value.equals("")) {
						errorMessageString += "- Renseignez votre recherche\n";
						tableStagiaire.setItems(
								FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
					}
					if (filtre.equals("Trier par:")) {
						errorMessageString += "- Renseignez un filtre de recherche";
					}
					errorLabel.setText(errorMessageString);
				}
				txtRecherche.setText("");
			}
		});

		Button btnAddStagiaire = new Button("Ajouter stagiaire");
		HBox hboxRightButton = new HBox(20);
		hboxRightButton.getChildren().addAll(btnRechercher, btnAddStagiaire);
		hboxRightButton.setAlignment(Pos.CENTER_RIGHT);

		hboxBoutons.getChildren().addAll(ChoiceBoxTri, txtRecherche, hboxRightButton);
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
		HBox hboxBoutonsSecondRow = new HBox(20);
		hboxBoutonsSecondRow.setPrefWidth(200);
		

		Button btnPrint = new Button("Imprimer");
		btnPrint.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Document document = new Document();

				try {
					File pdf = new File("src/main/java/assets/aperçu.pdf");
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
					document.open();
//					
					PdfPTable pdfTable = new PdfPTable(5); // 5 colonnes pour le nom, le prénom, le département, la
															// promotion et l'année
					pdfTable.setWidthPercentage(100);
					pdfTable.setSpacingBefore(10f);
					pdfTable.setSpacingAfter(10f);

					// En-têtes de colonnes
					PdfPCell nomHeader = new PdfPCell(new Phrase("Nom"));
					nomHeader.setBorderColor(BaseColor.BLACK);
					nomHeader.setPaddingLeft(10);
					nomHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
					nomHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
					nomHeader.setBackgroundColor(BaseColor.GRAY);
					pdfTable.addCell(nomHeader);

					PdfPCell prenomHeader = new PdfPCell(new Phrase("Prénom"));
					prenomHeader.setBorderColor(BaseColor.BLACK);
					prenomHeader.setPaddingLeft(10);
					prenomHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
					prenomHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
					prenomHeader.setBackgroundColor(BaseColor.GRAY);
					pdfTable.addCell(prenomHeader);

					PdfPCell dptHeader = new PdfPCell(new Phrase("Département"));
					dptHeader.setBorderColor(BaseColor.BLACK);
					dptHeader.setPaddingLeft(10);
					dptHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
					dptHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
					dptHeader.setBackgroundColor(BaseColor.GRAY);
					pdfTable.addCell(dptHeader);

					PdfPCell promoHeader = new PdfPCell(new Phrase("Promotion"));
					promoHeader.setBorderColor(BaseColor.BLACK);
					promoHeader.setPaddingLeft(10);
					promoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
					promoHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
					promoHeader.setBackgroundColor(BaseColor.GRAY);
					pdfTable.addCell(promoHeader);

					PdfPCell anneeHeader = new PdfPCell(new Phrase("Année"));
					anneeHeader.setBorderColor(BaseColor.BLACK);
					anneeHeader.setPaddingLeft(10);
					anneeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
					anneeHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
					anneeHeader.setBackgroundColor(BaseColor.GRAY);
					pdfTable.addCell(anneeHeader);

					// ajouter les données de chaque stagiaire
					for (Stagiaire stagiaire : tableStagiaire.getItems()) {
						pdfTable.addCell(new PdfPCell(new Paragraph(stagiaire.getNom())));
						pdfTable.addCell(new PdfPCell(new Paragraph(stagiaire.getPrenom())));
						pdfTable.addCell(new PdfPCell(new Paragraph(stagiaire.getDepartement())));
						pdfTable.addCell(new PdfPCell(new Paragraph(stagiaire.getPromotion())));
						pdfTable.addCell(new PdfPCell(new Paragraph(stagiaire.getAnnee())));

					}

					document.add(pdfTable);
					document.close();
					writer.close();

					Desktop.getDesktop().open(pdf);

				} catch (IOException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		Button btnRefresh = new Button("Rafraîchir");
		btnRefresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableStagiaire
						.setItems(FXCollections.observableArrayList(ListeStagiaires.listInitialisation().getListe()));
			}
		});
		
		hboxBoutonsSecondRow.getChildren().addAll(errorBox,btnPrint,btnRefresh);

		this.add(hboxBoutons, 0, 0);
		this.add(hboxBoutonsSecondRow, 0, 1);
		TableauStagiaire.setHalignment(hboxBoutonsSecondRow, HPos.RIGHT);
		TableauStagiaire.setHalignment(hboxRightButton, HPos.RIGHT);
		hboxRightButton.setMaxWidth(300);

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
		dptStagiaire.setMinWidth(110);
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
						buttonDelete.getStyleClass().add("deleteButton");
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
		this.add(tableStagiaire, 0, 2);
		this.setVgap(10);

	}

}
