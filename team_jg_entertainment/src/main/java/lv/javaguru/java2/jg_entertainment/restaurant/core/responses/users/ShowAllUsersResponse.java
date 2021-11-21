package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class ShowAllUsersResponse extends CoreResponse{
    private List<User> newUser;


    public ShowAllUsersResponse(List<CoreError> errorsList, List<User> newUser) {
        super(errorsList);
        this.newUser = newUser;
    }

    public ShowAllUsersResponse(List<User> newUser) {
        this.newUser = newUser;
    }

    public List<User> getNewUser() {
        return newUser;
    }
}
