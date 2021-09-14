package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioGroupedByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserPortfolioGroupedByIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.ShowUserPortfolioGroupedByIndustryValidator;

import java.util.*;

public class ShowUserPortfolioGroupedByIndustryService {

    private final UserData userData;
    private final ShowUserPortfolioGroupedByIndustryValidator validator;

    public ShowUserPortfolioGroupedByIndustryService(UserData userData, ShowUserPortfolioGroupedByIndustryValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public ShowUserPortfolioGroupedByIndustryResponse execute(ShowUserPortfolioGroupedByIndustryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Optional<User> user = userData.findUserByName(request.getUserName());
            return new ShowUserPortfolioGroupedByIndustryResponse(
                    userData.showUserPortfolioGroupedByIndustry(user.orElseThrow(RuntimeException::new)));
        }
        return new ShowUserPortfolioGroupedByIndustryResponse(errors);
    }

}