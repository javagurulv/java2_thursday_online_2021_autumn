package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public class DoctorIdPatientIdSearchCriteria implements VisitsSearchCriteria{

    private final JpaVisitRepository database;

    public DoctorIdPatientIdSearchCriteria(JpaVisitRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isPatientIdProvided()
                && !request.isVisitIdProvided()
                && !request.isDateProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByDoctorIdAndPatientId
                (Long.parseLong(request.getDoctorId()), Long.parseLong(request.getPatientId()));
    }
}
