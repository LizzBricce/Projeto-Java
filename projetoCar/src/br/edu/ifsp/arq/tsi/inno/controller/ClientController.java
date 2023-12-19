package br.edu.ifsp.arq.tsi.inno.controller;

import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifsp.arq.tsi.inno.model.Client;
import br.edu.ifsp.arq.tsi.inno.model.LegalClient;
import br.edu.ifsp.arq.tsi.inno.model.CommonClient;


public class ClientController {

    Scanner scanner = new Scanner(System.in);

    private static ClientController instance;

    private ArrayList<Client> clients;

    private ClientController() {
        clients = new ArrayList<>();
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public boolean save(Client client) {
        if (client != null) {
            return clients.add(client);
        }
        return false;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public Client findById(int searchId) {
        for (Client c : clients) {
            if (c.getClientId() == searchId) {
                return c;
            }
        }
        return null; 
    }

    public boolean removeClient(int clientId) {
        Client clientToRemove = findById(clientId);
        if (clientToRemove != null) {
            clients.remove(clientToRemove);
            return true;
        }
        return false;
    }

    public boolean isClientUnique(String identifier) {
        for (Client c : clients) {
            if (c instanceof CommonClient) {
                CommonClient commonClient = (CommonClient) c;
                if (commonClient.getCpf().equals(identifier)) {
                    return false; 
                }
            } else if (c instanceof LegalClient) {
                LegalClient legalClient = (LegalClient) c;
                if (legalClient.getCnpj().equals(identifier)) {
                    return false; 
                }
            }
        }
        return true; 
    }
    
    

    public void addClient() {
        System.out.println("-->Escolha o tipo do cliente:");
        System.out.println("-->(1)- Cliente comum");
        System.out.println("-->(2)- Cliente jurídico");
        int choice = scanner.nextInt();
        scanner.nextLine();  // 
    
        if (choice == 1) {
            System.out.println("->Digite o nome do cliente comum:");
            String clientName = scanner.nextLine();
            System.out.println("-->Digite o CPF do cliente comum:");
            String cpf = scanner.nextLine();
    
            if (isClientUnique(cpf)) {
                CommonClient commonClient = new CommonClient(clientName, cpf);
                save(commonClient);
                System.out.println("--Cliente adicionado com sucesso!");
            } else {
                System.out.println("-!-Ja existe um cliente com esse CPF.");
            }
    
        } if(choice == 2) {
            System.out.println("-->Digite o nome do cliente jurídico:");
            String clientName = scanner.nextLine();
            System.out.println("-->Digite a razão social do cliente jurídico:");
            String socialReason = scanner.nextLine();
            System.out.println("-->Digite o CNPJ do cliente jurídico:");
            String cnpj = scanner.nextLine();
    
            if (isClientUnique(cnpj)) {
                LegalClient legalClient = new LegalClient(clientName, cnpj, socialReason);
                save(legalClient);
                System.out.println("--Cliente adicionado com sucesso!");
            } else {
                System.out.println("-!-Ja existe um cliente com esse CNPJ.");
            }

        }else{
            System.out.println("-!-Opção invalida!!");   
        }
    }
    
    public String Report() {
        StringBuilder report = new StringBuilder();
        for (Client c : clients) {
            report.append(c.clientReport()).append("\n\n");
        }
        return report.toString();
    }
    }
