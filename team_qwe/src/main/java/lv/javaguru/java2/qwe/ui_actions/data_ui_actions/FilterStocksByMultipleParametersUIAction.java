package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.requests.FilterStockByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.requests.FilterStockByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByMultipleParametersService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.inputDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FilterStocksByMultipleParametersUIAction implements UIAction {

    private final FilterStocksByMultipleParametersService multipleParametersService;

    public FilterStocksByMultipleParametersUIAction(FilterStocksByMultipleParametersService multipleParametersService) {
        this.multipleParametersService = multipleParametersService;
    }

    @Override
    public void execute() {
        String[] parameters = {"Market price", "Dividend", "Risk weight", "none"};
        String[] operators = {">", ">=", "<", "<=", "="};
        List<String> finalParameterList = setFilterParameters(parameters);
        messageDialog("You have chosen parameters:\n - " + printList(finalParameterList));
        List<FilterStockByAnyDoubleParameterRequest> requestList = createRequestList(finalParameterList, operators);
        FilterStockByMultipleParametersRequest multiFilterRequest = new FilterStockByMultipleParametersRequest(requestList);
        List<Security> resultList = multipleParametersService.execute(multiFilterRequest);
        resultList.forEach(System.out::println);
        System.out.println("\n");
    }

    private List<String> setFilterParameters(String[] parameters) {
        List<String> finalParameterList = new ArrayList<>();
        while (Arrays.asList(parameters).contains("none") && parameters.length != 1) {
            String result = inputDialog("Choose parameter", "FILTER", parameters);
            if (result.equals("none")) {
                break;
            }
            finalParameterList.add(result);
            parameters = Arrays.stream(parameters)
                    .filter(parameter -> !parameter.equals(result))
                    .toArray(String[]::new);
        }
        return finalParameterList;
    }

    private List<FilterStockByAnyDoubleParameterRequest> createRequestList(List<String> finalParameterList, String[] operators) {
        List<FilterStockByAnyDoubleParameterRequest> requestList = new ArrayList<>();
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .forEach(i -> requestList.add(new FilterStockByAnyDoubleParameterRequest(
                        finalParameterList.get(i),
                        inputDialog("Choose operator", "PARAMETER " + finalParameterList.get(i), operators),
                        inputDialog("Enter target amount for " + finalParameterList.get(i))
                )));
        return requestList;
    }

    private String printList(List<String> list) {
        return String.join("\n - ", list);
    }

}