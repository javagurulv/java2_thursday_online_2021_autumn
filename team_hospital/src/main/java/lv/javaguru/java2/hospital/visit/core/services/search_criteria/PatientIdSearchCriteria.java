package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public class PatientIdSearchCriteria implements VisitsSearchCriteria{

    private final VisitRepository database;

    public PatientIdSearchCriteria(VisitRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isPatientIdProvided()
                && !request.isVisitIdProvided()
                && !request.isDoctorIdProvided()
                && !request.isDateProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByPatientId(Long.valueOf(request.getPatientId()));
    }
}
