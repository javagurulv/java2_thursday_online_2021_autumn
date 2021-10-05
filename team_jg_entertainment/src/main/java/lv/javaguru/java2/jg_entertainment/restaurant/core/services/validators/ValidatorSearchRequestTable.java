package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class ValidatorSearchRequestTable {

    @DIDependency private ValidatorSearchRequestFieldTable fieldTable;
    @DIDependency private ValidatorOrdering validatorOrdering;
    @DIDependency private ValidatorPaging validatorPaging;

//    public ValidatorSearchRequestTable(ValidatorSearchRequestFieldTable fieldTable,
//                                       ValidatorOrdering validatorOrdering,
//                                       ValidatorPaging validatorPaging) {
//        this.fieldTable = fieldTable;
//        this.validatorOrdering = validatorOrdering;
//        this.validatorPaging = validatorPaging;
//    }

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
