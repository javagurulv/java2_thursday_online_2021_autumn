package lv.javaguru.java2.core.requests.Remove;

public class RemoveClientRequest {

    private long clientId;
    private String clientName;
    private String clientSurname;

    public RemoveClientRequest(long id, String name, String surname) {
        this.clientId = id;
        this.clientName = name;
        this.clientSurname = surname;
    }

    public long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }
}
