package parts;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

/**
 * Class FXMenu
 * erstellt ein Menü, über welches das Programm beendet werden kann
 * und die gewünschten Einheiten selektiert werden können.
 */
public class FXMenu extends HBox {

    Menu optionMenu;
    ToggleGroup groupDist;
    RadioMenuItem kmItem;
    RadioMenuItem miItem;
    ToggleGroup groupVol;
    RadioMenuItem usGalItem;
    RadioMenuItem lItem;
    ToggleGroup groupCur;
    RadioMenuItem euroItem;
    RadioMenuItem dollarItem;

    public FXMenu(Window parentWindow) {
        MenuBar menuBar = new MenuBar();

        // Datei-Menü
        Menu fileMenu = new Menu("Datei");
        MenuItem closeMenuItem = new MenuItem("Beenden");
        closeMenuItem.setOnAction(e -> {
            // Nur das Fenster schließen, nicht die JVM beenden
            parentWindow.hide();
        });
        fileMenu.getItems().addAll(closeMenuItem);

        // Optionen-Menü
        optionMenu = new Menu("Optionen");

        // Distanz
        groupDist = new ToggleGroup();
        kmItem = new RadioMenuItem("km");
        miItem = new RadioMenuItem("mi");

        kmItem.setToggleGroup(groupDist);
        miItem.setToggleGroup(groupDist);
        kmItem.setSelected(true);

        // Volumen
        groupVol = new ToggleGroup();
        usGalItem = new RadioMenuItem("US.liq.gal.");
        lItem = new RadioMenuItem("l");
        usGalItem.setToggleGroup(groupVol);
        lItem.setToggleGroup(groupVol);
        lItem.setSelected(true);

        // Währung
        groupCur = new ToggleGroup();
        euroItem = new RadioMenuItem("€");
        dollarItem = new RadioMenuItem("$");
        euroItem.setToggleGroup(groupCur);
        dollarItem.setToggleGroup(groupCur);
        euroItem.setSelected(true);

        // Optionen zusammenbauen
        optionMenu.getItems().addAll(
                kmItem, miItem,
                new SeparatorMenuItem(),
                usGalItem, lItem,
                new SeparatorMenuItem(),
                euroItem, dollarItem
        );

        // Menüleiste bestücken
        menuBar.getMenus().addAll(fileMenu, optionMenu);
        getChildren().add(menuBar);
        HBox.setHgrow(menuBar, Priority.ALWAYS);
    }

    public Entry.unitOfMeasure getSelectedDistanceUnit() {
        return kmItem.isSelected() ? Entry.unitOfMeasure.Kilometer : Entry.unitOfMeasure.Mile;
    }
    public Entry.unitOfFuel getSelectedVolumeUnit() {
        return lItem.isSelected() ? Entry.unitOfFuel.Liter : Entry.unitOfFuel.US_Gallon;
    }
    public Entry.unitOfMoney getSelectedCurrencyUnit() {
        return euroItem.isSelected() ? Entry.unitOfMoney.Euro : Entry.unitOfMoney.USD;
    }
}