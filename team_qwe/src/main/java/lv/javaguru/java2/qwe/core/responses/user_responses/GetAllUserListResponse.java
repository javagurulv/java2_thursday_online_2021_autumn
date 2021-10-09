package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.core.domain.User;

import java.util.List;

public class GetAllUserListResponse {

    private final List<User> list;

    public GetAllUserListResponse(List<User> list) {
        this.list = list;
    }

    public List<User> getList() {
        return list;
    }

}