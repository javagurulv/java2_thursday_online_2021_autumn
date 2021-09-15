package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserPortfolioValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetUserPortfolioService {

    private final UserData userData;
    private final GetUserPortfolioValidator validator;

    public GetUserPortfolioService(UserData userData, GetUserPortfolioValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

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