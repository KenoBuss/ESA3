package esa.esa3;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parts.*;
import parts.events.EntryEvent;
import parts.events.LastFuelingEvent;
import parts.events.TotalAverageEvent;

import java.util.ArrayList;
import java.util.List;

public class MainView {
    private final Stage stage;
    private BorderPane root;
    private Scene scene;
    private final List<Entry> entryList = new ArrayList<Entry>();

    private FXMenu fxMenue;
    private FXInputForm fxInputForm;
    private FXTable fxTable;
    private FXAveragePanel fxAveragePanel;
    private VBox rightBox;

    public MainView(Stage stage) {
        this.stage = stage;
        stage.sizeToScene();
        stage.setResizable(false);
        buildUI();
    }

    private void buildUI() {
        root = new BorderPane();
        root.addEventFilter(EntryEvent.ANY, this::handelEntryEvent);
        root.addEventFilter(TotalAverageEvent.ANY, this::handelTotalAverageEvent);
        root.addEventFilter(LastFuelingEvent.ANY, this::handelLastFuelingEvent);

        createMenuBar();
        createRightBox();
        createLeftTableContent();

        scene = new Scene(root);
        stage.setTitle("Average Fuel Calculation");
        stage.setScene(scene);
        stage.setMinWidth(550);
        stage.setMinHeight(500);
        stage.show();
    }

    private void createMenuBar() {
        fxMenue = new FXMenu(stage);
        root.setTop(fxMenue);
    }

    private void createRightBox() {
        rightBox = new VBox();
        fxInputForm = new FXInputForm();
        fxAveragePanel = new FXAveragePanel();

        VBox.setMargin(fxAveragePanel, new Insets(20, 20, 20, 10));
        rightBox.getChildren().addAll(fxInputForm, fxAveragePanel);
        fxAveragePanel.setCustomText("Noch sind keine Werte vorhanden.");
        root.setRight(rightBox);
    }

    private void createLeftTableContent(){
        fxTable = new FXTable();
        root.setLeft(fxTable);
    }

    public void handelEntryEvent(EntryEvent event) {
        Entry entry = event.getEntry();
        entry.setUnitOfFuel(fxMenue.getSelectedVolumeUnit());
        entry.setUnitOfMoney(fxMenue.getSelectedCurrencyUnit());
        entry.setUnitOfMeasure(fxMenue.getSelectedDistanceUnit());
        entry.calculateExchanges(entry.getUnitOfFuel(), entry.getUnitOfMeasure(), entry.getUnitOfMoney());
        entryList.add(entry);
        fxTable.addEntry(entry);
        if(entryList.size() >= 2)
            fxAveragePanel.setTextForLastFueling(entryList.getLast(), entryList.get(entryList.size()-2), fxMenue.getSelectedVolumeUnit(), fxMenue.getSelectedCurrencyUnit(), fxMenue.getSelectedDistanceUnit());
        else
            fxAveragePanel.setCustomText("Bei weniger als zwei Tankfüllungen,\nkann noch kein richtiger Durchschnitt gebildet werden.");
    }

    public void handelTotalAverageEvent(TotalAverageEvent event){
        if(entryList.size() >= 2)
            fxAveragePanel.setTextForTotalAverage(entryList, fxMenue.getSelectedVolumeUnit(), fxMenue.getSelectedCurrencyUnit(), fxMenue.getSelectedDistanceUnit());
        else
            fxAveragePanel.setCustomText("Bei weniger als zwei Tankfüllungen,\nkann noch kein richtiger Durchschnitt gebildet werden.");
    }

    public void handelLastFuelingEvent(LastFuelingEvent event) {
        if(entryList.size() >= 2)
            fxAveragePanel.setTextForLastFueling(entryList.getLast(), entryList.get(entryList.size()-2), fxMenue.getSelectedVolumeUnit(), fxMenue.getSelectedCurrencyUnit(), fxMenue.getSelectedDistanceUnit());
        else
            fxAveragePanel.setCustomText("Bei weniger als zwei Tankfüllungen,\nkann noch kein richtiger Durchschnitt gebildet werden.");
    }
}
