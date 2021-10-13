package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceForSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchPatientsValidator {

    @Autowired private SearchPatientsRequestFieldValidator fieldValidator;
    @Autowired private PatientOrderingValidator orderingValidator;
    @Autowired private PatientPagingValidator pagingValidator;
    @Autowired private PatientExistenceForSearchValidator existenceSearchValidator;

    public List<CoreError> validate(SearchPatientsRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validatePagingIfNeeded(request, errors);
        validateOrderingIfNeeded(request, errors);
        validateFieldsForPatientExistence(request, errors);
        return errors;
    }

    private void validatePagingIfNeeded(SearchPatientsRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            List<CoreError> pagingErrors = pagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfNeeded(SearchPatientsRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            List<CoreError> orderingErrors = orderingValidator.validate(request.getOrdering());
            errors.addAll(orderingErrors);
        }
    }

    private void validateFieldsForPatientExistence(SearchPatientsRequest request, List<CoreError> errors){
        List<CoreError> validateErrors = existenceSearchValidator.execute(request);
        errors.addAll(validateErrors);
    }
}
