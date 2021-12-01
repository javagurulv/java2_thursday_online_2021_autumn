package lv.javaguru.java2.oddJobs.core.requests.remove;

public class RemoveAdvertismentRequest {

    private String advTitle;
    private String advDescription;
    private Long advId;

    public RemoveAdvertismentRequest (String advTitle, String advDescription, Long advId) {
        this.advDescription = advDescription;
        this.advTitle = advTitle;
        this.advId = advId;
    }

    public RemoveAdvertismentRequest(String advertismentTitle, long advertismentId) {
    }

    public RemoveAdvertismentRequest() {

    }

    public Long getAdvId() {return advId;}

    public String getAdvTitle() {return advTitle;}

    public String getAdvDescription() {return advDescription;}


    }


