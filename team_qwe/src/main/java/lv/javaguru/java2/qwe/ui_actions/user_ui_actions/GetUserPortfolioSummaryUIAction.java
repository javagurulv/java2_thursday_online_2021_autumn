package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioSummaryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioSummaryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class GetUserPortfolioSummaryUIAction implements UIAction {

    @Autowired private GetUserPortfolioSummaryService summaryService;
    @Autowired private UtilityMethods utils;
    private String userName;

    @Override
    public void execute() {
        GetUserPortfolioSummaryRequest request =
                new GetUserPortfolioSummaryRequest(utils.inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO SUMMARY",
                        utils.convertToStringArray(summaryService.getUserData())
                ));
        userName = request.getUserName();
        GetUserPortfolioSummaryResponse response =
                summaryService.execute(request);
        printResponse(response);

    }

    public void printResponse(GetUserPortfolioSummaryResponse response) {
        if (!response.hasErrors()) {
            System.out.println("===============PORTFOLIO SUMMARY AT "
                    + summaryService.getUserData().getCurrentDate() + "====================");
            System.out.println("USER NAME: " + userName);
            System.out.println("USER RISK TOLERANCE LEVEL: " + response.getUserRiskTolerance());
            System.out.println("USER INITIAL INVESTMENT: " + response.getUserInitialInvestment());
            Optional<LocalDate> portfolioGenerationDate = Optional.ofNullable(response.getPortfolioGenerationDate());
            System.out.println("DATE OF PORTFOLIO INCEPTION: "
                    + portfolioGenerationDate.orElse(summaryService.getUserData().getCurrentDate()));
            System.out.println("RETURN SINCE INCEPTION: " + utils.round((
                    response.getPortfolioValue() / response.getUserInitialInvestment() - 1) * 100) + "%");
            System.out.println("PORTFOLIO VALUE: " + utils.round(response.getPortfolioValue()));
            System.out.println("AMOUNT OF POSITIONS: " + response.getAmountOfPositions());
            System.out.println("PORTFOLIO ALLOCATION:");
            response.getPortfolioAllocation().forEach(
                    (key, value) -> System.out.println(key + ": " + utils.round(value * 100) + "%"));
            System.out.println("AVERAGE WEIGHTED DIVIDEND YIELD: " + utils.round(response.getAvgWgtDividendYield()) + "%");
            System.out.println("AVERAGE WEIGHTED RISK WEIGHT: " + utils.round(response.getAvgWgtRiskWeight()));
            System.out.println("======================================================");
        } else {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
    }

}