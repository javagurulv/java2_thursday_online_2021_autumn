package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class SearchUsersResponse extends CoreResponse{

    private List<User> users;

    public SearchUsersResponse(List<User> users, List<CoreError> errorsList) {
        super(errorsList);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
