package lv.javaguru.java2.hospital.patient.core.requests;

public class SearchPatientsRequest {
    private String name;
    private String surname;
    private String personalCode;

    private PatientOrdering patientOrdering;
    private PatientPaging patientPaging;


    public SearchPatientsRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    public SearchPatientsRequest(String name, String surname, String personalCode, PatientOrdering patientOrdering) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.patientOrdering = patientOrdering;
    }

    public SearchPatientsRequest(String name, String surname, String personalCode, PatientPaging patientPaging) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.patientPaging = patientPaging;
    }

    public SearchPatientsRequest(String name, String surname, String personalCode, PatientOrdering patientOrdering, PatientPaging patientPaging) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.patientOrdering = patientOrdering;
        this.patientPaging = patientPaging;
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

    public PatientOrdering getOrdering() {
        return patientOrdering;
    }

    public PatientPaging getPaging() {
        return patientPaging;
    }
}
