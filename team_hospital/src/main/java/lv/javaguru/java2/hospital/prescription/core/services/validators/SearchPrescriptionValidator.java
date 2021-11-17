package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.PrescriptionExistenceForSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchPrescriptionValidator {

    @Autowired private SearchPrescriptionFieldValidator fieldValidator;
    @Autowired private PrescriptionOrderingValidator prescriptionOrderingValidator;
    @Autowired private PrescriptionPagingValidator prescriptionPagingValidator;
    @Autowired private PrescriptionExistenceForSearchValidator prescriptionExistenceForSearchValidator;

    public List<CoreError> validate(SearchPrescriptionRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        validatePrescriptionExistence(request, errors);
        return errors;
    }

    private void validatePagingIfPresent(SearchPrescriptionRequest request, List<CoreError> errors) {
        if(request.getPrescriptionPaging() != null) {
            List<CoreError> pagingErrors = prescriptionPagingValidator.validate(request.getPrescriptionPaging());
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfPresent(SearchPrescriptionRequest request, List<CoreError> errors) {
        if (request.getPrescriptionOrdering() != null) {
            List<CoreError> orderingErrors = prescriptionOrderingValidator.validate(request.getPrescriptionOrdering());
            errors.addAll(orderingErrors);
        }
    }

    private void validatePrescriptionExistence(SearchPrescriptionRequest request, List<CoreError> errors) {
        List<CoreError> existenceErrors = prescriptionExistenceForSearchValidator.validate(request);
        errors.addAll(existenceErrors);
    }

}
