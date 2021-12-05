package lv.javaguru.java2.hospital.doctor.core.requests;

public class AddDoctorRequest {

    private String name;
    private String surname;
    private String speciality;

    public AddDoctorRequest() {
    }

    public AddDoctorRequest(String name, String surname, String speciality) {
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
