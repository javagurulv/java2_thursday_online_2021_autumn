package lv.javaguru.java2.oddJobs.core.requests.get;

public class GetAdvertisementRequest {

    private Long id;

    public GetAdvertisementRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
