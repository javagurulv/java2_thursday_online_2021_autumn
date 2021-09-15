package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserInvestmentsByEachIndustryValidator;

import java.util.List;
import java.util.Optional;

public class GetUserInvestmentsByEachIndustryService {

    private final UserData userData;
    private final GetUserInvestmentsByEachIndustryValidator validator;

    public GetUserInvestmentsByEachIndustryService(UserData userData, GetUserInvestmentsByEachIndustryValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public GetUserInvestmentsByEachIndustryResponse execute(GetUserInvestmentsByEachIndustryRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            return new GetUserInvestmentsByEachIndustryResponse(
                    userData.showUserInvestmentsByEachIndustry(user.get()));
        }
        return new GetUserInvestmentsByEachIndustryResponse(errors);
    }

}