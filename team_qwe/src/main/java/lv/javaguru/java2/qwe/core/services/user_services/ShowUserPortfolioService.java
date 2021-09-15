package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.ShowUserPortfolioValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowUserPortfolioService {

    private final UserData userData;
    private final ShowUserPortfolioValidator validator;

    public ShowUserPortfolioService(UserData userData, ShowUserPortfolioValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public ShowUserPortfolioResponse execute(ShowUserPortfolioRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Optional<User> user = userData.findUserByName(request.getUserName());
            return new ShowUserPortfolioResponse(user.orElseThrow(RuntimeException::new).getPortfolio());
        }
        List<Position> positions = new ArrayList<>();
        return new ShowUserPortfolioResponse(errors, positions);
    }

}