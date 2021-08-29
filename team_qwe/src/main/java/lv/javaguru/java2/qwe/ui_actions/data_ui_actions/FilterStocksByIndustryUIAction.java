package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FilterStocksByIndustryUIAction implements UIAction {

    private final Database database;

    public FilterStocksByIndustryUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.showListOfSecurities(
                database.filterStocksByIndustry(inputDialog(
                        "Choose industry:",
                        "FILTER",
                        new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                                "Industrials", "Consumer Discretionary"}
                )));
    }

}