package lv.javaguru.java2.qwe.core.responses.user_responses;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.CoreResponse;

import java.util.List;

public class FindUserByNameResponse extends CoreResponse {

    private User user;

    public FindUserByNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindUserByNameResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}