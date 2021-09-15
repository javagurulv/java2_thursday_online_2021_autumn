package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class GetUserPortfolioUIAction implements UIAction {

    private final GetUserPortfolioService showUserPortfolioService;
    private String userName;

    public GetUserPortfolioUIAction(GetUserPortfolioService showUserPortfolioService) {
        this.showUserPortfolioService = showUserPortfolioService;
    }

    @Override
    public void execute() {
        GetUserPortfolioRequest request =
                new GetUserPortfolioRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO",
                        convertToStringArray(showUserPortfolioService.getUserData())
                ));
        userName = request.getUserName();
        GetUserPortfolioResponse response = showUserPortfolioService.execute(request);
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