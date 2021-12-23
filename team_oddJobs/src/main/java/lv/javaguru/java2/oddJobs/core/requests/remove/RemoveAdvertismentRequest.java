package lv.javaguru.java2.oddJobs.core.requests.remove;

public class RemoveAdvertismentRequest {

    private Long advId;
    private String advTitle;


    public RemoveAdvertismentRequest(Long advId, String advTitle) {
        this.advId = advId;
        this.advTitle = advTitle;
    }

    public RemoveAdvertismentRequest() {

    }


    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }
}


