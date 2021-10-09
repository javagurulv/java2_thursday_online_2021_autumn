package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioGroupedByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioGroupedByIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioGroupedByIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserPortfolioGroupedByIndustryUIAction implements UIAction {

    @Autowired private GetUserPortfolioGroupedByIndustryService getUserPortfolioGroupedByIndustryService;
    @Autowired private UtilityMethods utils;
    private String userName;

    @Override
    public void execute() {
        GetUserPortfolioGroupedByIndustryRequest request =
                new GetUserPortfolioGroupedByIndustryRequest(utils.inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO BY INDUSTRIES",
                        utils.convertToStringArray(getUserPortfolioGroupedByIndustryService.getUserData())
                ));
        userName = request.getUserName();
        GetUserPortfolioGroupedByIndustryResponse response =
                getUserPortfolioGroupedByIndustryService.execute(request);
        printResponse(response);
    }

    public void printResponse(GetUserPortfolioGroupedByIndustryResponse response) {
        if (!response.hasErrors()) {
            System.out.println("==============" + userName + "===============");
            response.getIndustryMap().forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println("\n");
        } else {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
    }

}