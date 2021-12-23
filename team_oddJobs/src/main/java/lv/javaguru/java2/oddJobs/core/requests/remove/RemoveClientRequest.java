package lv.javaguru.java2.oddJobs.core.requests.remove;

public class RemoveClientRequest {
    private String clientName;
    private String clientSurname;
    private Long clientId;


    public RemoveClientRequest(String clientName, String clientSurname, Long clientId) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientId = clientId;
    }

    public RemoveClientRequest(String clientName, String clientSurname) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public RemoveClientRequest() {

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

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
