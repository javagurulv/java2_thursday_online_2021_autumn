package lv.javaguru.java2.hospital.patient.core.requests;

public class FindPatientByIdRequest {
    private Long IDRequest;

    public FindPatientByIdRequest(Long idRequest) {
        this.IDRequest = idRequest;
    }

    public Long getIDRequest() {
        return IDRequest;
    }
}
