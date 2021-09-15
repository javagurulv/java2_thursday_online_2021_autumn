package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;

public class GetAllUserListService {

    private final UserData userData;

    public GetAllUserListService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public GetAllUserListResponse execute(GetAllUserListRequest request) {
        return new GetAllUserListResponse(userData.showUserList());
    }

}