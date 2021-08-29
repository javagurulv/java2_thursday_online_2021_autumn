package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.UserData;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.convertToStringArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ShowUserPortfolioUIAction implements UIAction {

    private final UserData userData;

    public ShowUserPortfolioUIAction(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void execute() {
        Optional<User> user = userData.findUserByName(
                inputDialog("Choose user:", "SHOW PORTFOLIO", convertToStringArray(userData))
        );
        userData.showUserPortfolio(user.orElseThrow(
                RuntimeException::new
        ));
    }

}