package lv.javaguru.java2.hospital.visits.responses;

import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.util.List;

public class PatientVisitResponse extends CoreResponse {
    private PatientVisit patientVisit;

    public PatientVisitResponse(List<CoreError> errors, PatientVisit patientVisit) {
        super(errors);
        this.patientVisit = patientVisit;
    }

    public PatientVisit getPatientVisit() {
        return patientVisit;
    }
}
