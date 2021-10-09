package lv.javaguru.java2.qwe.ui_actions;

import lv.javaguru.java2.qwe.ui_actions.data_ui_actions.*;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

import static java.util.Map.*;
import static lv.javaguru.java2.qwe.ApplicationDemo.getApplicationContext;

@Component
public class ChooseDataMenuUIAction implements UIAction {

    @Autowired private UtilityMethods utils;

    @Override
    public void execute() {

        String[] dataMenu = {"IMPORT DATA FROM FILE", "ADD STOCK", "ADD BOND", "REMOVE SECURITY",
                "SHOW LIST", "FIND SECURITY BY NAME", "FILTER SECURITIES(STOCKS) BY MULTIPLE PARAMETERS",
                "RETURN TO MAIN MENU"};

        Map<String, UIAction> dataMenuMap = ofEntries(
                entry("IMPORT DATA FROM FILE",
                        getApplicationContext().getBean(ImportDataFromFileUIAction.class)),
                entry("ADD STOCK",
                        getApplicationContext().getBean(AddStockUIAction.class)),
                entry("ADD BOND",
                        getApplicationContext().getBean(AddBondUIAction.class)),
                entry("REMOVE SECURITY",
                        getApplicationContext().getBean(RemoveSecurityUIAction.class)),
                entry("SHOW LIST",
                        getApplicationContext().getBean(GetAllSecurityListUIAction.class)),
                entry("FIND SECURITY BY NAME",
                        getApplicationContext().getBean(FindSecurityByNameUIAction.class)),
                entry("FILTER SECURITIES(STOCKS) BY MULTIPLE PARAMETERS",
                        getApplicationContext().getBean(FilterStocksByMultipleParametersUIAction.class))
        );

        boolean dataMenuOpen = true;
        while (dataMenuOpen) {
            String type = utils.inputDialog("Choose operation", "DATA MENU", dataMenu);
            if (!dataMenuMap.containsKey(type)) {
                dataMenuOpen = false;
            } else {
                dataMenuMap.get(type).execute();
            }
        }
    }

}