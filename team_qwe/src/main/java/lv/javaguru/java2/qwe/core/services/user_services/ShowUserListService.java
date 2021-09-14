package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserListResponse;

public class ShowUserListService {

    private final UserData userData;

    public ShowUserListService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public ShowUserListResponse execute(ShowUserListRequest request) {
        return new ShowUserListResponse(userData.showUserList());
    }

}