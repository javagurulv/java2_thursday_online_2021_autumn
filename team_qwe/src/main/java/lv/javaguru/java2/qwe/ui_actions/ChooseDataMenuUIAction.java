package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.services.data_services.*;
import lv.javaguru.java2.qwe.ui_actions.data_ui_actions.*;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class ChooseDataMenuUIAction implements UIAction {

    private final Database database;

    public ChooseDataMenuUIAction(Database database) {
        this.database = database;
    }


    @Override
    public void execute() {
        String[] dataMenu = {"IMPORT DATA FROM FILE", "ADD SECURITY", "REMOVE SECURITY", "SHOW LIST",
                "FIND SECURITY BY NAME", "FILTER SECURITIES(STOCKS) BY ANY DOUBLE PARAMETER",
                "FILTER SECURITIES(STOCKS) BY INDUSTRY", "RETURN TO MAIN MENU"};

        boolean dataMenuOpen = true;
        while (dataMenuOpen) {
            String type = inputDialog("Choose operation", "DATA MENU", dataMenu);
            switch (type) {
                case "IMPORT DATA FROM FILE" -> new ImportDataFromFileUIAction(new ImportSecuritiesService(database)).execute();
                case "ADD SECURITY" -> new AddSecurityUIAction(new AddSecurityService(database)).execute();
                case "REMOVE SECURITY" -> new RemoveSecurityUIAction(new RemoveSecurityService(database)).execute();
                case "SHOW LIST" -> new ShowListUIAction(new ShowListService(database)).execute();
                case "FIND SECURITY BY NAME" -> new FindSecurityByNameUIAction(new FindSecurityByNameService(database)).execute();
                case "FILTER SECURITIES(STOCKS) BY ANY DOUBLE PARAMETER" -> new FilterStocksByAnyDoubleParameterUIAction(new FilterStocksByAnyDoubleParameterService(database)).execute();
                case "FILTER SECURITIES(STOCKS) BY INDUSTRY" -> new FilterStocksByIndustryUIAction(new FilterStocksByIndustryService(database)).execute();
                default -> dataMenuOpen = false;
            }
        }
    }

}