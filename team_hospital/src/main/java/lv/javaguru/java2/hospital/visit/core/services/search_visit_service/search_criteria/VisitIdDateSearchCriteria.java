package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;

import java.util.List;

public class VisitIdDateSearchCriteria implements VisitsSearchCriteria {

    private final VisitRepository database;
    private final GetVisitDate getVisitDate;

    public VisitIdDateSearchCriteria(VisitRepository database, GetVisitDate getVisitDate) {
        this.database = database;
        this.getVisitDate = getVisitDate;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && request.isDateProvided()
                && !request.isPatientIdProvided()
                && !request.isDoctorIdProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByVisitIDAndDate(Long.valueOf(request.getVisitId()),
                getVisitDate.getVisitDateFromString(request.getVisitDate()));
    }
}
