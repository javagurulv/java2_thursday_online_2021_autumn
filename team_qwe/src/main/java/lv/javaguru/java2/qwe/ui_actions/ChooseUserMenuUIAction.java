package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.ui_actions.user_ui_actions.*;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;
import static lv.javaguru.java2.qwe.ApplicationDemo.getApplicationContext;

@DIComponent
public class ChooseUserMenuUIAction implements UIAction {

    @Override
    public void execute() {
        String[] userMenu = {"ADD NEW USER", "REMOVE USER", "SHOW USER LIST", "FIND USER BY NAME",
                "GENERATE PORTFOLIO FOR USER", "SHOW USER PORTFOLIO", "SHOW USER PORTFOLIO GROUPED BY INDUSTRY",
                "SHOW USER INVESTMENTS BY EACH INDUSTRY", "SHOW PORTFOLIO SUMMARY", "RETURN TO MAIN MENU"};

        boolean userMenuOpen = true;
        while (userMenuOpen) {
            String type = inputDialog("Choose operation", "USER MENU", userMenu);
            switch (type) {
                case "ADD NEW USER" -> {
                    AddUserUIAction uiAction =
                            getApplicationContext().getBean(AddUserUIAction.class);
                    uiAction.execute();
                }
                case "REMOVE USER" -> {
                    RemoveUserUIAction uiAction =
                            getApplicationContext().getBean(RemoveUserUIAction.class);
                    uiAction.execute();
                }
                case "SHOW USER LIST" -> {
                    GetUserListUIAction uiAction =
                            getApplicationContext().getBean(GetUserListUIAction.class);
                    uiAction.execute();
                }
                case "FIND USER BY NAME" -> {
                    FindUserByNameUIAction uiAction =
                            getApplicationContext().getBean(FindUserByNameUIAction.class);
                    uiAction.execute();
                }
                case "GENERATE PORTFOLIO FOR USER" -> {
                    GenerateUserPortfolioUIAction uiAction =
                            getApplicationContext().getBean(GenerateUserPortfolioUIAction.class);
                    uiAction.execute();
                }
                case "SHOW USER PORTFOLIO" -> {
                    GetUserPortfolioUIAction uiAction =
                            getApplicationContext().getBean(GetUserPortfolioUIAction.class);
                    uiAction.execute();
                }
                case "SHOW USER PORTFOLIO GROUPED BY INDUSTRY" -> {
                    GetUserPortfolioGroupedByIndustryUIAction uiAction =
                            getApplicationContext().getBean(GetUserPortfolioGroupedByIndustryUIAction.class);
                    uiAction.execute();
                }
                case "SHOW USER INVESTMENTS BY EACH INDUSTRY" -> {
                    GetUserInvestmentsByEachIndustryUIAction uiAction =
                            getApplicationContext().getBean(GetUserInvestmentsByEachIndustryUIAction.class);
                    uiAction.execute();
                }
                case "SHOW PORTFOLIO SUMMARY" -> {
                    GetUserPortfolioSummaryUIAction uiAction =
                            getApplicationContext().getBean(GetUserPortfolioSummaryUIAction.class);
                    uiAction.execute();
                }
                default -> userMenuOpen = false;
            }
        }
    }

}