package lv.javaguru.java2.hospital.doctor.core.requests;

public class SearchDoctorsRequest {
    private Long id;
    private String name;
    private String surname;
    private String speciality;

    private DoctorOrdering doctorOrdering;
    private DoctorPaging doctorPaging;


    public SearchDoctorsRequest() {
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality, DoctorOrdering doctorOrdering) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.doctorOrdering = doctorOrdering;
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality, DoctorPaging doctorPaging) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.doctorPaging = doctorPaging;
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality, DoctorOrdering doctorOrdering, DoctorPaging doctorPaging) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.doctorOrdering = doctorOrdering;
        this.doctorPaging = doctorPaging;
    }

    public Long getId() {
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
        return this.id != null;
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

    public DoctorOrdering getOrdering() {
        return doctorOrdering;
    }

    public DoctorPaging getPaging() {
        return doctorPaging;
    }

    public DoctorOrdering getDoctorOrdering() {
        return doctorOrdering;
    }

    public DoctorPaging getDoctorPaging() {
        return doctorPaging;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDoctorOrdering(DoctorOrdering doctorOrdering) {
        this.doctorOrdering = doctorOrdering;
    }

    public void setDoctorPaging(DoctorPaging doctorPaging) {
        this.doctorPaging = doctorPaging;
    }
}
