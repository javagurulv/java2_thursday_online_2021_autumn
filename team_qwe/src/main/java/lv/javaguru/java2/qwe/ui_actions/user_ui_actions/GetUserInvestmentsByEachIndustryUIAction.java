package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserInvestmentsByEachIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserInvestmentsByEachIndustryUIAction implements UIAction {

    @Autowired private GetUserInvestmentsByEachIndustryService getUserInvestmentsByEachIndustryService;
    @Autowired private UtilityMethods utils;
    private String userName;

    @Override
    public void execute() {
        GetUserInvestmentsByEachIndustryRequest request =
                new GetUserInvestmentsByEachIndustryRequest(utils.inputDialog(
                        "Choose user:",
                        "SHOW INVESTMENTS BY INDUSTRY",
                        utils.convertToStringArray(getUserInvestmentsByEachIndustryService.getUserData())
                ));
        userName = request.getUserName();
        GetUserInvestmentsByEachIndustryResponse response =
                getUserInvestmentsByEachIndustryService.execute(request);
        printResponse(response);
    }

    public void printResponse(GetUserInvestmentsByEachIndustryResponse response) {
        if (!response.hasErrors()) {
            System.out.println("==============" + userName + "===============");
            response.getInvestmentMap().forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println("\n");
        } else {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
    }

}