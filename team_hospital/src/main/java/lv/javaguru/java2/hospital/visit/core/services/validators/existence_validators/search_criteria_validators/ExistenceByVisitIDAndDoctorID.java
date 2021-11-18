package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.search_criteria_validators;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceByVisitIDAndDoctorID implements VisitExistenceBySearchCriteria {

    @Autowired private VisitRepository database;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && request.isDoctorIdProvided()
                && !request.isPatientIdProvided()
                && !request.isDateProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.getAllVisits()) {
            if (visit.getVisitID().equals(Long.parseLong(request.getVisitId())) &&
                    visit.getDoctor().getId().equals(Long.parseLong(request.getDoctorId()))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "does not exist!"));
    }
}
