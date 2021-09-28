package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class AddStockService {

    @DIDependency private Database database;
    @DIDependency private AddStockValidator validator;

    public AddStockResponse execute(CoreRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Stock stock = addStock((AddStockRequest) request);
            database.addStock(stock);
            return new AddStockResponse(stock);
        }
        return new AddStockResponse(errors);
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