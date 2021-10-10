package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidatorSearchRequestTable {

    @Autowired
    private SearchRequestFieldTableValidator fieldTable;
    @Autowired private ValidatorOrdering validatorOrdering;
    @Autowired private ValidatorPaging validatorPaging;

    public List<CoreError> validator(SearchTableRequest request) {
        List<CoreError> coreErrors = fieldTable.validatorField(request);
        validateOrderingIfPresent(request, coreErrors);
        validatePagingIfPresent(request, coreErrors);
        return coreErrors;
    }

    private void validateOrderingIfPresent(SearchTableRequest request,
                                           List<CoreError> errorList) {
        if (request.getOrdering() != null) {
            OrderingTable orderingTable = request.getOrdering();
            errorList.addAll(validatorOrdering.validator(orderingTable));
        }
    }

    private void validatePagingIfPresent(SearchTableRequest request,
                                         List<CoreError> errorList) {
        if (request.getPaging() != null) {
            PagingTable pagingTable = request.getPaging();
            errorList.addAll(validatorPaging.validatorPaging(pagingTable));
        }
    }
}
