package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddAdvertisementRequest {

    private String advTitle;
    private String advDescription;

    public AddAdvertisementRequest(String advTitle, String advDescription) {
        this.advTitle = advTitle;
        this.advDescription = advDescription;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public String getAdvDescription() {
        return advDescription;
    }
}
