package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceForSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchVisitValidator {

    @Autowired private SearchVisitFieldValidator fieldValidator;
    @Autowired private VisitOrderingValidator visitOrderingValidator;
    @Autowired private VisitPagingValidator visitPagingValidator;
    @Autowired private VisitExistenceForSearchValidator visitExistenceForSearchValidator;

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = fieldValidator.validate(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        validateVisitExistence(request, errors);
        return errors;
    }

    private void validatePagingIfPresent(SearchVisitRequest request, List<CoreError> errors) {
        if (request.getVisitPaging() != null) {
            List<CoreError> pagingErrors = visitPagingValidator.validate(request.getVisitPaging());
            errors.addAll(pagingErrors);
        }
    }

    private void validateOrderingIfPresent(SearchVisitRequest request, List<CoreError> errors) {
        if (request.getVisitOrdering() != null) {
            List<CoreError> orderingErrors = visitOrderingValidator.validate(request.getVisitOrdering());
            errors.addAll(orderingErrors);
        }
    }

    private void validateVisitExistence(SearchVisitRequest request, List<CoreError> errors) {
        List<CoreError> existenceErrors = visitExistenceForSearchValidator.validate(request);
        errors.addAll(existenceErrors);
    }
}
