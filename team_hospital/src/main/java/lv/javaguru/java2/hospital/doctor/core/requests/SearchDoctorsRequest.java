package lv.javaguru.java2.hospital.doctor.core.requests;

public class SearchDoctorsRequest {

    private Long id;
    private String name;
    private String surname;
    private String speciality;

    private Ordering ordering;
    private Paging paging;


    public SearchDoctorsRequest(Long id, String name, String surname, String speciality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality, Ordering ordering) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.ordering = ordering;
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality, Paging paging) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.paging = paging;
    }

    public SearchDoctorsRequest(Long id, String name, String surname, String speciality, Ordering ordering, Paging paging) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.ordering = ordering;
        this.paging = paging;
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

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
