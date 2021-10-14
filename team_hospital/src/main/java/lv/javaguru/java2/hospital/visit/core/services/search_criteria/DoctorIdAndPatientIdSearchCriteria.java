package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public class DoctorIdAndPatientIdSearchCriteria implements VisitsSearchCriteria{

    private final VisitDatabase database;

    public DoctorIdAndPatientIdSearchCriteria(VisitDatabase database) {
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
        return database.findByDoctorIdAndPatientId(request.getDoctorId(), request.getPatientId());
    }
}
