package lv.javaguru.java2.oddJobs.core.requests.add;

public class AddAdvertisementRequest {

    private String advTitle;
    private String advDescription;

    public AddAdvertisementRequest(String advTitle, String advDescription) {
        this.advTitle = advTitle;
        this.advDescription = advDescription;
    }

    public AddAdvertisementRequest() {
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvDescription() {
        return advDescription;
    }

    public void setAdvDescription(String advDescription) {
        this.advDescription = advDescription;
    }
}
