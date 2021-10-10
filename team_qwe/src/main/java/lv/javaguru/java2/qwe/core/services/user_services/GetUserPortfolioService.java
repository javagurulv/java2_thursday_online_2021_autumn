package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserPortfolioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetUserPortfolioService {

    @Autowired private UserData userData;
    @Autowired private GetUserPortfolioValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public GetUserPortfolioResponse execute(GetUserPortfolioRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            return new GetUserPortfolioResponse(user.get().getPortfolio());
        }
        List<Position> positions = new ArrayList<>();
        return new GetUserPortfolioResponse(errors, positions);
    }

}