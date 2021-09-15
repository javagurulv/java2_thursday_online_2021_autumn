package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindUserByNameValidator;

import java.util.List;
import java.util.Optional;

public class FindUserByNameService {

    private final UserData userData;
    private final FindUserByNameValidator validator;

    public FindUserByNameService(UserData userData, FindUserByNameValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public FindUserByNameResponse execute(FindUserByNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> result = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && result.isPresent()) {
            return new FindUserByNameResponse(result.get());
        }
        return new FindUserByNameResponse(errors);
    }

}