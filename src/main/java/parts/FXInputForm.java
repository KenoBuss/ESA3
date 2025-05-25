package parts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import parts.events.EntryEvent;

/**
 * Class FXInputForm
 * erstellt ein GridPane, in welchem mithilfe mehrerer
 * TextFields und Labels und einem Button die Werte eingegeben werden können.
 * Entsprechend der Aufgabe wurden noch keine Sicherheitsmaßnahmen herangezogen.
 */
public class FXInputForm extends VBox {

    private final GridPane grid;
    private final Button btnSave;
    private final Label lblDate;
    private final Label lblOdometerReader;
    private final Label lblRefueledQuantity;
    private final Label lblPrice;
    private final DatePicker datePicker;
    private final TextField txtOddmeterReader;
    private final TextField txtRefueledQuantity;
    private final TextField txtPrice;
    private final ButtonBar btnBar;


    public FXInputForm() {
        grid = new GridPane();

        lblDate = new Label("Datum");
        lblOdometerReader = new Label("Tachostand (gesamt)");
        lblRefueledQuantity = new Label("Tankmenge");
        lblPrice = new Label("Preis");

        datePicker = new DatePicker();
        txtOddmeterReader = new TextField();
        txtRefueledQuantity = new TextField();
        txtPrice = new TextField();

        btnSave = new Button("Speichern");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Entry entry = new Entry(datePicker.getValue(),
                        Long.parseLong(txtOddmeterReader.getText()),
                        Double.parseDouble(txtRefueledQuantity.getText().replace(",", ".")),
                        Double.parseDouble(txtPrice.getText().replace(",", ".")));
                btnSave.fireEvent(new EntryEvent(EntryEvent.ENTRY_SAVE, entry));
            }
        });

        btnBar = new ButtonBar();
        btnBar.getButtons().addAll(btnSave);


        grid.add(lblDate, 0, 0, 1, 1);
        grid.add(datePicker, 1, 0, 1, 1);

        grid.add(lblOdometerReader, 0, 1, 1, 1);
        grid.add(txtOddmeterReader, 1, 1, 1, 1);

        grid.add(lblRefueledQuantity, 0, 2, 1, 1);
        grid.add(txtRefueledQuantity, 1, 2, 1, 1);

        grid.add(lblPrice, 0, 3, 1, 1);
        grid.add(txtPrice, 1, 3, 1, 1);

        grid.add(btnBar, 0, 4, 2, 1);

        grid.setHgap(10);
        grid.setVgap(5);

        getChildren().add(grid);
        setPadding(new Insets(50, 20, 20, 15));
        VBox.setVgrow(grid, Priority.ALWAYS);
    }
}