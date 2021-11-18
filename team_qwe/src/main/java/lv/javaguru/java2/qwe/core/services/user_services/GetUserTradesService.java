package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.TradeTicket;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserTradesRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserTradesResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserTradesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GetUserTradesService {

    @Autowired private UserData userData;
    @Autowired private GetUserTradesValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public GetUserTradesResponse execute(GetUserTradesRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByIdOrName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            User user1 = user.get();
            List<TradeTicket> trades = userData.getUserTrades(user1.getId());
            return new GetUserTradesResponse(user.get(), trades);
        }
        return new GetUserTradesResponse(errors, null, null);
    }

}