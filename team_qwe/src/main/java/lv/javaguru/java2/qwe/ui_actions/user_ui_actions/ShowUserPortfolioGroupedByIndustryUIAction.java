package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserPortfolioGroupedByIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.convertToStringArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ShowUserPortfolioGroupedByIndustryUIAction implements UIAction {

    private final ShowUserPortfolioGroupedByIndustryService showUserPortfolioGroupedByIndustryService;

    public ShowUserPortfolioGroupedByIndustryUIAction(ShowUserPortfolioGroupedByIndustryService showUserPortfolioGroupedByIndustryService) {
        this.showUserPortfolioGroupedByIndustryService = showUserPortfolioGroupedByIndustryService;
    }

    @Override
    public void execute() {
        Optional<User> user = showUserPortfolioGroupedByIndustryService.getUserData().findUserByName(
                inputDialog("Choose user:", "SHOW INDUSTRY", convertToStringArray(showUserPortfolioGroupedByIndustryService.getUserData()))
        );
        showUserPortfolioGroupedByIndustryService.getUserData().showUserPortfolioGroupedByIndustry(user.orElseThrow(
                RuntimeException::new
        ));
    }

}