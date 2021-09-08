package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.convertToStringArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ShowUserPortfolioUIAction implements UIAction {

    private final ShowUserPortfolioService showUserPortfolioService;

    public ShowUserPortfolioUIAction(ShowUserPortfolioService showUserPortfolioService) {
        this.showUserPortfolioService = showUserPortfolioService;
    }

    @Override
    public void execute() {
        Optional<User> user = showUserPortfolioService.getUserData().findUserByName(
                inputDialog("Choose user:", "SHOW PORTFOLIO", convertToStringArray(showUserPortfolioService.getUserData()))
        );
        showUserPortfolioService.getUserData().showUserPortfolio(user.orElseThrow(
                RuntimeException::new
        ));
    }

}