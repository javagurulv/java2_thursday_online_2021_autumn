package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.core.services.validator.RemoveUserValidator;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class RemoveUserService {

    @DIDependency private UserData userData;
    @DIDependency private RemoveUserValidator validator;

    public UserData getUserData() {
        return userData;
    }

    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);

        return (errors.isEmpty()) ? new RemoveUserResponse(userData.removeUser(request.getName())) :
                new RemoveUserResponse(errors);
    }

}