package br.edu.ifsp.arq.tsi.inno.model;

import java.util.ArrayList;

public abstract class Client {
    protected int clientId; 
    protected String clientName;
    protected static int counterId;
    protected ArrayList<Location> locations = new ArrayList<>();

    public Client(String clientName) {
        this.clientName = clientName;
        this.clientId = ++counterId;
    }
    public int getClientId() {
        return clientId;
    }
    
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    public boolean save(Location location) {
        if (locations == null) {
            locations = new ArrayList<>();
        }
        if (location != null) {
            return locations.add(location);
        }
        return false;
    }
    
    public void printLocationHistory() {
        if (locations != null && !locations.isEmpty()) {
            System.out.println("--Histórico de locações do cliente " + clientName + ":");
            for (Location l : locations) {
                System.out.println(l.toString());
            }
        } else {
            System.out.println("-!-O cliente " + clientName + " não possui histórico de locações.");
        }
    }

    public boolean isLocationInList(Location location) {
        if (locations != null && !locations.isEmpty()) {
            return locations.contains(location);
        }
        return false;
    }

    public String clientReport() {
        return "\nNome: " + clientName +
               "\nID: " + clientId;
    }


}

    
   

   



