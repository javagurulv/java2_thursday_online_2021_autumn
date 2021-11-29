package lv.javaguru.java2.hospital.patient.core.requests;

public class AddPatientRequest {
    private String name;
    private String surname;
    private String personalCode;

    public AddPatientRequest() {
    }

    public AddPatientRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }
}
