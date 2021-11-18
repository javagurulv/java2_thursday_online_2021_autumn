package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitSearchExecute {

    @Autowired private GetVisitDate getVisitDate;
    @Autowired private VisitRepository visitRepository;

    public List<Visit> execute(SearchVisitRequest request) {
        List<Visit> visits = new ArrayList<>();

        VisitsSearchCriteria[] visitsSearchCriteria = getVisitsSearchCriteria();

        for (VisitsSearchCriteria processor : visitsSearchCriteria) {
            if (processor.canProcess(request)) {
                visits = processor.process(request);
                break;
            }
        }

        return visits;
    }

    private VisitsSearchCriteria[] getVisitsSearchCriteria() {
        return new VisitsSearchCriteria[]{
                new VisitIdSearchCriteria(visitRepository),
                new VisitIdPatientSearchCriteria(visitRepository),
                new VisitIdDoctorIdSearchCriteria(visitRepository),
                new VisitIdDoctorIdDateSearchCriteria(visitRepository, getVisitDate),
                new VisitIdPatientIdDateSearchCriteria(visitRepository, getVisitDate),
                new VisitIdDoctorIdPatientIdSearchCriteria(visitRepository),
                new FourFieldsSearchCriteria(visitRepository, getVisitDate),
                new VisitIdDateSearchCriteria(visitRepository, getVisitDate),
                new DoctorIdPatientIdDateSearchCriteria(visitRepository, getVisitDate),
                new DoctorIdPatientIdSearchCriteria(visitRepository),
                new DoctorIdDateSearchCriteria(visitRepository, getVisitDate),
                new PatientIdDateSearchCriteria(visitRepository, getVisitDate),
                new DoctorIdSearchCriteria(visitRepository),
                new PatientIdSearchCriteria(visitRepository),
                new DateSearchCriteria(visitRepository, getVisitDate)};
    }
}