package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllVisitService {

    @Autowired private VisitDatabaseImpl database;

    public ShowAllVisitResponse execute(ShowAllVisitRequest request) {
        List<Visit> visits = database.showAllVisits();
        return new ShowAllVisitResponse(visits);
    }
}
