package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserInvestmentsByEachIndustryValidator;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class GetUserInvestmentsByEachIndustryService {

    @DIDependency private UserData userData;
    @DIDependency private GetUserInvestmentsByEachIndustryValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public GetUserInvestmentsByEachIndustryResponse execute(GetUserInvestmentsByEachIndustryRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            return new GetUserInvestmentsByEachIndustryResponse(
                    userData.getUserInvestmentsByEachIndustry(user.get()));
        }
        return new GetUserInvestmentsByEachIndustryResponse(errors);
    }

}