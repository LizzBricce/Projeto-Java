package br.edu.ifsp.arq.tsi.inno.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifsp.arq.tsi.inno.model.Car;
import br.edu.ifsp.arq.tsi.inno.model.Client;
import br.edu.ifsp.arq.tsi.inno.model.Location;

public class LocationController {
    Scanner scanner = new Scanner(System.in);

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private static LocationController instance;

    private ArrayList<Location> locations;

    private LocationController() {
        locations = new ArrayList<>();
    }

    public static LocationController getInstance() {
        if (instance == null) {
            instance = new LocationController();
        }
        return instance;
    }

    public boolean save(Location location) {
        if (location != null) {
            return locations.add(location);
        }
        return false;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public Location findById(int searchId) {
        for (Location l : locations) {
            if (l.getLocationId() == searchId) {
                return l;
            }
        }
        return null; 
    }

    public boolean removeLocation(int locationId) {
        Location locationToRemove = findById(locationId);
        if (locationToRemove != null) {
            locations.remove(locationToRemove);
            return true;
        }
        return false;
    }

    public void makeLocation() {
        int clientId, carId;
        
        System.out.println("-->Digite o ID do cliente que vai fazer a locação:");
        clientId = scanner.nextInt();
    
        Client client = ClientController.getInstance().findById(clientId);
        if (client != null) {
            System.out.println("---Cliente encontrado!!!\n" + (client.clientReport()));
            System.out.println("-->Digite o ID do carro a ser locado: ");
            carId = scanner.nextInt();
    
            Car car = CarController.getInstance().findById(carId);
            if (car != null && car.isCarStatus()) { 
                System.out.println("---Carro encontrado!!!\n" +(car));
                Location location = new Location(null, car, client, false); 
    
                System.out.println("-->Digite a quantidade de dias que o carro ficará alocado: ");
                location.setQntyDays(scanner.nextInt());

                location.setPredictedPrice(location.calculatePredictedPrice()); //
    
                System.out.println("-----Recibo da locação----------");
                System.out.println("Dia máximo para devolver o carro: " + location.getDayMax().format(dateFormatter));
                System.out.println("Valor previsto a pagar: R$ " + decimalFormat.format(location.getPredictedPrice()));
                System.out.println("(O valor pode ser alterado de acordo com o dia de retorno)");
    
                Report();
    
                car.setCarStatus(false);
                location.setReturnDay(null);
                client.save(location);
                save(location);
            } else {
                System.out.println("-!-Carro não encontrado ou indisponivel");
            }
        } else {
            System.out.println("-!-Cliente não encontrado");
        }
    }
    
    public void makeReturn() {
        int clientId, locationId;
    
        System.out.println("-->Digite o ID do cliente que vai fazer a devolução: ");
        clientId = scanner.nextInt();
    
        Client client = ClientController.getInstance().findById(clientId);
        if (client != null) {
            System.out.println("--Cliente encontrado!!!\n" + client.clientReport());
            // mostrar a lista de locações desse cliente
            System.out.println("-------------------------------------------------------------");
            client.printLocationHistory();
            System.out.println("-->Digite o ID da locação:");
            
            locationId = scanner.nextInt();
    
            ArrayList<Location> locations = client.getLocations();
            if (locations != null) {
                for (Location location : locations) {
                    if (location.getLocationId() == locationId) {
                        System.out.println("--Locação encontrada!!!\n");
    
                        if (location.isLocationStatus()) {
                            location.setLocationStatus(false);
                            Car car = location.getCar();
                            car.setCarStatus(true);
                            location.setReturnDay(LocalDate.now());
                            
                            location.calculatePredictedPrice();
                            location.setAllPrice(location.calculateAllPrice());
    
                            System.out.println("-----Recibo da locação----------");
                            int additionalDays = (int) ChronoUnit.DAYS.between(location.getDayMax(), LocalDate.now());
                            System.out.println((additionalDays <= 0) ? "--Carro devolvido no prazo." : "--Dias adicionais: " +additionalDays);
                            System.out.println("(a cada dia adicional é cobrado 5% a mais do valor da diaria): ");
                            System.out.println("--Valor total a pagar de acordo com a quantidade de dias que o carro ficou locado: R$" + decimalFormat.format(location.getAllPrice()));
                            System.out.println("--Devolução concluída com sucesso!!");
                        } else {
                            System.out.println("-!-Carro já foi devolvido.");
                        }
                        return;
                    }
                }
            }
            System.out.println("-!-Locação não encontrada no histórico do cliente.");
        } else {
            System.out.println("-!-Cliente não encontrado");
        }
    }
    
    
public String Report() {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    StringBuilder report = new StringBuilder();
    
    for (Location l : locations) {
        report.append(
            "\n-------------------------------------------------------------"+
            "\nId da locaçao: "+ l.getLocationId()+
            "\nData da locação: " + l.getDateLocation().format(dateFormatter) +
            "\nQuantidade de dias da locação: " + l.getQntyDays() +
            "\nDia máximo da devolução: " + l.getDayMax().format(dateFormatter) +
            "\nCarro que foi alocado: " + l.getCar().getCarModel() + ", id do carro: " +l.getCar().getCarId()+
            "\nCliente que fez a locação: " + l.getClient().getClientName() + ", id do cliente: " + l.getClient().getClientId()+
            "\nDia do retorno do carro: " + (l.getReturnDay() != null ? l.getReturnDay().format(dateFormatter) : "o carro ainda não foi devolvido") +
            "\nStatus da locação: " + (l.isLocationStatus() ? "em andamento" : "finalizada")+
            "\n-------------------------------------------------------------"
        );
    }    
    return report.toString();
}
}


  

     
