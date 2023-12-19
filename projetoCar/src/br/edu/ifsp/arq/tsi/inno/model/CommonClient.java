package br.edu.ifsp.arq.tsi.inno.model;

public class CommonClient extends Client {
    String cpf;

     public CommonClient(String clientName, String cpf) {
        super(clientName);
        this.cpf = cpf;
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getClientName() {
        return super.getClientName();
    }

    @Override
    public void setClientName(String clientName) {
        super.setClientName(clientName);
    }
     
    @Override
    public String clientReport() {
        return super.clientReport() + "\nCPF: " + cpf;
    }

}
