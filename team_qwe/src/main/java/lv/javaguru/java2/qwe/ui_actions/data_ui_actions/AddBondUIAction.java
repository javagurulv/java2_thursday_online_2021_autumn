package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.AddBondRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddBondResponse;
import lv.javaguru.java2.qwe.core.services.data_services.AddBondService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddBondUIAction implements UIAction {

    @Autowired private AddBondService addBondService;
    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {
        String ticker = utils.inputDialog("Security ticker");
        String name = utils.inputDialog("Security name");
        String industry = utils.inputDialog("Industry", "CHOOSE INDUSTRY", utils.generateIndustriesArray());
        String currency = utils.inputDialog("Currency", "CHOOSE CURRENCY", new String[]{"USD"});
        String marketPrice = utils.inputDialog("Market price");
        String coupon = utils.inputDialog("Coupon");
        String rating = utils.inputDialog("Rating");
        String nominal = utils.inputDialog("Nominal");
        String maturity = utils.inputDialog("Maturity");
        CoreRequest bondRequest = new AddBondRequest(ticker, name, industry, currency,
                marketPrice, coupon, rating, nominal, maturity);
        AddBondResponse bondResponse = addBondService.execute(bondRequest);
        printResponse(bondResponse);
    }

    private void printResponse(AddBondResponse response) {
        if (response.hasErrors()) {
            utils.messageDialog("FAILED TO ADD BOND!\n" +
                    utils.printErrorList(response));
        } else {
            utils.messageDialog("Bond " + response.getNewBond().getName() + " has been added!");
        }
    }

}