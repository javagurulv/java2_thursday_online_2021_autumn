package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.services.data_services.*;
import lv.javaguru.java2.qwe.core.services.validator.*;
import lv.javaguru.java2.qwe.ui_actions.data_ui_actions.*;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ChooseDataMenuUIAction implements UIAction {

    private final Database database;

    public ChooseDataMenuUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        String[] dataMenu = {"IMPORT DATA FROM FILE", "ADD STOCK", "ADD BOND", "REMOVE SECURITY",
                "SHOW LIST", "FIND SECURITY BY NAME", "FILTER SECURITIES(STOCKS) BY MULTIPLE PARAMETERS",
                "RETURN TO MAIN MENU"};

        boolean dataMenuOpen = true;
        while (dataMenuOpen) {
            String type = inputDialog("Choose operation", "DATA MENU", dataMenu);
            switch (type) {
                case "IMPORT DATA FROM FILE" -> new ImportDataFromFileUIAction(
                        new ImportSecuritiesService(database, new AddStockValidator(database), new AddBondValidator(database))).execute();
                case "ADD STOCK" -> new AddStockUIAction(
                        new AddStockService(database, new AddStockValidator(database))).execute();
                case "ADD BOND" -> new AddBondUIAction(
                        new AddBondService(database, new AddBondValidator(database))).execute();
                case "REMOVE SECURITY" -> new RemoveSecurityUIAction(
                        new RemoveSecurityService(database)).execute();
                case "SHOW LIST" -> new GetAllSecurityListUIAction(
                        new GetAllSecurityListService(database)).execute();
                case "FIND SECURITY BY NAME" -> new FindSecurityByNameUIAction(
                        new FindSecurityByNameService(database, new FindSecurityByNameValidator())).execute();
                case "FILTER SECURITIES(STOCKS) BY MULTIPLE PARAMETERS" -> new FilterStocksByMultipleParametersUIAction(
                        new FilterStocksByMultipleParametersService(database, new FilterStocksByMultipleParametersValidator())).execute();
                default -> dataMenuOpen = false;
            }
        }
    }

}