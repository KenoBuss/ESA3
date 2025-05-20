package parts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import parts.events.LastFuelingEvent;
import parts.events.TotalAverageEvent;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class FXAveragePanel extends VBox {

    private final TextArea textArea;

    public FXAveragePanel() {

        Button btnLastFueling;
        Button btnTotalFueling;
        textArea = new TextArea();
        textArea.setEditable(false);          // Nur Anzeige, kein Editieren durch Nutzer
        textArea.setFocusTraversable(false);  // Springt nicht beim Tabben an
        textArea.setWrapText(true);           // Zeilenumbruch
        textArea.setPrefRowCount(14);         // Optional: Höhe
        textArea.setPrefColumnCount(12);

        ButtonBar btnBar = new ButtonBar();
        btnLastFueling = new Button("Seit letzter Befüllung");
        btnLastFueling.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnLastFueling.fireEvent(new LastFuelingEvent(LastFuelingEvent.LASTFUELING_CLICKED));
            }
        });
        btnTotalFueling = new Button("Insgesamt");
        btnTotalFueling.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnTotalFueling.fireEvent(new TotalAverageEvent(TotalAverageEvent.TOTALAVERAGE_CLICKED));
            }
        });




        btnBar.getButtons().addAll(btnLastFueling, btnTotalFueling);

        getChildren().addAll(textArea, btnBar);
        setMargin(btnBar, new Insets(10,20,0,0));
        setMargin(textArea, new Insets(0,20,0,0));
    }

    public void setCustomText(String text) {
        textArea.setText(text);
    }

    public void setTextForTotalAverage(List<Entry> entries,
                                       Entry.unitOfFuel unitOfFuel,
                                       Entry.unitOfMoney unitOfMoney,
                                       Entry.unitOfMeasure unitOfMeasure) {

        long distance = 0;
        double fuel = 0.0;
        double money = 0.0;
        StringBuilder sb = new StringBuilder();

        if (unitOfMeasure == Entry.unitOfMeasure.Kilometer) {
            for (int i = 1; i < entries.size(); i++) {
                distance += (long)(Math.round(entries.get(i).getDistanceInKilometers()) - Math.round(entries.get(i - 1).getDistanceInKilometers()));
            }
        }
        else if(unitOfMeasure == Entry.unitOfMeasure.Mile){
            for (int i = 1; i < entries.size(); i++) {
                distance += (long)(Math.round(entries.get(i).getDistanceInMiles()) - Math.round(entries.get(i - 1).getDistanceInMiles()));
            }
        }

        if(unitOfFuel == Entry.unitOfFuel.Liter){
            fuel = entries.stream()
                    .mapToDouble(Entry::getVolumeInLiter)
                    .sum();
        }
        else if(unitOfFuel == Entry.unitOfFuel.US_Gallon){
            fuel = entries.stream()
                    .mapToDouble(Entry::getVolumeInGallons)
                    .sum();
        }

        if(unitOfMoney == Entry.unitOfMoney.Euro){
           money = entries.stream()
                    .mapToDouble(Entry::getCostInEuros)
                    .sum();
        }
        else if(unitOfMoney == Entry.unitOfMoney.USD){
            money = entries.stream()
                    .mapToDouble(Entry::getCostInDollars)
                    .sum();
        }

        if (distance <= 0) {
            sb.append("Die gefahrene Strecke konnte nicht ermittelt werden (prüfe Einträge).");
            textArea.setText(sb.toString());
            return;
        }

        int timesFuelled = entries.size();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        sb.append("Sie sind im Zeitraum vom: ")
                .append(entries.getFirst().getDate().format(formatter))
                .append(" bis zum ")
                .append(entries.getLast().getDate().format(formatter))
                .append(" insgesamt ").append(distance).append(" ").append(unitOfMeasure)
                .append(String.format(" gefahren und haben dabei: %d. getankt. \n",timesFuelled))
                .append("insgesamt ergibt sich daraus:\n")
                .append(String.format("->%.2f %s betankt\n", fuel, unitOfFuel.toString()))
                .append(String.format("->%.2f %s bezahlt\n",money, unitOfMoney.toString()))
                .append("Das ist ein Verbrauch von: \n")
                .append(String.format("%.2f", fuel * 100 / distance))
                .append(String.format("%s/100%s \n", unitOfFuel.toString(), unitOfMeasure.toString()))
                .append("bei einem durchschnittspreis von: \n")
                .append(String.format("%.2f %s/%s", money / fuel, unitOfMoney.toString(), unitOfFuel.toString()));
        textArea.setText(sb.toString());
    }

    public void setTextForLastFueling(Entry newerEntry,
                                      Entry olderEntry,
                                      Entry.unitOfFuel unitOfFuel,
                                      Entry.unitOfMoney unitOfMoney,
                                      Entry.unitOfMeasure unitOfMeasure) {

        double fuel = 0.0;
        double money = 0.0;
        double distance = 0.0;
        StringBuilder sb = new StringBuilder();

        if(unitOfFuel == Entry.unitOfFuel.Liter){
            fuel = newerEntry.getVolumeInLiter();
        }
        else if (unitOfFuel == Entry.unitOfFuel.US_Gallon){
            fuel = newerEntry.getVolumeInGallons();
        }

        if(unitOfMoney == Entry.unitOfMoney.Euro){
            money = newerEntry.getCostInEuros();
        }
        else if(unitOfMoney == Entry.unitOfMoney.USD){
            money = newerEntry.getCostInDollars();
        }

        if(unitOfMeasure == Entry.unitOfMeasure.Kilometer){
            distance = Math.round(newerEntry.getDistanceInKilometers()) - Math.round(olderEntry.getDistanceInKilometers());
        }
        else if(unitOfMeasure == Entry.unitOfMeasure.Mile){
            distance = Math.round(newerEntry.getDistanceInMiles()) - Math.round(olderEntry.getDistanceInMiles());
        }

        if (distance <= 0) {
            sb.append("Gefahrene Strecke konnte nicht ermittelt werden.");
        } else {
            double consumption = (fuel * 100.0) / distance;
            double pricePerLitre = (fuel > 0) ? (money / fuel) : 0;

            sb.append(String.format("Seit der letzten Tankfüllung: ( %s ) wurden: \n", olderEntry.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .append(String.format("%d Kilometer gefahren.\n", (long)distance))
                .append(String.format("%.2f Liter getankt.\n", fuel))
                .append(String.format("und %.2f € gezahlt\n\n", money))
                .append("Daraus ergibt sich ein Durchschnitt von: \n")
                .append(String.format("%.2f %s/100%s\n", consumption, unitOfFuel.toString(), unitOfMeasure.toString()))
                .append(String.format("bei einem Durchschnittspreis von %.2f %s/%s", pricePerLitre, unitOfMoney.toString(), unitOfFuel.toString()));
        }

        textArea.setText(sb.toString());
    }

}
