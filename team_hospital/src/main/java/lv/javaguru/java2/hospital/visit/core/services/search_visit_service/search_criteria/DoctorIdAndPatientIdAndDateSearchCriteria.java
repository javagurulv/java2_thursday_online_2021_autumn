package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;

import java.util.List;

public class DoctorIdAndPatientIdAndDateSearchCriteria implements VisitsSearchCriteria{

    private final VisitRepository database;
    private final GetVisitDate getVisitDate;

    public DoctorIdAndPatientIdAndDateSearchCriteria(VisitRepository database, GetVisitDate getVisitDate) {
        this.database = database;
        this.getVisitDate = getVisitDate;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isPatientIdProvided()
                && request.isDateProvided()
                && !request.isVisitIdProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByDoctorIdAndPatientIdAndDate
                (Long.parseLong(request.getDoctorId()), Long.valueOf(request.getPatientId()),
                        getVisitDate.getVisitDateFromString(request.getVisitDate()));
    }
}
