package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioGroupedByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserPortfolioGroupedByIndustryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserPortfolioGroupedByIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ShowUserPortfolioGroupedByIndustryUIAction implements UIAction {

    private final ShowUserPortfolioGroupedByIndustryService showUserPortfolioGroupedByIndustryService;
    private String userName;

    public ShowUserPortfolioGroupedByIndustryUIAction(ShowUserPortfolioGroupedByIndustryService showUserPortfolioGroupedByIndustryService) {
        this.showUserPortfolioGroupedByIndustryService = showUserPortfolioGroupedByIndustryService;
    }

    @Override
    public void execute() {
        ShowUserPortfolioGroupedByIndustryRequest request =
                new ShowUserPortfolioGroupedByIndustryRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO BY INDUSTRIES",
                        convertToStringArray(showUserPortfolioGroupedByIndustryService.getUserData())
                ));
        userName = request.getUserName();
        ShowUserPortfolioGroupedByIndustryResponse response =
                showUserPortfolioGroupedByIndustryService.execute(request);
        printResponse(response);
    }

    public void printResponse(ShowUserPortfolioGroupedByIndustryResponse response) {
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