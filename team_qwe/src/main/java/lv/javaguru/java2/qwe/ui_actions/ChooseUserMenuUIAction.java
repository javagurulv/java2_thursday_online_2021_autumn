package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.UserData;
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
                "GENERATE PORTFOLIO FOR USER", "SHOW USER PORTFOLIO", "SHOW PORTFOLIO SUMMARY", "RETURN TO MAIN MENU"};

        boolean userMenuOpen = true;
        while (userMenuOpen) {
            String type = inputDialog("Choose operation", "USER MENU", userMenu);
            switch (type) {
                case "ADD NEW USER" -> new AddUserUIAction(userData).execute();
                case "REMOVE USER" -> new RemoveUserUIAction(userData).execute();
                case "SHOW USER LIST" -> new ShowUserListUIAction(userData).execute();
                case "FIND USER BY NAME" -> new FindUserByNameUIAction(userData).execute();
                case "GENERATE PORTFOLIO FOR USER" -> new GeneratePortfolioForUserUIAction(userData).execute();
                case "SHOW USER PORTFOLIO" -> new ShowUserPortfolioUIAction(userData).execute();
                case "SHOW PORTFOLIO SUMMARY" -> new ShowPortfolioSummaryUIAction(userData).execute();
                default -> userMenuOpen = false;
            }
        }
    }

}