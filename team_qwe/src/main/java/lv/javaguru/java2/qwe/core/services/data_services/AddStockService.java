package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AddStockService {

    @Autowired private Database database;
    @Autowired private AddStockValidator validator;

    public AddStockResponse execute(CoreRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Stock stock = createStock((AddStockRequest) request);
            database.addStock(stock);
            return new AddStockResponse(stock);
        }
        return new AddStockResponse(errors);
    }

    private Stock createStock(AddStockRequest request) {
        return new Stock(
                request.getTicker(),
                request.getName(),
                request.getIndustry(),
                request.getCurrency(),
                Double.parseDouble(request.getMarketPrice()),
                Double.parseDouble(request.getDividends()),
                Double.parseDouble(request.getRiskWeight())
        );
    }

}