package lv.javaguru.java2.core.requests.Add;

public class AddAdvertismentRequest {

    private String advTitle;
    private String advDescription;

    public AddAdvertismentRequest(String advTitle, String advDescription) {
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
