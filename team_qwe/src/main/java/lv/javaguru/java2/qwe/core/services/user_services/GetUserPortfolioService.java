package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserPortfolioValidator;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class GetUserPortfolioService {

    @DIDependency private UserData userData;
    @DIDependency private GetUserPortfolioValidator validator;

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