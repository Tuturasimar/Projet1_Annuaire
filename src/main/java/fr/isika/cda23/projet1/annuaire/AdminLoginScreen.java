
package fr.isika.cda23.projet1.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import fr.isika.cda23.projet1.models.Utilisateur;
import javafx.beans.value.ObservableValue;

public class AdminLoginScreen extends GridPane {

	public AdminLoginScreen() {

		super();
		try {

			this.setPadding(new Insets(20));
			this.setHgap(25);
			this.setVgap(15);

			// Label Login
			Label login = new Label("Login");
			this.add(login, 0, 0);

			// TextField Login
			TextField logintf = new TextField();
			logintf.setFont(Font.font("Avenir",16));

			this.add(logintf, 1, 0);

			// Label Password
			Label password = new Label("Mot de passe");
			this.add(password, 0, 1);

			// PAssword field
			PasswordField passwordField = new PasswordField();
			passwordField.setFont(Font.font("Avenir",16));

			this.add(passwordField, 1, 1);

			// POur afficher le PAssword
			TextField pass_text = new TextField();
			pass_text.setVisible(false);
			pass_text.setFont(Font.font("Avenir",16));

			this.add(pass_text, 1, 1);

			// checkBOx "afficher"
			CheckBox pass_Toggle = new CheckBox("Afficher mot de passe");
			this.add(pass_Toggle, 2, 1);
			// Event
			pass_Toggle.selectedProperty()
					.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
						if (pass_Toggle.isSelected()) {
							pass_text.setText(passwordField.getText());
							pass_text.setVisible(true);
							passwordField.setVisible(false);
							return;

						} else {
							passwordField.setText(pass_text.getText());
							passwordField.setVisible(true);
							pass_text.setVisible(false);
							return;
						}
					});
			// LAbel pour afficher le message
			Label errorLabel = new Label("");
			errorLabel.getStyleClass().add("incorrect-label");
			this.add(errorLabel, 0, 3);
			GridPane.setColumnSpan(errorLabel, 2);

			// Text utilisateur
			Label txtUtilisateur = new Label("Continuer en tant qu'utilisateur");
			txtUtilisateur.getStyleClass().add("txtUtilisateur");

			this.add(txtUtilisateur, 1, 2);

			txtUtilisateur.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent me) {
					TableauStagiaire tableauStagiaire = new TableauStagiaire();
					BorderPane root = (BorderPane) txtUtilisateur.getScene().getRoot();
					root.setCenter(tableauStagiaire);
					tableauStagiaire.setAlignment(Pos.CENTER);
				}
			});

			Label testLabel = new Label();

			this.add(testLabel, 0, 4);

			// BUtton Login pour Administrateur
			Button valider = new Button("VALIDER");
			this.add(valider, 2, 2);

			valider.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					testLabel.setText(logintf.getText());
					String enteredPassword = passwordField.getText();
					if (!logintf.getText().isEmpty()
							&& (!passwordField.getText().isEmpty() || !pass_text.getText().isEmpty())) {
						Utilisateur.checkPassword(enteredPassword);
						Utilisateur.checkPassword(pass_text.getText());
						if (Utilisateur.isAdmin) {
							TableauStagiaire tableauStagiaire = new TableauStagiaire();
							BorderPane root = (BorderPane) valider.getScene().getRoot();
							root.setCenter(tableauStagiaire);
							tableauStagiaire.setAlignment(Pos.CENTER);
						} else {
							errorLabel.setText("Mot de passe incorrect");
						}
					} else {
						errorLabel.setText("Renseignez les champs");
					}

				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
