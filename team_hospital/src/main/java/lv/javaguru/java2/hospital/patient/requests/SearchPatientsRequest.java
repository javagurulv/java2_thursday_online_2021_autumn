package lv.javaguru.java2.hospital.patient.requests;

public class SearchPatientsRequest {
    private String name;
    private String surname;
    private String personalCode;

    private Ordering ordering;
    private Paging paging;


    public SearchPatientsRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    public SearchPatientsRequest(String name, String surname, String personalCode, Ordering ordering) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.ordering = ordering;
    }

    public SearchPatientsRequest(String name, String surname, String personalCode, Paging paging) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.paging = paging;
    }

    public SearchPatientsRequest(String name, String surname, String personalCode, Ordering ordering, Paging paging) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.ordering = ordering;
        this.paging = paging;
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

    public boolean isNameProvided(){
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isSurnameProvided(){
        return this.surname != null && !this.surname.isEmpty();
    }

    public boolean isPersonalCodeProvided(){
        return this.personalCode != null && !this.personalCode.isEmpty();
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
