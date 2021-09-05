package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.services.user_services.GeneratePortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.convertToStringArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class GeneratePortfolioForUserUIAction implements UIAction {

    private final GeneratePortfolioService generatePortfolioService;

    public GeneratePortfolioForUserUIAction(GeneratePortfolioService generatePortfolioService) {
        this.generatePortfolioService = generatePortfolioService;
    }

    @Override
    public void execute() {
        Optional<User> user = generatePortfolioService.getUserData().findUserByName(
                inputDialog("Enter name:", "GENERATE PORTFOLIO", convertToStringArray(generatePortfolioService.getUserData()))
        );
        generatePortfolioService.execute(user.orElseThrow(
                RuntimeException::new
        ));
    }

}