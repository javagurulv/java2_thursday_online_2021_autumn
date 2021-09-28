package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.validator.FilterStocksByMultipleParametersValidator;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class FilterStocksByMultipleParametersService {

    @DIDependency private Database database;
    @DIDependency private FilterStocksByMultipleParametersValidator validator;

    public Database getDatabase() {
        return database;
    }

    public FilterStocksByMultipleParametersResponse execute(FilterStocksByMultipleParametersRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            return new FilterStocksByMultipleParametersResponse(
                    database.filterStocksByMultipleParameters(database.getSecurityList(), request, 0));
        }
        List<Security> emptyList = new ArrayList<>();
        return new FilterStocksByMultipleParametersResponse(errors, emptyList);
    }

}