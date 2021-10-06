package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;

import java.util.List;

public class ShowAllVisitService {

    private VisitDatabaseImpl database;

    public ShowAllVisitService(VisitDatabaseImpl database) {
        this.database = database;
    }

    public ShowAllVisitResponse execute(ShowAllVisitRequest request) {
        List<Visit> visits = database.showAllVisits();
        return new ShowAllVisitResponse(visits);
    }
}
