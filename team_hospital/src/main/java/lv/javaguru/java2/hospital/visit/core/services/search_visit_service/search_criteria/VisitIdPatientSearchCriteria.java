package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public class VisitIdPatientSearchCriteria implements VisitsSearchCriteria {

    private final JpaVisitRepository database;

    public VisitIdPatientSearchCriteria(JpaVisitRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && request.isPatientIdProvided()
                && !request.isDoctorIdProvided()
                && !request.isDateProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByIdAndPatientId(Long.parseLong(request.getVisitId()), Long.parseLong(request.getPatientId()));
    }
}
