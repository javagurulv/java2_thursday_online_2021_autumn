package lv.javaguru.java2.hospital.patient.core.requests;

public class DeletePatientRequest {
    private Long idRequest;

    public DeletePatientRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public Long getIdRequest() {
        return idRequest;
    }
}
