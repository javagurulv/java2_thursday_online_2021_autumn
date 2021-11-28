package lv.javaguru.java2.oddJobs.core.requests.remove;

public class RemoveSpecialistRequest {

    private Long specialistId;
    private String specialistName;
    private String specialistSurname;

    public RemoveSpecialistRequest(Long specialistId, String specialistName, String specialistSurname) {
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
    }

    public RemoveSpecialistRequest() {
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public void setSpecialistSurname(String specialistSurname) {
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
}
