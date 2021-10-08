package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindUserByNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindUserByNameService {

    @Autowired private UserData userData;
    @Autowired private FindUserByNameValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public FindUserByNameResponse execute(FindUserByNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> result = userData.findUserByName(request.getUserName());
        return (errors.isEmpty() && result.isPresent()) ?
                new FindUserByNameResponse(result.get()) : new FindUserByNameResponse(errors);
    }

}