package lv.javaguru.java2.hospital.visits.core.request;

public class DeleteVisitRequest {
    private Long id;

    public DeleteVisitRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
