package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitSearchExecute {

    @Autowired private VisitDatabase visitDatabase;

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
                new VisitIdSearchCriteria(visitDatabase),
                new VisitIDAndPatientSearchCriteria(visitDatabase),
                new VisitIDAndDoctorIDSearchCriteria(visitDatabase),
                new VisitIDAndPatientIDAndDoctorIDSearchCriteria(visitDatabase),
                new VisitIDPatientIDDoctorIDDateSearchCriteria(visitDatabase),
                new DoctorIdAndPatientIdAndDateSearchCriteria(visitDatabase),
                new DoctorIdAndPatientIdSearchCriteria(visitDatabase),
                new DoctorIdAndDateSearchCriteria(visitDatabase),
                new PatientIdAndDateSearchCriteria(visitDatabase),
                new DoctorIdSearchCriteria(visitDatabase),
                new PatientIdSearchCriteria(visitDatabase),
                new DateSearchCriteria(visitDatabase)};
    }
}