package fr.isika.cda23.projet1.annuaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        

        var label = new Label("Hello World");
        var scene = new Scene(new StackPane(label), 640, 480);
        scene.getRoot().setStyle("-fx-font-family: 'Helvetica'");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}