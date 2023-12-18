package br.edu.ifsp.arq.tsi.inno.controller;

import br.edu.ifsp.arq.tsi.inno.model.Car;
import java.util.ArrayList;
import java.util.Scanner;

public class CarController {

    Scanner scanner = new Scanner(System.in);

    private static CarController instance;

    private ArrayList <Car> cars;

   private CarController(){
     cars = new ArrayList<>();
   }

   public static CarController getInstance(){
    if(instance ==null){
        instance = new CarController();
    }
    return instance;
   }

   public boolean save(Car car){
    if(car != null){
        return cars.add(car);
    }
    return false;
   }

   public ArrayList<Car> getCars(){
    return cars;
   }

   public Car findById(int searchId){
    for(Car c: cars){
        if(c.getCarId() == searchId){
            return c;
        }
    }
    return null;
   }

    public boolean removeCar(int carId) {
        Car foundCar = findById(carId);

     if (foundCar != null) {
            return cars.remove(foundCar);
     }

    return false;
}
   public boolean isCarUnique(String identifier) {
    for (Car c : cars) {
        if (c instanceof Car) {
            Car car = (Car) c;
            if (car.getCarPlate().equals(identifier)) {
                return false;
            }
        }
    }
    return true;
}

public void addCar() {
    try {
        System.out.println("-->Digite o modelo do carro:");
        String carModel = scanner.nextLine();

        System.out.println("-->Digite a marca do carro:");
        String carBrand = scanner.nextLine();

        System.out.println("-->Digite o número de portas do carro:");
        int carNumDoors = scanner.nextInt();

        System.out.println("-->Digite o ano do carro:");
        int carYear = scanner.nextInt();

        System.out.println("-->Digite o valor da diária do carro:");
        double carDailyValue = scanner.nextDouble();

        scanner.nextLine(); 

        boolean hasAirConditioning; 

        System.out.println("-->O carro tem ar condicionado? (S para Sim, N para Não):");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("N")) {
            hasAirConditioning = input.equalsIgnoreCase("S");
        } else {
            System.out.println("-!-Valor invalido.");
            return; 
        }

        System.out.println("-->Digite a placa do carro:");
        String carPlate = scanner.nextLine();

        if (isCarUnique(carPlate)) {
            Car car = new Car(0, carNumDoors, carYear, carDailyValue, carModel, carBrand, carPlate, hasAirConditioning, false);
            CarController.getInstance().save(car);
            System.out.println("---Carro adicionado com sucesso!");
        } else {
            System.out.println("-!-Já existe um carro com essa placa.");
        }
    } catch (java.util.InputMismatchException e) {
        System.out.println("-!-Erro: O valor inserido é inválido.");
        scanner.nextLine(); 
    }
}

   public String Report(){
    StringBuilder report = new StringBuilder();
    for(Car c : cars){
        report.append(
        "\n-------------------------------------------------------------"+
        "\nCarro ID: " + c.getCarId() +
        "\nNúmero de portas: " + c.getCarQntyDoors() +
        "\nAno: " + c.getCarYear() +
        "\nValor diário: R$" + c.getCarDailyValue() +
        "\nMarca: " + c.getCarBrand() +
        "\nModelo: " + c.getCarModel() +
        "\nPlaca: " + c.getCarPlate() +
        "\nAr condicionado: " + (c.isCarAirConditioning() ? "Sim" : "Não") +
        "\nStatus: " + (c.isCarStatus() ? "Disponível" : "Indisponível") +
        "\n-------------------------------------------------------------");
    }
    return report.toString();
}
  
}
