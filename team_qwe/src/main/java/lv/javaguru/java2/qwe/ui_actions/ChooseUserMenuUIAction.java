package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.services.user_services.*;
import lv.javaguru.java2.qwe.core.services.validator.*;
import lv.javaguru.java2.qwe.ui_actions.user_ui_actions.*;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ChooseUserMenuUIAction implements UIAction {

    private final UserData userData;

    public ChooseUserMenuUIAction(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void execute() {
        String[] userMenu = {"ADD NEW USER", "REMOVE USER", "SHOW USER LIST", "FIND USER BY NAME",
                "GENERATE PORTFOLIO FOR USER", "SHOW USER PORTFOLIO", "SHOW USER PORTFOLIO GROUPED BY INDUSTRY",
                "SHOW USER INVESTMENTS BY EACH INDUSTRY", "SHOW PORTFOLIO SUMMARY", "RETURN TO MAIN MENU"};

        boolean userMenuOpen = true;
        while (userMenuOpen) {
            String type = inputDialog("Choose operation", "USER MENU", userMenu);
            switch (type) {
                case "ADD NEW USER" -> new AddUserUIAction(
                        new AddUserService(
                                userData, new AddUserValidator(userData))).execute();
                case "REMOVE USER" -> new RemoveUserUIAction(
                        new RemoveUserService(
                                userData)).execute();
                case "SHOW USER LIST" -> new GetUserListUIAction(
                        new GetAllUserListService(
                                userData)).execute();
                case "FIND USER BY NAME" -> new FindUserByNameUIAction(
                        new FindUserByNameService(
                                userData, new FindUserByNameValidator())).execute();
                case "GENERATE PORTFOLIO FOR USER" -> new GenerateUserPortfolioUIAction(
                        new GenerateUserPortfolioService(
                                userData, new GenerateUserPortfolioValidator())).execute();
                case "SHOW USER PORTFOLIO" -> new GetUserPortfolioUIAction(
                        new GetUserPortfolioService(
                                userData, new GetUserPortfolioValidator())).execute();
                case "SHOW USER PORTFOLIO GROUPED BY INDUSTRY" -> new GetUserPortfolioGroupedByIndustryUIAction(
                        new GetUserPortfolioGroupedByIndustryService(
                                userData, new GetUserPortfolioGroupedByIndustryValidator())).execute();
                case "SHOW USER INVESTMENTS BY EACH INDUSTRY" -> new GetUserInvestmentsByEachIndustryUIAction(
                        new GetUserInvestmentsByEachIndustryService(
                                userData, new GetUserInvestmentsByEachIndustryValidator())).execute();
                case "SHOW PORTFOLIO SUMMARY" -> new GetUserPortfolioSummaryUIAction(
                        new GetUserPortfolioSummaryService(
                                userData, new GetUserPortfolioSummaryValidator())).execute();
                default -> userMenuOpen = false;
            }
        }
    }

}