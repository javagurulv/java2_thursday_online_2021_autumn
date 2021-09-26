package lv.javaguru.java2.hospital.visits.request;

public class DeletePatientVisitRequest {
    private String id;

    public DeletePatientVisitRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
