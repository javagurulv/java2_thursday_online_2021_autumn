package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUserListService {

    @Autowired private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public GetAllUserListResponse execute(GetAllUserListRequest request) {
        return new GetAllUserListResponse(userData.getAllUserList());
    }

}