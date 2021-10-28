package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllVisitService {

    @Autowired private VisitDatabase database;

    public ShowAllVisitResponse execute(ShowAllVisitRequest request) {
        List<Visit> visits = database.getAllVisits();
        return new ShowAllVisitResponse(visits);
    }
}
