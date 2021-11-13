package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.core.services.validator.RemoveUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class RemoveUserService {

    @Autowired private UserData userData;
    @Autowired private RemoveUserValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);

        return (errors.isEmpty()) ? new RemoveUserResponse(userData.removeUser(request.getName())) :
                new RemoveUserResponse(errors);
    }

}