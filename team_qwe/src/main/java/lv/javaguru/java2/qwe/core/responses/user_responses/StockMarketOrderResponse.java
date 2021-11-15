package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class StockMarketOrderResponse extends CoreResponse {

    private Position position;

    private TradeTicket ticket;

    public StockMarketOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public StockMarketOrderResponse(Position position, TradeTicket ticket) {
        this.position = position;
        this.ticket = ticket;
    }

    public Position getPosition() {
        return position;
    }

    public TradeTicket getTicket() {
        return ticket;
    }

}