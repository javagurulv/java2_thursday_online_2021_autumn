package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserPortfolioUIAction implements UIAction {

    @Autowired private GetUserPortfolioService portfolioService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        GetUserPortfolioRequest request =
                new GetUserPortfolioRequest(utils.inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO",
                        utils.convertToStringArray(portfolioService.getUserData())
                ));
        GetUserPortfolioResponse response = portfolioService.execute(request);
        printResponse(response);
    }

    public void printResponse(GetUserPortfolioResponse response) {
        if (!response.hasErrors()) {
            String userName = response.getUser().getName() + "(ID: " + response.getUser().getId() + ")";
            System.out.println("==============" + userName + "===============");
            response.getUser().getPortfolio().forEach(position -> System.out.println("company=" + position.getSecurity().getName() + ", amount=" +
                    position.getAmount() + ", purchase price=" + position.getPurchasePrice() + ", last market price=" +
                    position.getSecurity().getMarketPrice() + ", profit&loss=" +
                    utils.round((position.getAmount() * position.getSecurity().getMarketPrice()) -
                            (position.getAmount() * position.getPurchasePrice()))));
            System.out.println("CASH: " + response.getUser().getCash());
            System.out.print("\n");
        } else {
            utils.messageDialog("WRONG INPUT!\n" +
                    utils.printErrorList(response));
        }
    }

}