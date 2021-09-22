package lv.javaguru.java2.hospital.patient.core.responses;

import java.util.List;

public class DeletePatientResponse extends CoreResponse {
    private String idResponse;
    private boolean isPatientDeleted;

    public DeletePatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeletePatientResponse(String idResponse, boolean isPatientDeleted) {
        this.idResponse = idResponse;
        this.isPatientDeleted = isPatientDeleted;
    }

    public String getIdResponse() {
        return idResponse;
    }

    public boolean isPatientDeleted() {
        return isPatientDeleted;
    }
}
