package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchVisitValidator {

    @Autowired private SearchVisitFieldValidator fieldValidator;
    @Autowired private VisitOrderingValidator visitOrderingValidator;
    @Autowired private VisitPagingValidator visitPagingValidator;

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
