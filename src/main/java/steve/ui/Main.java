package steve.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main JavaFX application class.
 * This class sets up and displays a simple JavaFX window with a "Hello World" label.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World"); // Creating a new Label Control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our scene
        stage.show(); // Render the stage
    }
}
