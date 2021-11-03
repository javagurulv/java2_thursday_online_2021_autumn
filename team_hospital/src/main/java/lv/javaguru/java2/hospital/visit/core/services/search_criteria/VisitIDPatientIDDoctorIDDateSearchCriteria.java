package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VisitIDPatientIDDoctorIDDateSearchCriteria implements VisitsSearchCriteria {

    private final VisitDatabase database;

    public VisitIDPatientIDDoctorIDDateSearchCriteria(VisitDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && request.isDoctorIdProvided()
                && request.isPatientIdProvided()
                && request.isDateProvided();
    }

    @Override
    public List<Visit> process(SearchVisitRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return database.findByVisitIDDoctorIDPatientIDDate
                (request.getVisitId(), request.getDoctorId(),
                        request.getPatientId(), LocalDateTime.parse(request.getVisitDate(), formatter));
    }
}
