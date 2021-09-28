package lv.javaguru.java2.hospital.visits.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.PatientVisit;
import lv.javaguru.java2.hospital.visits.core.request.ShowAllPatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.ShowAllPatientVisitResponse;

import java.util.List;

public class ShowAllPatientVisitService {

    private VisitDatabaseImpl database;

    public ShowAllPatientVisitService(VisitDatabaseImpl database) {
        this.database = database;
    }

    public ShowAllPatientVisitResponse execute(ShowAllPatientVisitRequest request) {
        List<PatientVisit> patientVisits = database.showAllPatientVisits();
        return new ShowAllPatientVisitResponse(patientVisits);
    }
}
