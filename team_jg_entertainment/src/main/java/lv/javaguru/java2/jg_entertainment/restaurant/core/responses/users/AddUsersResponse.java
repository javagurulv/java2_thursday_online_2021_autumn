package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class AddUsersResponse extends CoreResponse {

    private User newUser;

    public AddUsersResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public AddUsersResponse(User newUser){
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }
}
