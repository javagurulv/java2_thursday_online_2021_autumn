package lv.javaguru.java2.core.requests.Find;


public class FindClientsRequest {

    private Long clientId;
    private String clientName;
    private String clientSurname;


    public FindClientsRequest(Long clientId, String clientName, String clientSurname) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public boolean isIdProvided() {
        return this.clientId != null;
    }

    public boolean isNameProvided () {
        return this.clientName != null && !this.clientName.isEmpty();
    }

    public boolean isSurnameProvide () {
        return this.clientSurname != null && !this.clientName.isEmpty();
    }
}
