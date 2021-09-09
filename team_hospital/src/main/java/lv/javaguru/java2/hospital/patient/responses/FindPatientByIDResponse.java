package lv.javaguru.java2.hospital.patient.responses;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;
import java.util.Optional;

public class FindPatientByIDResponse extends CoreResponse {
    private String idResponse;
    private Optional <Patient> patient;

    public FindPatientByIDResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindPatientByIDResponse(String idResponse, Optional<Patient> patient) {
        this.idResponse = idResponse;
        this.patient = patient;
    }

    public String getIdResponse() {
        return idResponse;
    }

    public Optional<Patient> getPatient() {
        return patient;
    }
}
