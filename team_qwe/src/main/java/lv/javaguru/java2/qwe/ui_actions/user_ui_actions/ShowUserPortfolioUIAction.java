package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.ShowUserPortfolioService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class ShowUserPortfolioUIAction implements UIAction {

    private final ShowUserPortfolioService showUserPortfolioService;
    private String userName;

    public ShowUserPortfolioUIAction(ShowUserPortfolioService showUserPortfolioService) {
        this.showUserPortfolioService = showUserPortfolioService;
    }

    @Override
    public void execute() {
        ShowUserPortfolioRequest request =
                new ShowUserPortfolioRequest(inputDialog(
                        "Choose user:",
                        "SHOW PORTFOLIO",
                        convertToStringArray(showUserPortfolioService.getUserData())
                ));
        userName = request.getUserName();
        ShowUserPortfolioResponse response = showUserPortfolioService.execute(request);
        printResponse(response);
    }

    public void printResponse(ShowUserPortfolioResponse response) {
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