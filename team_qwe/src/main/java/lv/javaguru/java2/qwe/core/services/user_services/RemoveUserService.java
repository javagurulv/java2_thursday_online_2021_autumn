package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

@DIComponent
public class RemoveUserService {

    @DIDependency private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public RemoveUserResponse execute(RemoveUserRequest request) {
        return new RemoveUserResponse(userData.removeUser(request.getName()));
    }

}