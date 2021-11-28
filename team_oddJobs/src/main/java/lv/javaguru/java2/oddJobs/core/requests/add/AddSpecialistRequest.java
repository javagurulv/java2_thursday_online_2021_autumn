package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddSpecialistRequest {

    private String specialistName;
    private String specialistSurname;
    private String specialistProfession;
    private String personalCode;
    private String city;

    public AddSpecialistRequest(String specialistName, String specialistSurname, String specialistProfession, String personalCode, String city) {
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;
        this.personalCode = personalCode;
        this.city = city;
    }

    public AddSpecialistRequest() {
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistSurname() {
        return specialistSurname;
    }

    public void setSpecialistSurname(String specialistSurname) {
        this.specialistSurname = specialistSurname;
    }

    public String getSpecialistProfession() {
        return specialistProfession;
    }

    public void setSpecialistProfession(String specialistProfession) {
        this.specialistProfession = specialistProfession;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
