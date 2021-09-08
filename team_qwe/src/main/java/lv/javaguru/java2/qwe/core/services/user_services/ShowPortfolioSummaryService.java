package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;

public class ShowPortfolioSummaryService {

    private final UserData userData;

    public ShowPortfolioSummaryService(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void execute(User user) {
        userData.showPortfolioSummary(user);
    }

}