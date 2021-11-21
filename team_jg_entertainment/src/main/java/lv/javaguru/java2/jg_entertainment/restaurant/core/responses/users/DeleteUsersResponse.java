package lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users;

import java.util.List;

public class DeleteUsersResponse extends CoreResponse{

    private boolean newUser;

    public DeleteUsersResponse(List<CoreError> errorsList) {
        super(errorsList);
    }

    public DeleteUsersResponse(boolean newUser) {
        this.newUser = newUser;
    }

    public boolean ifUserIdDeleted() {
        return newUser;
    }
}
