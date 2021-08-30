package lv.javaguru.java2.qwe.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.database.UserData;

public class ShowUserPortfolioService {

    private final UserData userData;

    public ShowUserPortfolioService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void execute(User user) {
        userData.showUserPortfolio(user);
    }

}