package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorIdAndPatientIdAndDateSearchCriteria implements VisitsSearchCriteria{

    private final VisitRepository database;

    public DoctorIdAndPatientIdAndDateSearchCriteria(VisitRepository database) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return database.findByDoctorIdAndPatientIdAndDate
                (Long.parseLong(request.getDoctorId()), Long.valueOf(request.getPatientId()),
                        LocalDateTime.parse(request.getVisitDate(), formatter));
    }
}
