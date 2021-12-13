package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class RemoveUserResponse extends CoreResponse {

    private User deleteUser;

    public RemoveUserResponse(User deleteUser) {
        this.deleteUser = deleteUser;
    }

    public RemoveUserResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public User getDeleteUser() {
        return deleteUser;
    }
}
