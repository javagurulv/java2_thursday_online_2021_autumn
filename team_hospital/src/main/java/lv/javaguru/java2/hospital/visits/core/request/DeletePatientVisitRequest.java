package lv.javaguru.java2.hospital.visits.core.request;

public class DeletePatientVisitRequest {
    private Long id;

    public DeletePatientVisitRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
