package lv.javaguru.java2.hospital.patient.core.responses;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;

public class AddPatientResponse extends CoreResponse{
    private Patient patient;

    public AddPatientResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddPatientResponse(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}
