package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserInvestmentsByEachIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ShowUserInvestmentsByEachIndustryUIAction implements UIAction {

    private final ShowUserInvestmentsByEachIndustryService showUserInvestmentsByEachIndustryService;
    private String userName;

    public ShowUserInvestmentsByEachIndustryUIAction(ShowUserInvestmentsByEachIndustryService showUserInvestmentsByEachIndustryService) {
        this.showUserInvestmentsByEachIndustryService = showUserInvestmentsByEachIndustryService;
    }

    @Override
    public void execute() {
        ShowUserInvestmentsByEachIndustryRequest request =
                new ShowUserInvestmentsByEachIndustryRequest(inputDialog(
                        "Choose user:",
                        "SHOW INVESTMENTS BY INDUSTRY",
                        convertToStringArray(showUserInvestmentsByEachIndustryService.getUserData())
                ));
        userName = request.getUserName();
        ShowUserInvestmentsByEachIndustryResponse response =
                showUserInvestmentsByEachIndustryService.execute(request);
        printResponse(response);
    }

    public void printResponse(ShowUserInvestmentsByEachIndustryResponse response) {
        if (!response.hasErrors()) {
            System.out.println("==============" + userName + "===============");
            response.getInvestmentMap().forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println("\n");
        } else {
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }
    }

}