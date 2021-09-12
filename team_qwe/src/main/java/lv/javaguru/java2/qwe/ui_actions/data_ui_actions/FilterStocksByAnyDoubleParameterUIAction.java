package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.FilterStockByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.responses.FilterStocksByAnyDoubleParameterResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByAnyDoubleParameterService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class FilterStocksByAnyDoubleParameterUIAction implements UIAction {

    private final FilterStocksByAnyDoubleParameterService filterStocksByAnyDoubleParameterService;

    public FilterStocksByAnyDoubleParameterUIAction(FilterStocksByAnyDoubleParameterService filterStocksByAnyDoubleParameterService) {
        this.filterStocksByAnyDoubleParameterService = filterStocksByAnyDoubleParameterService;
    }

    @Override
    public void execute() {
        String[] operators = {">", ">=", "<", "<=", "="};
        String[] parameters = {"Market price", "Dividend", "Risk weight"};

        String parameter = inputDialog("Choose parameter:", "FILTER", parameters);
        String operator = inputDialog("Choose operator:", "FILTER", operators);
        String targetAmount = inputDialog("Enter amount:");
        FilterStockByAnyDoubleParameterRequest request =
                new FilterStockByAnyDoubleParameterRequest(parameter, operator, targetAmount);
        FilterStocksByAnyDoubleParameterResponse response =
                filterStocksByAnyDoubleParameterService.execute(request);
        printResponse(response);
    }

    private void printResponse(FilterStocksByAnyDoubleParameterResponse response) {
        if (response.hasErrors()) {
            messageDialog("FAILED TO ADD STOCK!\n" +
                    printErrorList(response));
        } else {
            response.getList().forEach(System.out::println);
        }
    }

}