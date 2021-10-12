package lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import java.util.Optional;

public interface DoctorExistenceBySearchCriteria {
    boolean canValidate(SearchDoctorsRequest request);

    Optional<CoreError> validateExistence(SearchDoctorsRequest request);
}
