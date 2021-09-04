package lv.javaguru.java2.qwe.services.data_services;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.request.AddBondRequest;
import lv.javaguru.java2.qwe.request.SecurityRequest;
import lv.javaguru.java2.qwe.services.validator.AddBondValidator;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class AddBondService {

    private final Database database;
    private final AddBondValidator validator;

    public AddBondService(Database database, AddBondValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public void execute(SecurityRequest request) {
        if (validator.validate(request).isEmpty()) {
            addBond((AddBondRequest) request);
            messageDialog("Bond has been added!");
        } else {
            messageDialog("FAILED to add bond!" +
                    String.join("\n", validator.validate(request)));
        }
    }

    private void addBond(AddBondRequest request) {
        database.addBond(new Bond(
                request.getName(),
                request.getIndustry(),
                request.getCurrency(),
                Double.parseDouble(request.getMarketPrice()),
                Double.parseDouble(request.getCoupon()),
                request.getRating(),
                Integer.parseInt(request.getNominal()),
                request.getMaturity())
        );
    }

}