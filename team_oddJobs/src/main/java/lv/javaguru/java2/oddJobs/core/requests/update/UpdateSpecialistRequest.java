package lv.javaguru.java2.oddJobs.core.requests.update;

public class UpdateSpecialistRequest {
    private Long id;
    private String newName;
    private String newSurname;
    private String newProfession;
    private String newPersonalCode;
    private String newCity;

    public UpdateSpecialistRequest() { }

    public Long getId() {
        return id;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public String getNewProfession() {
        return newProfession;
    }

    public String getNewPersonalCode() {
        return newPersonalCode;
    }

    public String getNewCity() {
        return newCity;
    }
}
