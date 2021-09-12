package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.FilterStockByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.FilterStocksByAnyDoubleParameterResponse;
import lv.javaguru.java2.qwe.core.services.validator.FilterStockByAnyDoubleParameterValidator;

import java.util.ArrayList;
import java.util.List;

public class FilterStocksByAnyDoubleParameterService {

    private final Database database;
    private final FilterStockByAnyDoubleParameterValidator validator;

    public FilterStocksByAnyDoubleParameterService(Database database, FilterStockByAnyDoubleParameterValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public Database getDatabase() {
        return database;
    }

    public FilterStocksByAnyDoubleParameterResponse execute(FilterStockByAnyDoubleParameterRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            return new FilterStocksByAnyDoubleParameterResponse(
                    database.filterStocksByAnyDoubleParameter(
                            request.getParameter(),
                            request.getOperator(),
                            Double.parseDouble(request.getTargetAmount())));
        } else {
            List<Security> emptyList = new ArrayList<>();
            return new FilterStocksByAnyDoubleParameterResponse(errors, emptyList);
        }
    }

}