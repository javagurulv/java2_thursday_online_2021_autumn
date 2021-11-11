package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class BuyStockMarketOrderResponse extends CoreResponse {

    private Position position;

    public BuyStockMarketOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public BuyStockMarketOrderResponse(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}