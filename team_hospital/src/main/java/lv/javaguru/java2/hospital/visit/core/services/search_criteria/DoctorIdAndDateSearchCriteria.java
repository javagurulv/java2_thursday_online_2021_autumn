package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return database.findByDoctorIdAndDate(request.getDoctorId(), LocalDateTime.parse(request.getVisitDate(), formatter));
    }
}
