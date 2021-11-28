package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddClientRequest {


    private String clientName;
    private String clientSurname;
    private String personalCode;
    private String city;

    public AddClientRequest(String clientName, String clientSurname, String personalCode, String city) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.personalCode = personalCode;
        this.city = city;
    }

    public AddClientRequest() {
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
