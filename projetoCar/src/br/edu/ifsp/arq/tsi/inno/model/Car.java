package br.edu.ifsp.arq.tsi.inno.model;

public class Car {
    
    private int carId;
    private int carQntyDoors;
    private int carYear;
    private static int counterId;
    private double carDailyValue;
    private String carBrand;
    private String carModel;
    private String carPlate;
    private boolean carAirConditioning; 
    private boolean carStatus;
     


    public Car(int carId, int carQntyDoors, int carYear, double carDailyValue, String carBrand, String carModel, String carPlate, boolean carAirConditioning, boolean carStatus) {
        this.carId = ++counterId;
        this.carQntyDoors = carQntyDoors;
        this.carYear = carYear;
        this.carDailyValue = carDailyValue;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carPlate = carPlate;
        this.carAirConditioning = carAirConditioning;
        this.carStatus = true;
    }

    public int getCarId() {
        return carId;
    }

    public int getCarQntyDoors() {
        return carQntyDoors;
    }

    public int getCarYear() {
        return carYear;
    }

    public double getCarDailyValue() {
        return carDailyValue;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public boolean isCarAirConditioning() {
        return carAirConditioning;
    }

    public boolean isCarStatus() {
        return carStatus; 
    }

    public void setCarQntyDoors(int carQntyDoors) {
        this.carQntyDoors = carQntyDoors;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public void setCarDailyValue(double carDailyValue) {
        this.carDailyValue = carDailyValue;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setCarAirConditioning(boolean carAirConditioning) {
        this.carAirConditioning = carAirConditioning;
    }

    public void setCarStatus(boolean carStatus) {
        this.carStatus = carStatus;
    }

    public void carUnavailable(){
        this.carStatus = false;
    }
    

public String toString() {
    return "Carro ID: " + carId +
           "\nNúmero de portas: " + carQntyDoors +
           "\nAno: " + carYear +
           "\nValor diário: " + carDailyValue +
           "\nMarca: " + carBrand +
           "\nModelo: " + carModel +
           "\nPlaca: " + carPlate +
           "\nAr condicionado: " + (carAirConditioning ? "Sim" : "Não") +
           "\nStatus: " + (carStatus ? "Disponível" : "Indisponível"); 
}
}