package esa.esa3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FuleCalculationApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        new MainView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}