package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.ShowUserInvestmentsByEachIndustryValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShowUserInvestmentsByEachIndustryService {

    private final UserData userData;
    private final ShowUserInvestmentsByEachIndustryValidator validator;

    public ShowUserInvestmentsByEachIndustryService(UserData userData, ShowUserInvestmentsByEachIndustryValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public ShowUserInvestmentsByEachIndustryResponse execute(ShowUserInvestmentsByEachIndustryRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            return new ShowUserInvestmentsByEachIndustryResponse(
                    userData.showUserInvestmentsByEachIndustry(user.get()));
        }
        return new ShowUserInvestmentsByEachIndustryResponse(errors);
    }

}