package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.request.AddStockRequest;
import lv.javaguru.java2.qwe.request.SecurityRequest;
import lv.javaguru.java2.qwe.services.data_services.AddStockService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.generateIndustriesArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class AddStockUIAction implements UIAction {

    private final AddStockService addStockService;

    public AddStockUIAction(AddStockService addSecurityService) {
        this.addStockService = addSecurityService;
    }

    @Override
    public void execute() {
        String name = inputDialog("Security name");
        String industry = inputDialog("Industry", "CHOOSE INDUSTRY", generateIndustriesArray());
        String currency = inputDialog("Currency", "CHOOSE CURRENCY", new String[]{"USD"});
        String marketPrice = inputDialog("Market price");
        String dividend = inputDialog("Dividend");
        String riskWeight = inputDialog("Risk weight");
        SecurityRequest stockRequest = new AddStockRequest(name, industry, currency,
                marketPrice, dividend, riskWeight);
        addStockService.execute(stockRequest);
    }

}