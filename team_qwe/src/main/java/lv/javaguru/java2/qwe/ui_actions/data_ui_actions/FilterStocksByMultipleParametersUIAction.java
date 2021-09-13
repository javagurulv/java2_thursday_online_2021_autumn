package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStockByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByMultipleParametersService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class FilterStocksByMultipleParametersUIAction implements UIAction {

    private final FilterStocksByMultipleParametersService multipleParametersService;
    private final String[] parameters = {"Industry", "Market price", "Dividend", "Risk weight", "none"};
    private final String[] operators = {">", ">=", "<", "<=", "="};
    private final String[] industries = new String[]{"Consumer Staples", "Utilities", "Communications",
            "Health Care", "Technology", "Materials", "Energy", "Financials", "Real Estate",
            "Industrials", "Consumer Discretionary"};

    public FilterStocksByMultipleParametersUIAction(FilterStocksByMultipleParametersService multipleParametersService) {
        this.multipleParametersService = multipleParametersService;
    }

    @Override
    public void execute() {
        try {
            List<String> finalParameterList = setFilterParameters(parameters);
            messageDialog("You have chosen parameters:\n - " + printList(finalParameterList) + ";");
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
            String result = inputDialog("Choose parameter:", "FILTER", parameters);
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
        addDoubleParametersRequests(requestList, finalParameterList, operators);
        addIndustryParameterRequest(requestList, finalParameterList, industries);
        addOrderingRequest(requestList);
        addPagingRequest(requestList);
        return requestList;
    }

    private void addDoubleParametersRequests(List<SecurityRequest> requestList,
                                             List<String> finalParameterList, String[] operators) {
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .filter(i -> !finalParameterList.get(i).equals("Industry"))
                .forEach(i -> requestList.add(new FilterStockByAnyDoubleParameterRequest(
                        finalParameterList.get(i),
                        inputDialog("Choose operator:", "PARAMETER " + finalParameterList.get(i), operators),
                        inputDialog("Enter target amount for " + finalParameterList.get(i) + ":")
                )));
    }

    private void addIndustryParameterRequest(List<SecurityRequest> requestList,
                                             List<String> finalParameterList, String[] industries) {
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .filter(i -> finalParameterList.get(i).equals("Industry"))
                .forEach(i -> requestList.add(new FilterStockByIndustryRequest(
                        inputDialog("Choose industry:", "FILTER", industries)
                )));
    }

    private void addOrderingRequest(List<SecurityRequest> requestList) {
        String orderBy = inputDialog(
                "Choose parameter:", "ORDERING", new String[]
                        {"Name", "Industry", "Currency", "Market price", "Dividend", "Risk weight"});
        String orderDirection = inputDialog(
                "Choose ordering direction:", "ORDERING", new String[]{"ASCENDING", "DESCENDING"}
        );
        requestList.add(new OrderingRequest(orderBy, orderDirection));
    }

    private void addPagingRequest(List<SecurityRequest> requestList) {
        String pageNumber = inputDialog("Enter page number:");
        String pageSize = inputDialog("Enter page size:");
        requestList.add(new PagingRequest(pageNumber, pageSize));
    }

    private String printList(List<String> list) {
        return String.join(";\n - ", list);
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