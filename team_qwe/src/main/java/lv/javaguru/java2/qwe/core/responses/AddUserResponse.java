package lv.javaguru.java2.qwe.core.responses;

import lv.javaguru.java2.qwe.User;

import java.util.List;

public class AddUserResponse extends CoreResponse {

    private User newUser;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddUserResponse(User newUser) {
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }

}