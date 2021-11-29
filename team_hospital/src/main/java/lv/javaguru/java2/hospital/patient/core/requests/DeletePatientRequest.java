package lv.javaguru.java2.hospital.patient.core.requests;

public class DeletePatientRequest {

    private String idRequest;

    public DeletePatientRequest() {
    }

    public DeletePatientRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    public String getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }
}
