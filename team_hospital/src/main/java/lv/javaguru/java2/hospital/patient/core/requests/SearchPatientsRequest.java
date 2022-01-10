package lv.javaguru.java2.hospital.patient.core.requests;

public class SearchPatientsRequest {
    private String name;
    private String surname;
    private String personalCode;
    private String orderBy;
    private String orderDirection;
    private String pageNumber;
    private String pageSize;

    private PatientOrdering patientOrdering;
    private PatientPaging patientPaging;

    public SearchPatientsRequest() {
    }

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

    public SearchPatientsRequest(String name, String surname, String personalCode,
                                 String orderBy, String orderDirection,
                                 String pageNumber, String pageSize) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
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

    public PatientOrdering getPatientOrdering() {
        return patientOrdering;
    }

    public PatientPaging getPatientPaging() {
        return patientPaging;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public void setPatientOrdering(PatientOrdering patientOrdering) {
        this.patientOrdering = patientOrdering;
    }

    public void setPatientPaging(PatientPaging patientPaging) {
        this.patientPaging = patientPaging;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
