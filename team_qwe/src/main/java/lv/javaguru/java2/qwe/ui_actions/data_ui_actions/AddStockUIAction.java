package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.services.data_services.AddStockService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddStockUIAction implements UIAction {

    @Autowired private AddStockService addStockService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        String name = utils.inputDialog("Security name");
        String industry = utils.inputDialog("Industry", "CHOOSE INDUSTRY", utils.generateIndustriesArray());
        String currency = utils.inputDialog("Currency", "CHOOSE CURRENCY", new String[]{"USD"});
        String marketPrice = utils.inputDialog("Market price");
        String dividend = utils.inputDialog("Dividend");
        String riskWeight = utils.inputDialog("Risk weight");
        CoreRequest stockRequest = new AddStockRequest(name, industry, currency,
                marketPrice, dividend, riskWeight);
        AddStockResponse stockResponse = addStockService.execute(stockRequest);
        printResponse(stockResponse);
    }

    private void printResponse(AddStockResponse response) {
        if (response.hasErrors()) {
            utils.messageDialog("FAILED TO ADD STOCK!\n" +
                    utils.printErrorList(response));
        } else {
            utils.messageDialog("Stock " + response.getNewStock().getName() + " has been added!");
        }
    }

}