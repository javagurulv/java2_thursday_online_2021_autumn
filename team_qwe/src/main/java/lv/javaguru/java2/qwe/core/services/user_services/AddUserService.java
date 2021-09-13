package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.AddUserResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddUserValidator;

import java.util.List;

import static lv.javaguru.java2.qwe.Type.*;

public class AddUserService {

    private final UserData userData;
    private final AddUserValidator validator;

    public AddUserService(UserData userData, AddUserValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            User user = addUser(request);
            userData.addUser(user);
            return new AddUserResponse(user);
        } else {
            return new AddUserResponse(errors);
        }
    }

    private User addUser(AddUserRequest request) {
        return new User(
                request.getName(),
                Integer.parseInt(request.getAge()),
                valueOf(request.getType()),
                Double.parseDouble(request.getInitialInvestment())
        );
    }

}