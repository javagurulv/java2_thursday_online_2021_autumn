package lv.javaguru.java2.core.requests.Find;

public class FindSpecialistByProfessionRequest {
    private String profession;

    public FindSpecialistByProfessionRequest(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }
}
