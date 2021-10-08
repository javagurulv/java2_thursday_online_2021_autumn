package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioSummaryResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioSummaryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.printErrorList;

@Component
public class GetUserPortfolioSummaryUIAction implements UIAction {

    @Autowired private GetUserPortfolioSummaryService summaryService;
    private String userName;

    @Override
    public void execute() {
        GetUserPortfolioSummaryRequest request =
                new GetUserPortfolioSummaryRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO SUMMARY",
                        convertToStringArray(summaryService.getUserData())
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