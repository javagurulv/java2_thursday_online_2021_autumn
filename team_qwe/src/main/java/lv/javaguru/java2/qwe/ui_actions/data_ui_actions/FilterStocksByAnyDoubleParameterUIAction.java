package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.services.data_services.FilterStocksByAnyDoubleParameterService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

public class FilterStocksByAnyDoubleParameterUIAction implements UIAction {

    private final FilterStocksByAnyDoubleParameterService filterStocksByAnyDoubleParameterService;

    public FilterStocksByAnyDoubleParameterUIAction(FilterStocksByAnyDoubleParameterService filterStocksByAnyDoubleParameterService) {
        this.filterStocksByAnyDoubleParameterService = filterStocksByAnyDoubleParameterService;
    }

    @Override
    public void execute() {
        String[] operators = {">", ">=", "<", "<=", "="};
        String[] parameters = {"Market price", "Dividend", "Risk weight"};
        try {
            filterStocksByAnyDoubleParameterService.getDatabase().showListOfSecurities(
                    filterStocksByAnyDoubleParameterService.getDatabase().filterStocksByAnyDoubleParameter(
                            inputDialog("Choose parameter:", "FILTER", parameters),
                            inputDialog("Choose operator:", "FILTER", operators),
                            Double.parseDouble(inputDialog("Enter amount:")))
            );
        } catch (NumberFormatException e) {
            System.out.println("Wrong data!");
        }
    }

}