package lv.javaguru.java2.qwe.ui_actions.user_ui_actions;

import lv.javaguru.java2.qwe.core.API.API;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.StockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.services.user_services.StockMarketOrderService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMarketOrderUIAction implements UIAction {

    @Autowired private StockMarketOrderService service;
    @Autowired private UtilityMethods utils;
    @Autowired private Database database;
    @Autowired private UserData userData;
    @Autowired private API api;
    private String type;

    @Override
    public void execute() {
        String userName = utils.inputDialog(
                "Choose user:",
                "BUY SECURITY MARKET ORDER",
                utils.convertToStringArray(userData));
        type = utils.inputDialog("Choose trade type:", "MARKET ORDER", new String[]{"BUY", "SELL"});
        String ticker = utils.inputDialog("Security ticker");
        String quantity = utils.inputDialog("Enter quantity:");
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                userData.findUserByIdOrName(userName).orElse(null),
                database.findSecurityByTickerOrName(ticker).orElse(null),
                (type.equals("BUY")) ? quantity : "-" + quantity,
                api.getQuote(ticker));
        StockMarketOrderResponse response = service.execute(request);
        printResponse(response);
    }

    private void printResponse(StockMarketOrderResponse response) {
        if (response.hasErrors()) {
            utils.messageDialog("FAILED TO EXECUTE TRADE!\n" +
                    utils.printErrorList(response));
        } else if (type.equals("BUY")){
            utils.messageDialog("You bought " + response.getPosition().getAmount() + " of " +
                    response.getPosition().getSecurity().getTicker() + " at " + response.getPosition().getPurchasePrice());
        }
        else {
            utils.messageDialog("You sold " + response.getPosition().getAmount() + " of " +
                    response.getPosition().getSecurity().getTicker() + " at " + response.getPosition().getPurchasePrice());
        }
    }

}