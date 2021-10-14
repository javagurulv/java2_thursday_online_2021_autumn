package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.Date;
import java.util.List;

public class DoctorIdAndDateSearchCriteria implements VisitsSearchCriteria{

    private final VisitDatabase database;

    public DoctorIdAndDateSearchCriteria(VisitDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isDateProvided()
                && !request.isVisitIdProvided()
                && !request.isPatientIdProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        return database.findByDoctorIdAndDate(request.getDoctorId(), getVisitDate(request));
    }

    private Date getVisitDate(SearchVisitRequest request) {
        GetVisitDate getVisitDate = new GetVisitDate();
        return getVisitDate.getVisitDateFromString(request.getVisitDate());
    }
}
