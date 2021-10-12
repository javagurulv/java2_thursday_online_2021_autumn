package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddClientRequest {


    private String name;
    private String surname;

    public AddClientRequest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}