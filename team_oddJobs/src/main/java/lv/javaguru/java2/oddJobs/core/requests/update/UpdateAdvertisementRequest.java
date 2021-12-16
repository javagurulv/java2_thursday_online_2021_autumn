package lv.javaguru.java2.oddJobs.core.requests.update;

public class UpdateAdvertisementRequest {
    private Long id;
    private String newAdvTitle;
    private String newAdvDescription;

    public UpdateAdvertisementRequest() {
    }

    public String getNewAdvTitle() {
        return newAdvTitle;
    }

    public String getNewAdvDescription() {
        return newAdvDescription;
    }

    public Long getId() {
        return id;
    }
}
