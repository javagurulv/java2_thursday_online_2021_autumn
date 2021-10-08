package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioGroupedByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioGroupedByIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioGroupedByIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

@Component
public class GetUserPortfolioGroupedByIndustryUIAction implements UIAction {

    @Autowired private GetUserPortfolioGroupedByIndustryService getUserPortfolioGroupedByIndustryService;
    private String userName;

    @Override
    public void execute() {
        GetUserPortfolioGroupedByIndustryRequest request =
                new GetUserPortfolioGroupedByIndustryRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO BY INDUSTRIES",
                        convertToStringArray(getUserPortfolioGroupedByIndustryService.getUserData())
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
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }
    }

}