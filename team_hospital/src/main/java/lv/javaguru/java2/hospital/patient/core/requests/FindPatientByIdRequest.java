package lv.javaguru.java2.hospital.patient.core.requests;

public class FindPatientByIdRequest {
    private Long idRequest;

    public FindPatientByIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public Long getIdRequest() {
        return idRequest;
    }
}
