package br.edu.ifsp.arq.tsi.inno.view;

import java.time.LocalDate;
// import java.time.LocalDate;
import java.util.Scanner;
import br.edu.ifsp.arq.tsi.inno.controller.CarController;
import br.edu.ifsp.arq.tsi.inno.controller.ClientController;
import br.edu.ifsp.arq.tsi.inno.controller.LocationController;
import br.edu.ifsp.arq.tsi.inno.model.Car;
import br.edu.ifsp.arq.tsi.inno.model.Client;
import br.edu.ifsp.arq.tsi.inno.model.CommonClient;
import br.edu.ifsp.arq.tsi.inno.model.LegalClient;
// import br.edu.ifsp.arq.tsi.inno.model.Location;
import br.edu.ifsp.arq.tsi.inno.model.Location;


public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        CarController carController = CarController.getInstance();
        ClientController clientController = ClientController.getInstance();
        LocationController locationController = LocationController.getInstance();
 
        carController.save(new Car(0, 4, 2022, 145.00, "Toyota Corolla", " XSE", "STU-0123", true, false));
        carController.save(new Car(0, 2, 2021, 300.00, " Ford Mustang", "GT", "ABC-1234", false, false));
        carController.save(new Car(0, 4, 2023, 200.40, "Civic", "Touring", "XYZ-5678", true, false));
        carController.save(new Car(0, 2, 2020, 540.90, "Chevrolet Camaro", "SS", "DEF-9876", true, false));
        carController.save(new Car(0, 2, 2022, 400.00, "Mazda MX-5 Miata", "Grand Touring", "QWE-4567", false, false));
        carController.save(new Car(0, 4, 2021, 120.10, "Tesla Model 3", "Long Range", "MNO-8901", true, false));
        carController.save(new Car(0, 4, 2023, 340.40, "BMW 3 Series", "M340i", "GHI-3456", false, false));
        carController.save(new Car(0, 4, 2022, 110.90, "Mercedes-Benz C-Class", " C300", "UVW-2345", true, false));
       
        clientController.save(new CommonClient("ana","11111"));
        clientController.save(new CommonClient("julia","445234"));
        clientController.save(new CommonClient("luiza","54534"));
        clientController.save(new CommonClient("camila","44444"));

        clientController.save(new LegalClient("maria", "656537", "maria jj"));
        clientController.save(new LegalClient("eduarda", "47756567", "eduarda dd"));
        clientController.save(new LegalClient("clara", "4556765767", "clara ll"));
        clientController.save(new LegalClient("alessandra", "45637927", "alessandra 21"));
        clientController.save(new LegalClient("jessica", "11122567", "jessica 43"));

        Car c10 = new Car(0, 2, 2023, 240.30, "Volkswagen Golf", "GTI", "PQR-7890", false, false);
        carController.save(c10);
        Client cl1 = new CommonClient("elizabeth", "1234");
        clientController.save(cl1);
        Location l1 = new Location(LocalDate.of(2023, 12, 15), c10, cl1, true);
        l1.setQntyDays(2);
        cl1.save(l1);
        locationController.save(l1);

        Car c11 = new Car(0, 2, 2020, 560.40, "Ford F-150", "Ford F-150", "JKL-6789", true, false);
        carController.save(c11);
        Client cl2 = new CommonClient("juju", "1211134");
        clientController.save(cl2);
        Location l2 = new Location(LocalDate.of(2023, 12, 17), c11, cl2, true);
        l2.setQntyDays(5);
        cl2.save(l2);
        locationController.save(l2);

        int choice;
        int idSearch;
        Client foundClient;
        Car foundCar;


        do{
            menu();
            choice = scanner.nextInt();
            switch (choice) {
            case 1:     
            String relatoryCars = carController.Report();
            System.out.println(relatoryCars);
                break;

            case 2:
            String relatoryClients = clientController.Report();
            System.out.println(relatoryClients);
                break;
            
            case 3:
            String relatoryLocations = locationController.Report();
            System.out.println(relatoryLocations);
                break;

            case 4:
            System.out.println("-->Digite o ID do cliente: ");
            idSearch = scanner.nextInt();
            foundClient = clientController.findById(idSearch);

            if (foundClient != null) {
                System.out.println("---Cliente encontrado: ");
                foundClient.printLocationHistory();
            } else {
                System.out.println("-!-Cliente não encontrado.");
            }
                break;

            case 5:
            System.out.println("-->Digite  o ID do carro: ");
            idSearch = scanner.nextInt();
            foundCar = carController.findById(idSearch);

            if (foundCar != null) {
                System.out.println("---Carro encontrado: " + foundCar);
            } else {
                System.out.println("-!-Carro não encontrado.");
            }
                break;

            case 6:
            clientController.addClient();
                break;   

            case 7:
            carController.addCar();

                break;

            case 8:
            locationController.makeLocation();
                break;

            case 9:
            locationController.makeReturn();    

                break;
            case 10:
            System.out.println("-->Digite o ID do cliente que deseja remover:");
            int clientIdToRemove = scanner.nextInt();
            boolean clientRemoved = clientController.removeClient(clientIdToRemove);
        
            if (clientRemoved) {
                System.out.println("--Cliente removido com sucesso!!");
            } else {
                System.out.println("-!-Cliente não encontrado.");
            }
            break;

            case 11:
            System.out.println("-->Digite o ID do carro que deseja remover:");
            int carIdToRemove = scanner.nextInt();
            boolean carRemoved = carController.removeCar(carIdToRemove);
        
            if (carRemoved) {
                System.out.println("--Carro removido com sucesso.");
            } else {
                System.out.println("-!-Carro não encontrado.");
            }
            break;

            case 12:
            System.out.println("-->Digite o ID da locação que deseja remover:");
            int locationIdToRemove = scanner.nextInt();
            boolean locationRemoved = locationController.removeLocation(locationIdToRemove);
        
            if (locationRemoved) {
                System.out.println("---Locação removida com sucesso.");
            } else {
                System.out.println("-!-Locação não encontrada ou não pôde ser removida.");
            }
            break;
            case 13:
            System.out.println("fechando programa.....");
                break;      

            default:
            System.out.println("-!-Opção não encontrada tente novamente");
                break;    

            }
        } while (choice != 12);

        scanner.close();
    }
        private static void menu(){
        System.out.println("._____________________________________________________.");
        System.out.println("|-----------------------------------------------------|");
        System.out.println("|------Bem vindo ao sistema de locação de carros------|");
        System.out.println("|-----------------------------------------------------|");
        System.out.println("|-------------------------MENU:-----------------------|");
        System.out.println("|-(1):consultar carros cadastrados.-------------------|");
        System.out.println("|-(2):consultar clientes cadastrados.-----------------|");
        System.out.println("|-(3):consultar lista de locações.--------------------|");
        System.out.println("|-(4):consultar historico de um cliente.--------------|");
        System.out.println("|-(5):pesquisar carro por id.-------------------------|");
        System.out.println("|-(6):cadastrar cliente.------------------------------|");
        System.out.println("|-(7):cadastrar carro.--------------------------------|");
        System.out.println("|-(8):locar carro.------------------------------------|");
        System.out.println("|-(9):devolver carro.---------------------------------|");
        System.out.println("|-(10):remover cliente da lista.----------------------|");
        System.out.println("|-(11):remover carro da lista.------------------------|");
        System.out.println("|-(12):remover locaçao da lista.----------------------|");
        System.out.println("|-(13):sair.------------------------------------------|");
        System.out.println("|--------------(escolha uma opçao:)-------------------|");
        System.out.println("|-----------------------------------------------------|");
        System.out.println("._____________________________________________________.");
                
    }
}
