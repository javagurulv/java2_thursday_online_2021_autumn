package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStockByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStockByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.validator.FilterStockByMultipleParametersValidator;

import java.util.ArrayList;
import java.util.List;

public class FilterStocksByMultipleParametersService {

    private final Database database;
    private final FilterStockByMultipleParametersValidator validator;

    public FilterStocksByMultipleParametersService(Database database, FilterStockByMultipleParametersValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public Database getDatabase() {
        return database;
    }

    public FilterStockByMultipleParametersResponse execute(FilterStockByMultipleParametersRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            return new FilterStockByMultipleParametersResponse(
                    database.filterStocksByMultipleParameters(database.getSecurityList(), request, 0)
            );
        } else {
            List<Security> emptyList = new ArrayList<>();
            return new FilterStockByMultipleParametersResponse(errors, emptyList);
        }
    }

}