package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchVisitorsRequestValidator {

    private SearchVisitorsRequestFieldValidator fieldValidator;
    private OrderingValidator orderingValidator;
    private PagingValidator pagingValidator;

    public SearchVisitorsRequestValidator(SearchVisitorsRequestFieldValidator fieldValidator,
                                          OrderingValidator orderingValidator,
                                          PagingValidator pagingValidator) {
        this.fieldValidator = fieldValidator;
        this.orderingValidator = orderingValidator;
        this.pagingValidator = pagingValidator;
    }

    public List<CoreError> validator(SearchVisitorsRequest request) {
        List<CoreError> errors = fieldValidator.validatorSearchField(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validateOrderingIfPresent(SearchVisitorsRequest request, List<CoreError> coreErrors) {
        if (request.getOrdering() != null) {
            Ordering ordering = request.getOrdering();
            coreErrors.addAll(orderingValidator.validator(ordering));
        }
    }

    private void validatePagingIfPresent(SearchVisitorsRequest request, List<CoreError> coreErrors) {
        if (request.getPaging() != null) {
            Paging paging = request.getPaging();
            coreErrors.addAll(pagingValidator.validatorPaging(paging));
        }
    }
}
