package lv.javaguru.java2.core.requests.Find;

public class FindSpecialistRequest {

    private Long specialistId;
    private String specialistName;
    private String specialistSurname;

    public FindSpecialistRequest(Long specialistId, String specialistName, String specialistSurname) {
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
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

    public boolean isIdProvided() {
        return this.specialistId != null;
    }

    public boolean isNameProvided() {
        return this.specialistName != null && !this.specialistName.isEmpty();
    }

    public boolean isSurnameProvided() {
        return this.specialistSurname !=null && !this.specialistSurname.isEmpty();
    }
}
