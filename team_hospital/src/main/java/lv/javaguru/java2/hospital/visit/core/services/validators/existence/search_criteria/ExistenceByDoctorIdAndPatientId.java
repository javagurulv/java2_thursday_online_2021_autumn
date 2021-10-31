package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceByDoctorIdAndPatientId implements VisitExistenceBySearchCriteria{

    @Autowired
    private VisitDatabase database;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isPatientIdProvided()
                && !request.isVisitIdProvided()
                && !request.isDateProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.getAllVisits()) {
            if (visit.getDoctorID().equals(request.getDoctorId())
                    && visit.getPatientID().equals(request.getPatientId())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "Does not exist!"));
    }
}
