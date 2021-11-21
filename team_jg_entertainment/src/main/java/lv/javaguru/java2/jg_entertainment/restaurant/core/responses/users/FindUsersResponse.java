package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class FindUsersResponse extends CoreResponse{

    private List<User> users;

    public FindUsersResponse(List<CoreError> errorsList, List<User> users) {
        super(errorsList);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
