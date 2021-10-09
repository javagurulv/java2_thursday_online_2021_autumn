package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.ui_actions.user_ui_actions.*;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.*;
import static lv.javaguru.java2.qwe.ApplicationDemo.getApplicationContext;

@Component
public class ChooseUserMenuUIAction implements UIAction {

    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        String[] userMenu = {"ADD NEW USER", "REMOVE USER", "SHOW USER LIST", "FIND USER BY NAME",
                "GENERATE PORTFOLIO FOR USER", "SHOW USER PORTFOLIO", "SHOW USER PORTFOLIO GROUPED BY INDUSTRY",
                "SHOW USER INVESTMENTS BY EACH INDUSTRY", "SHOW PORTFOLIO SUMMARY", "RETURN TO MAIN MENU"};

        Map<String, UIAction> userMenuMap = ofEntries(
                entry("ADD NEW USER",
                        getApplicationContext().getBean(AddUserUIAction.class)),
                entry("REMOVE USER",
                        getApplicationContext().getBean(RemoveUserUIAction.class)),
                entry("SHOW USER LIST",
                        getApplicationContext().getBean(GetUserListUIAction.class)),
                entry("FIND USER BY NAME",
                        getApplicationContext().getBean(FindUserByNameUIAction.class)),
                entry("GENERATE PORTFOLIO FOR USER",
                        getApplicationContext().getBean(GenerateUserPortfolioUIAction.class)),
                entry("SHOW USER PORTFOLIO",
                        getApplicationContext().getBean(GetUserPortfolioUIAction.class)),
                entry("SHOW USER PORTFOLIO GROUPED BY INDUSTRY",
                        getApplicationContext().getBean(GetUserPortfolioGroupedByIndustryUIAction.class)),
                entry("SHOW USER INVESTMENTS BY EACH INDUSTRY",
                        getApplicationContext().getBean(GetUserInvestmentsByEachIndustryUIAction.class)),
                entry("SHOW PORTFOLIO SUMMARY",
                        getApplicationContext().getBean(GetUserPortfolioSummaryUIAction.class))
        );

        boolean userMenuOpen = true;
        while (userMenuOpen) {
            String type = utils.inputDialog("Choose operation", "USER MENU", userMenu);
            if (!userMenuMap.containsKey(type)) {
                userMenuOpen = false;
            } else {
                userMenuMap.get(type).execute();
            }
        }
    }

}