package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public class IdSearchCriteria implements VisitsSearchCriteria{

    private final VisitDatabase database;

    public IdSearchCriteria(VisitDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isVisitIdProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByVisitId(request.getVisitId());
    }
}
