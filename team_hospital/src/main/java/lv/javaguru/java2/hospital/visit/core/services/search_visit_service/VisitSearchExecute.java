package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitSearchExecute {

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
                new VisitIDAndPatientSearchCriteria(visitRepository),
                new VisitIDAndDoctorIDSearchCriteria(visitRepository),
                new VisitIDAndPatientIDAndDoctorIDSearchCriteria(visitRepository),
                new VisitIDPatientIDDoctorIDDateSearchCriteria(visitRepository),
                //new VisitIDAndDateSearchCriteria(visitRepository),
                new DoctorIdAndPatientIdAndDateSearchCriteria(visitRepository),
                new DoctorIdAndPatientIdSearchCriteria(visitRepository),
                new DoctorIdAndDateSearchCriteria(visitRepository),
                new PatientIdAndDateSearchCriteria(visitRepository),
                new DoctorIdSearchCriteria(visitRepository),
                new PatientIdSearchCriteria(visitRepository),
                new DateSearchCriteria(visitRepository)};
    }
}