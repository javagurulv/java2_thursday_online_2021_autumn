package lv.javaguru.java2.oddJobs.core.requests.get;

public class GetSpecialistRequest {
    private Long id;

    public GetSpecialistRequest(Long id) {
        this.id = id;
    }

    public GetSpecialistRequest() {
    }

    public Long getId() {
        return id;
    }
}

