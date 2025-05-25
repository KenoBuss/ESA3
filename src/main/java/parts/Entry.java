package parts;

import java.time.LocalDate;

/**
 * Class Entry
 *
 * Stellt eine Klasse eines Basiseintrags dar.
 * Stellt Funktionen zur Umrechnung von Werten in verschiedene Einheiten bereit.
 */
public class Entry {
    public enum unitOfFuel {
        Liter,
        US_Gallon
    };
    public enum unitOfMeasure {
        Kilometer,
        Mile
    }
    public enum unitOfMoney {
        Euro,
        USD
    }
    private LocalDate date;
    private long distance;
    private double distanceInMiles;
    private double distanceInKilometers;
    private double volume;
    private double volumeInLiter;
    private double volumeInGallons;
    private double cost;
    private double costInEuros;
    private double costInDollars;
    private unitOfMoney unitOfMoney;
    private unitOfFuel unitOfFuel;
    private unitOfMeasure unitOfMeasure;
    private final double EXCHANGE_RATE_FOR_EURO_TO_DOLLAR = 1.08; // Beispielkurs
    private final double EXCHANGE_RATE_FOR_KILOMETER_TO_MILE = 1.609344;
    private final double EXCHANGE_RATE_FOR_LITER_TO_US_GALLON = 3.79;


    Entry(LocalDate date, long odometer, double quantity, double cost) {
        setDate(date);
        setDistance(odometer);
        setVolume(quantity);
        setCost(cost);
    }

    public void calculateExchanges(unitOfFuel unitOfFuel, unitOfMeasure unitOfMeasure, unitOfMoney unitOfMoney) {
        // DISTANZ
        if (unitOfMeasure == unitOfMeasure.Kilometer) {
            this.distanceInKilometers = this.distance;
            setDistanceInMiles(this.distance);
        } else if (unitOfMeasure == unitOfMeasure.Mile) {
            this.distanceInMiles = this.distance;
            setDistanceInKilometers(this.distance);
        }

        // VOLUME
        if (unitOfFuel == unitOfFuel.Liter) {
            this.volumeInLiter = this.volume;
            setVolumeInGallons(this.volume);
        } else if (unitOfFuel == unitOfFuel.US_Gallon) {
            this.volumeInGallons = this.volume;
            setVolumeInLiter(this.volume);
        }

        // COST
        if (unitOfMoney == unitOfMoney.Euro) {
            this.costInEuros = this.cost;
            setCostInDollars(this.cost);
        } else if (unitOfMoney == unitOfMoney.USD) {
            this.costInDollars = this.cost;
            setCostInEuros(this.cost);
        }
    }


    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getDistance(){
        return distance;
    }
    public void setDistance(long distance){
        this.distance = distance;
    }

    public double getVolume(){
        return volume;
    }
    public void setVolume(double volume){
        this.volume = volume;
    }

    public double getCost(){
        return cost;
    }
    public void setCost(double cost){
        this.cost = cost;
    }

    public unitOfFuel getUnitOfFuel(){
        return this.unitOfFuel;
    }
    public void setUnitOfFuel(unitOfFuel unitOfFuel){
        this.unitOfFuel = unitOfFuel;
    }

    public unitOfMoney getUnitOfMoney(){
        return unitOfMoney;
    }
    public void setUnitOfMoney(unitOfMoney unitOfMoney){
        this.unitOfMoney = unitOfMoney;
    }

    public unitOfMeasure getUnitOfMeasure(){
        return this.unitOfMeasure;
    }
    public void setUnitOfMeasure(unitOfMeasure unitOfMeasure){
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getDistanceInMiles(){
        return this.distanceInMiles;
    }
    public double getDistanceInKilometers(){
        return this.distanceInKilometers;
    }

    public double getVolumeInGallons(){
        return this.volumeInGallons;
    }
    public double getVolumeInLiter(){
        return this.volumeInLiter;
    }

    public double getCostInEuros(){
        return this.costInEuros;
    }
    public double getCostInDollars(){
        return this.costInDollars;
    }

    private void setDistanceInMiles(long kilometers) {
        this.distanceInMiles = kilometers / EXCHANGE_RATE_FOR_KILOMETER_TO_MILE;
    }
    private void setDistanceInKilometers(long miles) {
        this.distanceInKilometers = miles * EXCHANGE_RATE_FOR_KILOMETER_TO_MILE;
    }

    private void setVolumeInGallons(double liters) {
        this.volumeInGallons = liters / EXCHANGE_RATE_FOR_LITER_TO_US_GALLON;
    }
    private void setVolumeInLiter(double gallons) {
        this.volumeInLiter = gallons * EXCHANGE_RATE_FOR_LITER_TO_US_GALLON;
    }

    private void setCostInDollars(double euro) {
        this.costInDollars = euro * EXCHANGE_RATE_FOR_EURO_TO_DOLLAR;
    }
    private void setCostInEuros(double dollar) {
        this.costInEuros = dollar / EXCHANGE_RATE_FOR_EURO_TO_DOLLAR;
    }

}
