package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserInvestmentsByEachIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.convertToStringArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ShowUserInvestmentsByEachIndustryUIAction implements UIAction {

    private final ShowUserInvestmentsByEachIndustryService showUserInvestmentsByEachIndustryService;

    public ShowUserInvestmentsByEachIndustryUIAction(ShowUserInvestmentsByEachIndustryService showUserInvestmentsByEachIndustryService) {
        this.showUserInvestmentsByEachIndustryService = showUserInvestmentsByEachIndustryService;
    }

    @Override
    public void execute() {
        Optional<User> user = showUserInvestmentsByEachIndustryService.getUserData().findUserByName(
                inputDialog("Choose user:", "SHOW INDUSTRY", convertToStringArray(showUserInvestmentsByEachIndustryService.getUserData()))
        );
        showUserInvestmentsByEachIndustryService.getUserData().showUserInvestmentsByEachIndustry(user.orElseThrow(
                RuntimeException::new
        ));
    }

}