package lv.javaguru.java2.qwe.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.database.UserData;

import java.util.List;

public class ShowUserListService {

    private final UserData userData;

    public ShowUserListService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void execute(List<User> list) {
        userData.showListOfUsers(list);
    }

}