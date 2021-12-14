package lv.javaguru.java2.oddJobs.core.requests.remove;

public class DeleteClientRequest {
    private Long id;

    public DeleteClientRequest() {
    }

    public DeleteClientRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
