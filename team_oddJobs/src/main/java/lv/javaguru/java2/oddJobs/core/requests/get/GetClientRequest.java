package lv.javaguru.java2.oddJobs.core.requests.get;

public class GetClientRequest {

    private Long id;

    public GetClientRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
