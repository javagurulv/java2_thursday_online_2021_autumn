package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioGroupedByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioGroupedByIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserPortfolioGroupedByIndustryValidator;

import java.util.*;

public class GetUserPortfolioGroupedByIndustryService {

    private final UserData userData;
    private final GetUserPortfolioGroupedByIndustryValidator validator;

    public GetUserPortfolioGroupedByIndustryService(UserData userData, GetUserPortfolioGroupedByIndustryValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public GetUserPortfolioGroupedByIndustryResponse execute(GetUserPortfolioGroupedByIndustryRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            return new GetUserPortfolioGroupedByIndustryResponse(
                    userData.getUserPortfolioGroupedByIndustry(user.get()));
        }
        return new GetUserPortfolioGroupedByIndustryResponse(errors);
    }

}