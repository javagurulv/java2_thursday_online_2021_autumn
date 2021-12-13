package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {

    private User updateUser;

    public UpdateUserResponse(User updateUser) {
        this.updateUser = updateUser;
    }

    public UpdateUserResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public User getUpdateUser() {
        return updateUser;
    }
}
