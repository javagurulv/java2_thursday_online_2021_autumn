package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.Optional;

public interface VisitExistenceBySearchCriteria {

    boolean canValidate(SearchVisitRequest request);

    Optional<CoreError> validateExistence(SearchVisitRequest request);
}
