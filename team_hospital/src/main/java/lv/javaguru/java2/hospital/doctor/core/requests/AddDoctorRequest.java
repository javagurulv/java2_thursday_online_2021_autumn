package lv.javaguru.java2.hospital.doctor.core.requests;

public class AddDoctorRequest {

    private String name;
    private String surname;
    private String speciality;

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
}
