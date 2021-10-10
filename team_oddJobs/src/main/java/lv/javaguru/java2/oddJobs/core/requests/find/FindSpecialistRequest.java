package lv.javaguru.java2.oddJobs.core.requests.find;

public class FindSpecialistRequest {

    private Long specialistId;
    private String specialistName;
    private String specialistSurname;
    private String specialistProfession;

    private Ordering ordering;
    private Paging paging;

    public FindSpecialistRequest(String specialistName, String specialistSurname, String specialistProfession) {
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;

    }

    public FindSpecialistRequest(String specialistName, String specialistSurname, String specialistProfession, Ordering ordering) {
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;
        this.ordering = ordering;
    }

    public FindSpecialistRequest(String specialistName, String specialistSurname, String specialistProfession, Paging paging) {
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;
        this.paging = paging;
    }

    public FindSpecialistRequest(String specialistName, String specialistSurname, String specialistProfession, Ordering ordering, Paging paging) {
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;
        this.ordering = ordering;
        this.paging = paging;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public String getSpecialistSurname() {
        return specialistSurname;
    }

    public String getSpecialistProfession() {
        return specialistProfession;
    }

    public Paging getPaging() {
        return paging;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public boolean isIdProvided() {
        return this.specialistId != null;
    }

    public boolean isNameProvided() {
        return this.specialistName != null && !this.specialistName.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.specialistSurname != null && !this.specialistSurname.isEmpty();
    }

    public boolean isProfessionProvided() {
        return this.specialistProfession != null && !this.specialistProfession.isEmpty();
    }
}
