package lv.javaguru.java2.core.requests.Remove;

public class RemoveClientRequest {
    private String clientName;
    private String clientSurname;
    private Long clientId;


    public RemoveClientRequest(String clientName, String clientSurname, Long clientId) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientId = clientId;
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
}
