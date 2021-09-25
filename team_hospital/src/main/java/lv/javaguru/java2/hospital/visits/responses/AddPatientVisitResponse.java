package lv.javaguru.java2.hospital.visits.responses;

import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.util.List;

public class AddPatientVisitResponse extends CoreResponse {
    private PatientVisit patientVisit;

    public AddPatientVisitResponse(List<CoreError> errors, PatientVisit patientVisit) {
        super(errors);
        this.patientVisit = patientVisit;
    }

    public PatientVisit getPatientVisit() {
        return patientVisit;
    }
}
