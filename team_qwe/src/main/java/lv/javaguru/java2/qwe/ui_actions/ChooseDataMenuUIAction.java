package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.ui_actions.data_ui_actions.*;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;
import static lv.javaguru.java2.qwe.ApplicationDemo.getApplicationContext;

@DIComponent
public class ChooseDataMenuUIAction implements UIAction {

    @Override
    public void execute() {
        String[] dataMenu = {"IMPORT DATA FROM FILE", "ADD STOCK", "ADD BOND", "REMOVE SECURITY",
                "SHOW LIST", "FIND SECURITY BY NAME", "FILTER SECURITIES(STOCKS) BY MULTIPLE PARAMETERS",
                "RETURN TO MAIN MENU"};

        boolean dataMenuOpen = true;
        while (dataMenuOpen) {
            String type = inputDialog("Choose operation", "DATA MENU", dataMenu);
            switch (type) {
                case "IMPORT DATA FROM FILE" -> {
                    ImportDataFromFileUIAction uiAction =
                            getApplicationContext().getBean(ImportDataFromFileUIAction.class);
                    uiAction.execute();
                }
                case "ADD STOCK" -> {
                    AddStockUIAction uiAction =
                            getApplicationContext().getBean(AddStockUIAction.class);
                    uiAction.execute();
                }
                case "ADD BOND" -> {
                    AddBondUIAction uiAction =
                            getApplicationContext().getBean(AddBondUIAction.class);
                    uiAction.execute();
                }
                case "REMOVE SECURITY" -> {
                    RemoveSecurityUIAction uiAction =
                            getApplicationContext().getBean(RemoveSecurityUIAction.class);
                    uiAction.execute();
                }
                case "SHOW LIST" -> {
                    GetAllSecurityListUIAction uiAction =
                            getApplicationContext().getBean(GetAllSecurityListUIAction.class);
                    uiAction.execute();
                }
                case "FIND SECURITY BY NAME" -> {
                    FindSecurityByNameUIAction uiAction =
                            getApplicationContext().getBean(FindSecurityByNameUIAction.class);
                    uiAction.execute();
                }
                case "FILTER SECURITIES(STOCKS) BY MULTIPLE PARAMETERS" -> {
                    FilterStocksByMultipleParametersUIAction uiAction =
                            getApplicationContext().getBean(FilterStocksByMultipleParametersUIAction.class);
                    uiAction.execute();
                }
                default -> dataMenuOpen = false;
            }
        }
    }

}