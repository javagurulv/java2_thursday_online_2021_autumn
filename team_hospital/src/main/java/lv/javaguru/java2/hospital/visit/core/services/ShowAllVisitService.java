package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ShowAllVisitService {

    @Autowired private JpaVisitRepository database;

    public ShowAllVisitResponse execute(ShowAllVisitRequest request) {
        List<Visit> visits = database.findAll();
        return new ShowAllVisitResponse(visits);
    }
}
