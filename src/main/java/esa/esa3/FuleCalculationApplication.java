package esa.esa3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Class FuleCalculationApplication
 *
 * Bietet die grundlegenden Funktionen in einer GUI eine Art Fahrtenbuch zu führen,
 * durch eingabe von Datum, Kilometerstand, getankter Menge und Preis
 * lässen sich Durchschnittswerte in den Einheiten €|$ Km|Mi und L|Gl. anzeigen.
 *
 * @author Keno Buß, Florian Fay, Arnich Raphael Scherbring
 * @version 1.0
 * @since 2025-05-20
 */
public class FuleCalculationApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        new MainView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}