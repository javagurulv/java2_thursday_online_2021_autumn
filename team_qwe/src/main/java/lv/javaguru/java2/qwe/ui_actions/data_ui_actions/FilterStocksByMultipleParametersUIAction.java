package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.*;
import lv.javaguru.java2.qwe.core.responses.FilterStockByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByMultipleParametersService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class FilterStocksByMultipleParametersUIAction implements UIAction {

    private final FilterStocksByMultipleParametersService multipleParametersService;

    public FilterStocksByMultipleParametersUIAction(FilterStocksByMultipleParametersService multipleParametersService) {
        this.multipleParametersService = multipleParametersService;
    }

    @Override
    public void execute() {
        try {
            String[] parameters = {"Industry", "Market price", "Dividend", "Risk weight", "none"};
            String[] operators = {">", ">=", "<", "<=", "="};
            String[] industries = new String[]{"Consumer Staples", "Utilities", "Communications", "Health Care",
                    "Technology", "Materials", "Energy", "Financials", "Real Estate",
                    "Industrials", "Consumer Discretionary"};
            List<String> finalParameterList = setFilterParameters(parameters);
            messageDialog("You have chosen parameters:\n - " + printList(finalParameterList));
            List<SecurityRequest> requestList = createRequestList(finalParameterList, operators, industries);
            FilterStockByMultipleParametersRequest multiFilterRequest = new FilterStockByMultipleParametersRequest(requestList);
            FilterStockByMultipleParametersResponse response = multipleParametersService.execute(multiFilterRequest);
            printResponse(response);
            System.out.println("\n");
        } catch (NumberFormatException e) {
            System.out.println("Wrong format!");
        }
    }

    private List<String> setFilterParameters(String[] parameters) {
        List<String> finalParameterList = new ArrayList<>();
        while (Arrays.asList(parameters).contains("none") && parameters.length != 1) {
            String result = inputDialog("Choose parameter", "FILTER", parameters);
            if (result.equals("none") || result.isEmpty()) {
                break;
            }
            finalParameterList.add(result);
            parameters = Arrays.stream(parameters)
                    .filter(parameter -> !parameter.equals(result))
                    .toArray(String[]::new);
        }
        return finalParameterList;
    }

    private List<SecurityRequest> createRequestList(List<String> finalParameterList, String[] operators, String[] industries) {
        List<SecurityRequest> requestList = new ArrayList<>();
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .filter(i -> !finalParameterList.get(i).equals("Industry"))
                .forEach(i -> requestList.add(new FilterStockByAnyDoubleParameterRequest(
                        finalParameterList.get(i),
                        inputDialog("Choose operator:", "PARAMETER " + finalParameterList.get(i), operators),
                        inputDialog("Enter target amount for " + finalParameterList.get(i) + ":")
                )));
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .filter(i -> finalParameterList.get(i).equals("Industry"))
                .forEach(i -> requestList.add(new FilterStockByIndustryRequest(
                        inputDialog("Choose industry:", "FILTER", industries)
                )));
        String orderBy = inputDialog(
                "Choose parameter:", "SORTING", new String[]
                        {"Name", "Industry", "Currency", "Market price", "Dividend", "Risk weight"});
        String orderDirection = inputDialog(
                "Choose direction:", "SORTING", new String[]{"ASCENDING", "DESCENDING"}
        );
//        if (!orderBy.isEmpty() && !orderDirection.isEmpty()) {
            requestList.add(new OrderingRequest(orderBy, orderDirection));
//        }
        return requestList;
    }

    private String printList(List<String> list) {
        return String.join("\n - ", list);
    }

    private void printResponse(FilterStockByMultipleParametersResponse response) {
        if (response.hasErrors()) {
            messageDialog("FAILED TO FILTER!\n" +
                    printErrorList(response));
        } else {
            System.out.println("LIST STARTS:");
            response.getList().forEach(System.out::println);
            System.out.println("LIST ENDS");
        }
    }

}