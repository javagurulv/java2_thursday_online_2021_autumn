package lv.javaguru.java2.hospital.patient.requests;

public class FindPatientByIdRequest {
    private String idRequest;

    public FindPatientByIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    public String getIdRequest() {
        return idRequest;
    }
}
