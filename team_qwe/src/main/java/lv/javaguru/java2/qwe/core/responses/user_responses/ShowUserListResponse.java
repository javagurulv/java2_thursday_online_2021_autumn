package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.User;

import java.util.List;

public class ShowUserListResponse {

    private final List<User> list;

    public ShowUserListResponse(List<User> list) {
        this.list = list;
    }

    public List<User> getList() {
        return list;
    }

}