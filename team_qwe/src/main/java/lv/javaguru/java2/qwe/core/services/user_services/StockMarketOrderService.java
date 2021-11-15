package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.domain.TradeType;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.StockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.services.validator.StockMarketOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class StockMarketOrderService {

    @Autowired private Database database;
    @Autowired private UserData userData;
    @Autowired private StockMarketOrderValidator validator;

    public StockMarketOrderResponse execute(StockMarketOrderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Stock stock = (Stock) request.getSecurity();
            stock.setMarketPrice(request.getRealTimePrice());
            Position position = new Position(stock,
                    Double.parseDouble(request.getQuantity()),
                    request.getRealTimePrice()
            );
            position.setUserId(request.getUser().getId());
            userData.savePosition(position, request.getUser().getId());
            TradeTicket ticket = new TradeTicket(
                    request.getUser(), request.getSecurity(), getTradeType(request),
                    Double.parseDouble(request.getQuantity()), request.getRealTimePrice(),
                    LocalDateTime.now()
            );
            userData.saveTradeTicket(ticket);
            return new StockMarketOrderResponse(position, ticket);
        }
        return new StockMarketOrderResponse(errors);
    }

    private TradeType getTradeType(StockMarketOrderRequest request) {
        return (Double.parseDouble(request.getQuantity()) > 0) ? TradeType.BUY : TradeType.SELL;
    }

}