package lv.javaguru.java2.hospital.patient.responses;

import java.util.List;

public class DeletePatientResponse extends CoreResponse {
    private String idResponse;

    public DeletePatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeletePatientResponse(String idResponse) {
        this.idResponse = idResponse;
    }

    public String getIdResponse() {
        return idResponse;
    }
}
