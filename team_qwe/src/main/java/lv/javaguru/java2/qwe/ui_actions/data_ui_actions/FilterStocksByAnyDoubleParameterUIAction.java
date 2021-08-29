package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FilterStocksByAnyDoubleParameterUIAction implements UIAction {

    private final Database database;

    public FilterStocksByAnyDoubleParameterUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        String[] operators = {">", ">=", "<", "<=", "="};
        String[] parameters = {"Market price", "Dividend", "Risk weight"};
        try {
            database.showListOfSecurities(database.filterStocksByAnyDoubleParameter(
                    inputDialog("Choose parameter:", "FILTER", parameters),
                    inputDialog("Choose operator:", "FILTER", operators),
                    Double.parseDouble(inputDialog("Enter amount:"))
            ));
        } catch (NumberFormatException e) {
            System.out.println("Wrong data!");
        }
    }

}