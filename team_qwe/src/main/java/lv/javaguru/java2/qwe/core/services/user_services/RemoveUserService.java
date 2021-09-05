package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;

public class RemoveUserService {

    private final UserData userData;

    public RemoveUserService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void execute(String name) {
        userData.removeUser(name);
    }

}