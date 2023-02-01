package fr.isika.cda23.projet1.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.beans.value.ObservableValue;


public class AdminLoginScreen extends GridPane{

	public AdminLoginScreen() {
		
		super();
		try {

			this.setPadding(new Insets(20));
			this.setHgap(25);
			this.setVgap(15);

			// Scene scene = new Scene(root, 650, 200);
			// scene.getStylesheets().add(getClass().getResource("appllication.css").toExternalForm());

			// LAbel Logiin
			Label login = new Label("Login");
			this.add(login, 0, 0);

			// TExtField Login
			TextField logintf = new TextField();
			this.add(logintf, 1, 0);

			// LAbel Password
			Label password = new Label("Mot de passe");
			this.add(password, 0, 1);

			// PAssword field
			PasswordField passwordField = new PasswordField();
			this.add(passwordField, 1, 1);

			// POur afficher le PAssword
			TextField pass_text = new TextField();
			pass_text.setVisible(false);
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
			Label label = new Label("");
			this.add(label, 0, 3);
			GridPane.setColumnSpan(label, 2);

			// BUtton utilisateur
			Button utilisateur = new Button("Continuer en tant qu'utilisateur");
			this.add(utilisateur, 1, 2);

			utilisateur.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

			

				}
			});

			// BUtton Login pour Administrateur
			Button valider = new Button("VALIDER");
			this.add(valider, 2, 2);

			valider.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if ((logintf.getText() != null && !passwordField.getText().isEmpty())) {

						if (!passwordField.getText().equals("ADMIN")) {
							label.setText("Mot de passe erroné");
							label.setTextFill(Color.RED);
						} else {
							label.setText("je passe à la page suivante");
							label.setTextFill(Color.BLACK);
						}

					} else {
						label.setText("Enter Login et Mot de passe");
					}

				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
