package lv.javaguru.java2.hospital.patient.requests;

public class DeletePatientRequest {
    private String idRequest;

    public DeletePatientRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    public String getIdRequest() {
        return idRequest;
    }
}
