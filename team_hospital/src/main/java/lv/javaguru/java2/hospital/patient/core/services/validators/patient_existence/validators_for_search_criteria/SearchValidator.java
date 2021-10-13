package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.validators_for_search_criteria;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;

import java.util.Optional;

public interface SearchValidator {
    boolean canProcess(SearchPatientsRequest request);

    Optional<CoreError> process(SearchPatientsRequest request);
}
