package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddClientRequest {


    private String name;
    private String surname;
    private String personalCode;
    private String city;

    public AddClientRequest(String name, String surname, String personalCode, String city) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getCity() {
        return city;
    }
}
