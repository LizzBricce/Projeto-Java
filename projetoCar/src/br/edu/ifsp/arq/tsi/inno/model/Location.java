package br.edu.ifsp.arq.tsi.inno.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Location {
    private int locationId;
    private static int counterId;
    private LocalDate dateLocation;
    private int qntyDays;
    private LocalDate dayMax;
    private double allPrice;
    private double predictedPrice;
    private LocalDate returnDay;
    private Car car;
    private Client client;
    private boolean locationStatus; 

    public Location(LocalDate dateLocation, int qntyDays, Car car, Client client, boolean locationStatus) {
        this.locationId = ++counterId;
        this.dateLocation = (dateLocation != null) ? dateLocation : LocalDate.now(); 
        this.qntyDays = qntyDays;
        this.car = car;
        this.client = client;
        this.dayMax = calculateDayMax();
        this.locationStatus = true;
        this.predictedPrice = calculatePredictedPrice();
        this.allPrice = calculateAllPrice();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Location.counterId = counterId;
    }

    public LocalDate getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(LocalDate dateLocation) {
        this.dateLocation = dateLocation;
    }

    public double getPredictedPrice() {
        return predictedPrice;
    }

    public void setPredictedPrice(double predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    public int getQntyDays() {
        return qntyDays;
    }

    public void setQntyDays(int qntyDays) {
        this.qntyDays = qntyDays;
    }

    public LocalDate getReturnDay() {
        return returnDay;
    }
    public void setReturnDay(LocalDate returnDay) {
        this.returnDay = returnDay;
    }

    public void setLocationStatus(boolean locationStatus) {
        this.locationStatus = locationStatus;
    }

    public boolean isLocationStatus() {
        return locationStatus;
    }

    public LocalDate getDayMax() {
        return dayMax;
    }

    public void setDayMax(LocalDate dayMax) {
        this.dayMax = dayMax;
    }

    public LocalDate calculateDayMax() {
        return this.dateLocation.plusDays(this.qntyDays);
    }
  
    public double calculatePredictedPrice() {
        return qntyDays * this.car.getCarDailyValue();
    }

    public double getAllPrice() {
        return allPrice;
    }

    public double calculateAllPrice() {
        LocalDate currentDate = LocalDate.now();

        if (this.returnDay != null && !this.returnDay.isBefore(currentDate)) {
            int additionalDays = (int) ChronoUnit.DAYS.between(currentDate, this.returnDay); //

            double totalPrice = this.qntyDays * this.car.getCarDailyValue();
            double additionalPrice = 0;

            if (additionalDays > 0) {
                additionalPrice = additionalDays * (1.05 * this.car.getCarDailyValue());
            }

            return totalPrice + additionalPrice;
        } else {
            return this.qntyDays * this.car.getCarDailyValue();
        }
    }


    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\n-----------------------------------------------------------------" +
                "\nId da locação: " + locationId +
                "\nData da locação: " + dateLocation.format(dateFormatter) +
                "\nCarro que foi alocado: " + car.getCarModel() + " , placa: " + car.getCarPlate() +
                "\nCliente que fez a locação: " + client.getClientName() +
                "\nValor previsto da locação: " + predictedPrice +
                "\nValor total a pagar: R$ " + allPrice +
                "\nDia máximo para devolver o carro: " + this.getDayMax().format(dateFormatter);
    }
}
