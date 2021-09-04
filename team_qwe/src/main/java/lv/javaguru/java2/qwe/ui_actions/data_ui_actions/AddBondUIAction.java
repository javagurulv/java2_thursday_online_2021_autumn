package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.request.AddBondRequest;
import lv.javaguru.java2.qwe.request.SecurityRequest;
import lv.javaguru.java2.qwe.services.data_services.AddBondService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.generateIndustriesArray;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class AddBondUIAction implements UIAction {

    private final AddBondService addBondService;

    public AddBondUIAction(AddBondService addBondService) {
        this.addBondService = addBondService;
    }

    @Override
    public void execute() {
        String name = inputDialog("Security name");
        String industry = inputDialog("Industry", "CHOOSE INDUSTRY", generateIndustriesArray());
        String currency = inputDialog("Currency", "CHOOSE CURRENCY", new String[]{"USD"});
        String marketPrice = inputDialog("Market price");
        String coupon = inputDialog("Coupon");
        String rating = inputDialog("Rating");
        String nominal = inputDialog("Nominal");
        String maturity = inputDialog("Maturity");
        SecurityRequest bondRequest = new AddBondRequest(name, industry, currency,
                marketPrice, coupon, rating, nominal, maturity);
        addBondService.execute(bondRequest);
    }

}