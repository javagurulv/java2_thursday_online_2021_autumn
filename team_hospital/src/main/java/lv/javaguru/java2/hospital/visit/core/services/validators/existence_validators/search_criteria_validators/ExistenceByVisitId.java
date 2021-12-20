package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.search_criteria_validators;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceByVisitId implements VisitExistenceBySearchCriteria {

    @Autowired
    private JpaVisitRepository database;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && !request.isPatientIdProvided()
                && !request.isDoctorIdProvided()
                && !request.isDateProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.findAll()) {
            if (visit.getVisitID().equals(Long.parseLong(request.getVisitId()))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "does not exist!"));
    }
}
