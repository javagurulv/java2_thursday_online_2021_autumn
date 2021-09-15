package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.SecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;

import java.util.List;

public class AddStockService {

    private final Database database;
    private final AddStockValidator validator;

    public AddStockService(Database database, AddStockValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddStockResponse execute(SecurityRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Stock stock = addStock((AddStockRequest) request);
            database.addStock(stock);
            return new AddStockResponse(stock);
        } else {
            return new AddStockResponse(errors);
        }
    }

    private Stock addStock(AddStockRequest request) {
        return new Stock(
                request.getName(),
                request.getIndustry(),
                request.getCurrency(),
                Double.parseDouble(request.getMarketPrice()),
                Double.parseDouble(request.getDividends()),
                Double.parseDouble(request.getRiskWeight())
        );
    }

}