package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class StockMarketOrderResponse extends CoreResponse {

    private Position position;

    public StockMarketOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public StockMarketOrderResponse(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}