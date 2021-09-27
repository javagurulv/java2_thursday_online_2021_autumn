package lv.javaguru.java2.hospital.visits.core.responses;

import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.util.List;

public class ShowAllPatientVisitResponse {

    private List<PatientVisit> patientVisits;

    public ShowAllPatientVisitResponse(List<PatientVisit> patientVisits) {
        this.patientVisits = patientVisits;
    }

    public List<PatientVisit> getPatientVisits() {
        return patientVisits;
    }
}
