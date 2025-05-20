package parts;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class FXTable extends VBox {

    private final TableView<Entry> table;

    public FXTable() {
        table = new TableView<>();

        // Spalten erstellen
        TableColumn<Entry, LocalDate> date = new TableColumn<>("Datum");
        TableColumn<Entry, Long> odometer = new TableColumn<>("Tachostand");
        TableColumn<Entry, Double> volume = new TableColumn<>("Menge");
        TableColumn<Entry, Entry.unitOfFuel> unitOfFuel = new TableColumn<>("Treibstoff Einheit");
        TableColumn<Entry, Double> cost = new TableColumn<>("Peis");
        TableColumn<Entry, Entry.unitOfMoney> unitOfMoney = new TableColumn<>("Preiseinheit");
        TableColumn<Entry, Entry.unitOfMeasure> unitOfMeasure = new TableColumn<>("Streckeneinheit");

        date.setPrefWidth(90);
        odometer.setPrefWidth(100);
        volume.setPrefWidth(90);
        unitOfFuel.setPrefWidth(130);
        cost.setPrefWidth(80);
        unitOfMoney.setPrefWidth(85);
        unitOfMeasure.setPrefWidth(100);

        // Datenbindung zu den Attributen der Entry-Klasse
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        odometer.setCellValueFactory(new PropertyValueFactory<>("distance"));
        volume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        unitOfFuel.setCellValueFactory(new PropertyValueFactory<>("unitOfFuel"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        unitOfMoney.setCellValueFactory(new PropertyValueFactory<>("unitOfMoney"));
        unitOfMeasure.setCellValueFactory(new PropertyValueFactory<>("unitOfMeasure"));

        // Nur einmal hinzufügen!
        table.getColumns().addAll(date, odometer, unitOfMeasure, volume, unitOfFuel, cost, unitOfMoney);

        // Layout-Einstellungen
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        getChildren().add(table);
        setPadding(new Insets(10));
        VBox.setVgrow(table, Priority.ALWAYS);
    }

    public void addEntry(Entry entry) {
        table.getItems().add(entry);
    }
}
