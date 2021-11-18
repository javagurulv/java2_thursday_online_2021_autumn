package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.search_criteria_validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.Optional;

public interface VisitExistenceBySearchCriteria {

    boolean canValidate(SearchVisitRequest request);

    Optional<CoreError> validateExistence(SearchVisitRequest request);
}
