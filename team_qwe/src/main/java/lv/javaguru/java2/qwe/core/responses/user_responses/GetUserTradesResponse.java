package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class GetUserTradesResponse extends CoreResponse {

    private final User user;
    private final List<TradeTicket> trades;

    public GetUserTradesResponse(User user, List<TradeTicket> trades) {
        this.user = user;
        this.trades = trades;
    }

    public GetUserTradesResponse(List<CoreError> errors, User user, List<TradeTicket> trades) {
        super(errors);
        this.user = user;
        this.trades = trades;
    }

    public User getUser() {
        return user;
    }

    public List<TradeTicket> getTrades() {
        return trades;
    }

}