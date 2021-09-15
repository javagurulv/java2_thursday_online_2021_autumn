package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserInvestmentsByEachIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class GetUserInvestmentsByEachIndustryUIAction implements UIAction {

    private final GetUserInvestmentsByEachIndustryService showUserInvestmentsByEachIndustryService;
    private String userName;

    public GetUserInvestmentsByEachIndustryUIAction(GetUserInvestmentsByEachIndustryService showUserInvestmentsByEachIndustryService) {
        this.showUserInvestmentsByEachIndustryService = showUserInvestmentsByEachIndustryService;
    }

    @Override
    public void execute() {
        GetUserInvestmentsByEachIndustryRequest request =
                new GetUserInvestmentsByEachIndustryRequest(inputDialog(
                        "Choose user:",
                        "SHOW INVESTMENTS BY INDUSTRY",
                        convertToStringArray(showUserInvestmentsByEachIndustryService.getUserData())
                ));
        userName = request.getUserName();
        GetUserInvestmentsByEachIndustryResponse response =
                showUserInvestmentsByEachIndustryService.execute(request);
        printResponse(response);
    }

    public void printResponse(GetUserInvestmentsByEachIndustryResponse response) {
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