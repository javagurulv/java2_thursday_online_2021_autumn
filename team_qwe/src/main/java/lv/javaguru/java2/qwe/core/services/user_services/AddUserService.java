package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.AddUserResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.qwe.core.domain.Type.*;

@Component
public class AddUserService {

    @Autowired private UserData userData;
    @Autowired private AddUserValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            User user = createUser(request);
            Long id = userData.addUser(user);
            user.setId(id);
            return new AddUserResponse(user);
        }
        return new AddUserResponse(errors);
    }

    private User createUser(AddUserRequest request) {
        return new User(
                request.getName(),
                Integer.parseInt(request.getAge()),
                valueOf(request.getType()),
                Double.parseDouble(request.getInitialInvestment())
        );
    }

}