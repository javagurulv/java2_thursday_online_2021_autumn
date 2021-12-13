package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class GetUserResponse extends CoreResponse {

    private User user;

    public GetUserResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public GetUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
