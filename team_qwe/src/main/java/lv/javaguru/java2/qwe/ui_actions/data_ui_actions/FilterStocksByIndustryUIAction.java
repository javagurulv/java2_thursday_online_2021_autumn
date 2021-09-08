package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.List;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FilterStocksByIndustryUIAction implements UIAction {

    private final FilterStocksByIndustryService filterStocksByIndustryService;

    public FilterStocksByIndustryUIAction(FilterStocksByIndustryService filterStocksByIndustryService) {
        this.filterStocksByIndustryService = filterStocksByIndustryService;
    }

    @Override
    public void execute() {
        List<Security> filteredList =
                filterStocksByIndustryService.getDatabase().filterStocksByIndustry(inputDialog(
                        "Choose industry:",
                        "FILTER",
                        new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                                "Industrials", "Consumer Discretionary"}
                ));
        filteredList.forEach(System.out::println);
    }

}