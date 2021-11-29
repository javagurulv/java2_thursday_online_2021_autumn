package lv.javaguru.java2.hospital.patient.core.requests;

public class FindPatientByIdRequest {
    private String  IDRequest;

    public FindPatientByIdRequest() {
    }

    public FindPatientByIdRequest(String IDRequest) {
        this.IDRequest = IDRequest;
    }

    public String getIDRequest() {
        return IDRequest;
    }

    public void setIDRequest(String IDRequest) {
        this.IDRequest = IDRequest;
    }
}
