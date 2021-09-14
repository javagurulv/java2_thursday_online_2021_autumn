package lv.javaguru.java2.hospital.doctor.core.requests;

public class SearchDoctorsRequest {

    private String id;
    private String name;
    private String surname;
    private String speciality;


    public SearchDoctorsRequest(String id, String name, String surname, String speciality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    public String getId() {
        return id;
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

    public boolean isIdProvided() {
        return this.id != null && !this.id.isEmpty() && Long.parseLong(this.id) != 0;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.surname != null && !this.surname.isEmpty();
    }

    public boolean isSpecialityProvided() {
        return this.speciality != null && !this.speciality.isEmpty();
    }

}
