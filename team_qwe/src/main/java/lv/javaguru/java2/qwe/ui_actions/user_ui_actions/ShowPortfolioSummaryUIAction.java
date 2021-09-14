package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserPortfolioSummaryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.ShowPortfolioSummaryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.printErrorList;

public class ShowPortfolioSummaryUIAction implements UIAction {

    private final ShowPortfolioSummaryService showPortfolioSummaryService;
    private String userName;

    public ShowPortfolioSummaryUIAction(ShowPortfolioSummaryService showPortfolioSummaryService) {
        this.showPortfolioSummaryService = showPortfolioSummaryService;
    }

    @Override
    public void execute() {
        ShowUserPortfolioSummaryRequest request =
                new ShowUserPortfolioSummaryRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO SUMMARY",
                        convertToStringArray(showPortfolioSummaryService.getUserData())
                ));
        userName = request.getUserName();
        ShowUserPortfolioSummaryResponse response =
                showPortfolioSummaryService.execute(request);
        printResponse(response);

    }

    public void printResponse(ShowUserPortfolioSummaryResponse response) {
        if (!response.hasErrors()) {
            System.out.println("===============PORTFOLIO SUMMARY======================");
            System.out.println("USER NAME: " + userName);
            System.out.println("USER RISK TOLERANCE LEVEL: " + response.getUserRiskTolerance());
            System.out.println("USER INITIAL INVESTMENT: " + response.getUserInitialInvestment());
            System.out.println("RETURN SINCE INCEPTION: " + round((
                    response.getPortfolioValue() / response.getUserInitialInvestment() - 1) * 100) + "%");
            System.out.println("PORTFOLIO VALUE: " + round(response.getPortfolioValue()));
            System.out.println("AMOUNT OF POSITIONS: " + response.getAmountOfPositions());
            System.out.println("PORTFOLIO ALLOCATION:");
            response.getPortfolioAllocation().forEach(
                    (key, value) -> System.out.println(key + ": " + round(value * 100) + "%"));
            System.out.println("AVERAGE WEIGHTED DIVIDEND YIELD: " + round(response.getAvgWgtDividendYield()) + "%");
            System.out.println("AVERAGE WEIGHTED RISK WEIGHT: " + round(response.getAvgWgtRiskWeight()));
            System.out.println("======================================================");
        } else {
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }
    }

}