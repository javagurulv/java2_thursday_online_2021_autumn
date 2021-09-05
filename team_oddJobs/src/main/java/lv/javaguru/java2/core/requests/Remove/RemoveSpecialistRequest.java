package lv.javaguru.java2.core.requests.Remove;

public class RemoveSpecialistRequest {

    private long specialistId;
    private String specialistName;
    private String specialistSurname;

    public RemoveSpecialistRequest(long specialistId, String specialistName, String specialistSurname) {
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
    }

    public long getSpecialistId() {
        return specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public String getSpecialistSurname() {
        return specialistSurname;
    }
}
