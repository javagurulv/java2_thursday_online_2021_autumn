package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

@Component
public class GetUserPortfolioUIAction implements UIAction {

    @Autowired
    private GetUserPortfolioService portfolioService;
    private String userName;

    @Override
    public void execute() {
        GetUserPortfolioRequest request =
                new GetUserPortfolioRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO",
                        convertToStringArray(portfolioService.getUserData())
                ));
        userName = request.getUserName();
        GetUserPortfolioResponse response = portfolioService.execute(request);
        printResponse(response);
    }

    public void printResponse(GetUserPortfolioResponse response) {
        if (!response.hasErrors()) {
            System.out.println("==============" + userName + "===============");
            response.getPortfolio().forEach(position -> System.out.println("company=" + position.getSecurity().getName() + ", amount=" +
                    position.getAmount() + ", purchase price=" + position.getPurchasePrice() + ", last market price=" +
                    position.getSecurity().getMarketPrice() + ", profit&loss=" +
                    round((position.getAmount() * position.getSecurity().getMarketPrice()) -
                            (position.getAmount() * position.getPurchasePrice()))));
            System.out.print("\n");
        } else {
            messageDialog("WRONG INPUT!\n" +
                    printErrorList(response));
        }

    }

}