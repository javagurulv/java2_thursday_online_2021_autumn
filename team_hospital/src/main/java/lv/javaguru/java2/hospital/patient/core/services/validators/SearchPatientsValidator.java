package lv.javaguru.java2.hospital.patient.core.services.validators;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import java.util.List;

public class SearchPatientsValidator {

    private final SearchPatientsRequestFieldValidator fieldValidator;
    private final OrderingValidator orderingValidator;
    private final PagingValidator pagingValidator;

    public SearchPatientsValidator(SearchPatientsRequestFieldValidator fieldValidator,
                                   OrderingValidator orderingValidator,
                                   PagingValidator pagingValidator) {
        this.fieldValidator = fieldValidator;
        this.orderingValidator = orderingValidator;
        this.pagingValidator = pagingValidator;
    }

    public List<CoreError> validate(SearchPatientsRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
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
            List<CoreError> orderingErrors = orderingValidator.validate(request.getOrdering());
            errors.addAll(orderingErrors);
        }
    }
}
