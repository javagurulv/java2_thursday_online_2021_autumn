package lv.javaguru.java2.oddJobs.core.requests.remove;

public class DeleteSpecialistRequest {
    private Long id;

    public DeleteSpecialistRequest() { }

    public DeleteSpecialistRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
