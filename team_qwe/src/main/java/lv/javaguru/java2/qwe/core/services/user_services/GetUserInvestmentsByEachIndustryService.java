package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserInvestmentsByEachIndustryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetUserInvestmentsByEachIndustryService {

    @Autowired private UserData userData;
    @Autowired private GetUserInvestmentsByEachIndustryValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public GetUserInvestmentsByEachIndustryResponse execute(GetUserInvestmentsByEachIndustryRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByIdOrName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            return new GetUserInvestmentsByEachIndustryResponse(
                    userData.getUserInvestmentsByEachIndustry(user.get()));
        }
        return new GetUserInvestmentsByEachIndustryResponse(errors);
    }

}