package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.user_requests.BuyStockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.BuyStockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.services.validator.BuyStockMarketOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyStockMarketOrderService {

    @Autowired private Database database;
    @Autowired private UserData userData;
    @Autowired private BuyStockMarketOrderValidator validator;

    public BuyStockMarketOrderResponse execute(BuyStockMarketOrderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Stock stock = (Stock) request.getSecurity();
            stock.setMarketPrice(request.getRealTimePrice());
            Position position = new Position(stock,
                    Double.parseDouble(request.getQuantity()),
                    request.getRealTimePrice()
            );
            database.updateStock(stock);
            userData.savePosition(position, request.getUser().getId());
            return new BuyStockMarketOrderResponse(position);
        }
        return new BuyStockMarketOrderResponse(errors);
    }

}