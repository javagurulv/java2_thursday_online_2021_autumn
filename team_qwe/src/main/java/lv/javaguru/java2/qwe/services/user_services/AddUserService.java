package lv.javaguru.java2.qwe.services.user_services;

import lv.javaguru.java2.qwe.database.UserData;

public class AddUserService {

    private final UserData userData;

    public AddUserService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void execute() {
        userData.addUser();
    }

}