package lv.javaguru.java2.hospital.patient.core.responses;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;

public class FindPatientByIDResponse extends CoreResponse {
    private Long idResponse;
    private List<Patient> patient;

    public FindPatientByIDResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindPatientByIDResponse(Long idResponse, List<Patient> patient) {
        this.idResponse = idResponse;
        this.patient = patient;
    }

    public Long getIdResponse() {
        return idResponse;
    }

    public List<Patient> getPatient() {
        return patient;
    }
}
