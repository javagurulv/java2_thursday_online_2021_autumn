package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchUsersRequestValidator {

    @Autowired private SearchUsersRequestFieldValidator fieldValidator;
    @Autowired private OrderingValidator orderingValidator;
    @Autowired private PagingValidator pagingValidator;

    public List<CoreError> validator(SearchUsersRequest request) {
        List<CoreError> errors = fieldValidator.validatorSearchField(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validateOrderingIfPresent(SearchUsersRequest request,
                                           List<CoreError> coreErrors) {
        if (request.getOrdering() != null) {
            Ordering ordering = request.getOrdering();
            coreErrors.addAll(orderingValidator.validator(ordering));
        }
    }

    private void validatePagingIfPresent(SearchUsersRequest request,
                                         List<CoreError> coreErrors) {
        if (request.getPaging() != null) {
            Paging paging = request.getPaging();
            coreErrors.addAll(pagingValidator.validatorPaging(paging));
        }
    }
}
