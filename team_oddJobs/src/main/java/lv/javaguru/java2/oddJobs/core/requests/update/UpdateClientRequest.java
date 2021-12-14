package lv.javaguru.java2.oddJobs.core.requests.update;

public class UpdateClientRequest {

    private Long id;
    private String newClientName;
    private String newClientSurname;
    private String newClientPersonalCode;
    private String newClientCity;

    public UpdateClientRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getNewClientName() {
        return newClientName;
    }

    public String getNewClientSurname() {
        return newClientSurname;
    }

    public String getNewClientPersonalCode() {
        return newClientPersonalCode;
    }

    public String getNewClientCity() {
        return newClientCity;
    }
}
