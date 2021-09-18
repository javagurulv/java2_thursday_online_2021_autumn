package lv.javaguru.java2.hospital.patient.services.validators;
import lv.javaguru.java2.hospital.patient.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientsValidator {

    private final OrderingValidator orderingValidator;
    private final PagingValidator pagingValidator;

    public SearchPatientsValidator(OrderingValidator orderingValidator, PagingValidator pagingValidator) {
        this.orderingValidator = orderingValidator;
        this.pagingValidator = pagingValidator;
    }

    public List<CoreError> validate(SearchPatientsRequest request) {
        List<CoreError> errors = validateSearchFields(request);
        validatePagingIfNeeded(request, errors);
        validateOrderingIfNeeded(request, errors);
        return errors;
    }

    private void validatePagingIfNeeded(SearchPatientsRequest request, List<CoreError> errors) {
        if (request != null) {
            List<CoreError> pagingErrors = pagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfNeeded(SearchPatientsRequest request, List<CoreError> errors) {
        if (request != null) {
            List<CoreError> pagingErrors = orderingValidator.validate(request.getOrdering());
            errors.addAll(pagingErrors);
        }
    }

    private List<CoreError> validateSearchFields(SearchPatientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getSurname())
                && isEmpty(request.getPersonalCode())) {
            errors.add(new CoreError("Name", "must not be empty!"));
            errors.add(new CoreError("Surname", "must not be empty!"));
            errors.add(new CoreError("Personal code", "must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
