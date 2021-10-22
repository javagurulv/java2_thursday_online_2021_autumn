package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorIdAndPatientIdAndDateSearchCriteria implements VisitsSearchCriteria{

    private final VisitDatabase database;

    public DoctorIdAndPatientIdAndDateSearchCriteria(VisitDatabase database) {
        this.database = database;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return database.findByDoctorIdAndPatientIdAndDate
                (request.getDoctorId(), request.getPatientId(), LocalDateTime.parse(request.getVisitDate(), formatter));
    }
}
