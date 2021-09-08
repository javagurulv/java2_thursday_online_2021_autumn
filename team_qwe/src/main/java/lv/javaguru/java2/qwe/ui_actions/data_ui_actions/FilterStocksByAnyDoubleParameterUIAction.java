package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByAnyDoubleParameterService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.List;

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
            List<Security> filteredList =
                    filterStocksByAnyDoubleParameterService.getDatabase().filterStocksByAnyDoubleParameter(
                            inputDialog("Choose parameter:", "FILTER", parameters),
                            inputDialog("Choose operator:", "FILTER", operators),
                            Double.parseDouble(inputDialog("Enter amount:"))
                    );
            filteredList.forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("Wrong data!");
        }
    }

}