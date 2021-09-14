package lv.javaguru.java2.hospital.doctor.core.requests;

public class SearchDoctorsRequest {

    private String name;
    private String surname;

    public SearchDoctorsRequest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.surname != null && !this.surname.isEmpty();
    }
}
