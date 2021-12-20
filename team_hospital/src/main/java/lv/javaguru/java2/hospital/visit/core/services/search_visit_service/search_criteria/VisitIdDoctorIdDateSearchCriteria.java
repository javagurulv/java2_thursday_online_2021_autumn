package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;

import java.util.List;

public class VisitIdDoctorIdDateSearchCriteria implements VisitsSearchCriteria {

    private final JpaVisitRepository database;
    private final GetVisitDate getVisitDate;

    public VisitIdDoctorIdDateSearchCriteria(JpaVisitRepository database, GetVisitDate getVisitDate) {
        this.database = database;
        this.getVisitDate = getVisitDate;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && request.isDoctorIdProvided()
                && request.isDateProvided()
                && !request.isPatientIdProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByVisitIdDoctorIdDate(Long.parseLong(request.getVisitId()),
                Long.parseLong(request.getDoctorId()),
                getVisitDate.getVisitDateFromString(request.getVisitDate()));
    }
}
