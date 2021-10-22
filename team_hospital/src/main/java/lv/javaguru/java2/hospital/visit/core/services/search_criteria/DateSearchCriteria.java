package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateSearchCriteria implements VisitsSearchCriteria{

    private final VisitDatabase database;

    public DateSearchCriteria(VisitDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isDateProvided()
                && !request.isDoctorIdProvided()
                && !request.isPatientIdProvided()
                && !request.isVisitIdProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return database.findByDate(LocalDateTime.parse(request.getVisitDate(), formatter));
    }
}
