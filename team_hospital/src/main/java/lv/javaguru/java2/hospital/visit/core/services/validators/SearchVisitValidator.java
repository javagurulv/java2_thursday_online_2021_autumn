package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public class SearchVisitValidator {

    private SearchVisitFieldValidator fieldValidator;
    private VisitOrderingValidator visitOrderingValidator;
    private VisitPagingValidator visitPagingValidator;

    public SearchVisitValidator(SearchVisitFieldValidator fieldValidator, VisitOrderingValidator visitOrderingValidator, VisitPagingValidator visitPagingValidator) {
        this.fieldValidator = fieldValidator;
        this.visitOrderingValidator = visitOrderingValidator;
        this.visitPagingValidator = visitPagingValidator;
    }

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validatePagingIfPresent(SearchVisitRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            List<CoreError> pagingErrors = visitPagingValidator.validate(request.getPaging());
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfPresent(SearchVisitRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            List<CoreError> orderingErrors = visitOrderingValidator.validate(request.getOrdering());
            errors.addAll(orderingErrors);
        }
    }
}
