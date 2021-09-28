package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserInvestmentsByEachIndustryService;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

@DIComponent
public class GetUserInvestmentsByEachIndustryUIAction implements UIAction {

    @DIDependency private GetUserInvestmentsByEachIndustryService getUserInvestmentsByEachIndustryService;
    private String userName;

    @Override
    public void execute() {
        GetUserInvestmentsByEachIndustryRequest request =
                new GetUserInvestmentsByEachIndustryRequest(inputDialog(
                        "Choose user:",
                        "SHOW INVESTMENTS BY INDUSTRY",
                        convertToStringArray(getUserInvestmentsByEachIndustryService.getUserData())
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
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }
    }

}