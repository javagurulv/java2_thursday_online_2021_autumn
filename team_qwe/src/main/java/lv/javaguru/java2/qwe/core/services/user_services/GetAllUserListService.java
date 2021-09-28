package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

@DIComponent
public class GetAllUserListService {

    @DIDependency private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public GetAllUserListResponse execute(GetAllUserListRequest request) {
        return new GetAllUserListResponse(userData.getAllUserList());
    }

}