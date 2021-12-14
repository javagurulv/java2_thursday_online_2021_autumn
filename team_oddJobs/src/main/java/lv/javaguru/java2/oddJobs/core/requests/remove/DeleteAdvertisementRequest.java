package lv.javaguru.java2.oddJobs.core.requests.remove;

public class DeleteAdvertisementRequest {
    private Long id;

    public DeleteAdvertisementRequest() {
    }

    public DeleteAdvertisementRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
