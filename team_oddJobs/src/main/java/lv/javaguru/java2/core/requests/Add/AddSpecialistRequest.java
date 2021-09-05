package lv.javaguru.java2.core.requests.Add;

public class AddSpecialistRequest {

    private String name;
    private String surname;
    private String profession;

    public AddSpecialistRequest(String name, String surname, String profession) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
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
}
