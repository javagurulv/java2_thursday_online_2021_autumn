package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.services.data_services.AddStockService;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

@DIComponent
public class AddStockUIAction implements UIAction {

    @DIDependency private AddStockService addStockService;

    @Override
    public void execute() {
        String name = inputDialog("Security name");
        String industry = inputDialog("Industry", "CHOOSE INDUSTRY", generateIndustriesArray());
        String currency = inputDialog("Currency", "CHOOSE CURRENCY", new String[]{"USD"});
        String marketPrice = inputDialog("Market price");
        String dividend = inputDialog("Dividend");
        String riskWeight = inputDialog("Risk weight");
        CoreRequest stockRequest = new AddStockRequest(name, industry, currency,
                marketPrice, dividend, riskWeight);
        AddStockResponse stockResponse = addStockService.execute(stockRequest);
        printResponse(stockResponse);
    }

    private void printResponse(AddStockResponse response) {
        if (response.hasErrors()) {
            messageDialog("FAILED TO ADD STOCK!\n" +
                    printErrorList(response));
        } else {
            messageDialog("Stock " + response.getNewStock().getName() + " has been added!");
        }
    }

}