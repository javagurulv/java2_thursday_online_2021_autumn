package lv.javaguru.java2.hospital.patient.core.responses;

import java.util.List;

public class DeletePatientResponse extends CoreResponse {
    private Long idResponse;

    public DeletePatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeletePatientResponse(Long idResponse) {
        this.idResponse = idResponse;
    }

    public Long getIdResponse() {
        return idResponse;
    }

}
