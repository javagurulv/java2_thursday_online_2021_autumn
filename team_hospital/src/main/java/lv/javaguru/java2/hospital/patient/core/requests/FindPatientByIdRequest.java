package lv.javaguru.java2.hospital.patient.core.requests;

public class FindPatientByIdRequest {
    private String  IDRequest;

    public FindPatientByIdRequest(String IDRequest) {
        this.IDRequest = IDRequest;
    }

    public String getIDRequest() {
        return IDRequest;
    }
}
