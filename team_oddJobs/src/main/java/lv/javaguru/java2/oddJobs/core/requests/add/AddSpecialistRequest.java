package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddSpecialistRequest {

    private String name;
    private String surname;
    private String profession;
    private String personalCode;
    private String city;

    public AddSpecialistRequest(String name, String surname, String profession, String personalCode, String city) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.personalCode = personalCode;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfession() {
        return profession;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getCity() {
        return city;
    }
}
