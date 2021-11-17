package lv.javaguru.java2.hospital.prescription.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;

import java.util.Optional;

public interface PrescriptionExistenceBySearchCriteria {

    boolean canValidate(SearchPrescriptionRequest request);

    Optional<CoreError> validateExistence(SearchPrescriptionRequest request);
}
