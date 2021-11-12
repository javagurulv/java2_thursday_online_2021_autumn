package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.API.API;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.BuyStockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.BuyStockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.services.user_services.BuyStockMarketOrderService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyStockMarketOrderUIAction implements UIAction {

    @Autowired private BuyStockMarketOrderService service;
    @Autowired private UtilityMethods utils;
    @Autowired private Database database;
    @Autowired private UserData userData;
    @Autowired private API api;

    @Override
    public void execute() {
        String userName = utils.inputDialog(
                "Choose user:",
                "BUY SECURITY MARKET ORDER",
                utils.convertToStringArray(userData));
        String ticker = utils.inputDialog("Security ticker");
        String quantity = utils.inputDialog("Enter quantity:");
        BuyStockMarketOrderRequest request = new BuyStockMarketOrderRequest(
                userData.findUserByIdOrName(userName).orElse(null),
                database.findSecurityByTickerOrName(ticker).orElse(null),
                quantity,
                api.getQuote(ticker));
        BuyStockMarketOrderResponse response = service.execute(request);
        printResponse(response);
    }

    private void printResponse(BuyStockMarketOrderResponse response) {
        if (response.hasErrors()) {
            utils.messageDialog("FAILED TO BUY STOCK!\n" +
                    utils.printErrorList(response));
        } else {
            utils.messageDialog("You bought " + response.getPosition().getAmount() + " of " +
                    response.getPosition().getSecurity().getTicker() + " at " + response.getPosition().getPurchasePrice());
        }
    }

}