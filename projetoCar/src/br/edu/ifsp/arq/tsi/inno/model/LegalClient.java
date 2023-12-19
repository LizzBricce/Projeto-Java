package br.edu.ifsp.arq.tsi.inno.model;

public class LegalClient extends Client{

    private String cnpj;
    private String socialReason;

    public LegalClient(String clientName, String cnpj, String socialReason) {
        super(clientName);
        this.cnpj = cnpj;
        this.socialReason = socialReason;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getSocialReason() {
        return socialReason;
    }
    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
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
        return super.clientReport() + "\nCNPJ: " + cnpj + "\nRazão Social: " + socialReason;
    }

   
}