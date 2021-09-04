package lv.javaguru.java2.qwe.services.data_services;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.request.AddStockRequest;
import lv.javaguru.java2.qwe.request.SecurityRequest;
import lv.javaguru.java2.qwe.services.validator.AddStockValidator;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class AddStockService {

    private final Database database;
    private final AddStockValidator validator;

    public AddStockService(Database database, AddStockValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public void execute(SecurityRequest request) {
        if (validator.validate(request).isEmpty()) {
            addStock((AddStockRequest) request);
            messageDialog("Stock has been added!");
        } else {
            messageDialog("FAILED to add stock!\n" +
                    String.join("\n", validator.validate(request)));
        }
    }

    private void addStock(AddStockRequest request) {
        database.addStock(new Stock(
                request.getName(),
                request.getIndustry(),
                request.getCurrency(),
                Double.parseDouble(request.getMarketPrice()),
                Double.parseDouble(request.getDividends()),
                Double.parseDouble(request.getRiskWeight()))
        );
    }

}