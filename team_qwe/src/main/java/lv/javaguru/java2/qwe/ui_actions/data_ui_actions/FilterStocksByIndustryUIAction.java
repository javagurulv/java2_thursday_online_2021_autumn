package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.FilterStockByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.FilterStockByIndustryResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByIndustryService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FilterStocksByIndustryUIAction implements UIAction {

    private final FilterStocksByIndustryService filterStocksByIndustryService;

    public FilterStocksByIndustryUIAction(FilterStocksByIndustryService filterStocksByIndustryService) {
        this.filterStocksByIndustryService = filterStocksByIndustryService;
    }

    @Override
    public void execute() {
        String[] industries = new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                "Technology", "Materials", "Energy", "Financials", "Real Estate",
                "Industrials", "Consumer Discretionary"};
        String industry = inputDialog("Choose industry:", "FILTER", industries);
        FilterStockByIndustryRequest request = new FilterStockByIndustryRequest(industry);
        FilterStockByIndustryResponse response = filterStocksByIndustryService.execute(request);
        response.getList().forEach(System.out::println);
        System.out.print("\n");
    }

}