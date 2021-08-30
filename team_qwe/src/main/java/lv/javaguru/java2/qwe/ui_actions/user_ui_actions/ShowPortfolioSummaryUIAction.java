package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.services.user_services.ShowPortfolioSummaryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.convertToStringArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ShowPortfolioSummaryUIAction implements UIAction {

    private final ShowPortfolioSummaryService showPortfolioSummaryService;

    public ShowPortfolioSummaryUIAction(ShowPortfolioSummaryService showPortfolioSummaryService) {
        this.showPortfolioSummaryService = showPortfolioSummaryService;
    }

    @Override
    public void execute() {
        Optional<User> user = showPortfolioSummaryService.getUserData().findUserByName(
                inputDialog("Choose user:", "SHOW SUMMARY", convertToStringArray(showPortfolioSummaryService.getUserData()))
        );
        showPortfolioSummaryService.getUserData().showPortfolioSummary(user.orElseThrow(
                RuntimeException::new
        ));
    }

}