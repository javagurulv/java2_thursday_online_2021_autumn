package lv.javaguru.java2.qwe.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.database.UserData;

import java.util.Optional;

public class FindUserByNameService {

    private final UserData userData;

    public FindUserByNameService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public Optional<User> execute(String name) {
        return userData.findUserByName(name);
    }

}