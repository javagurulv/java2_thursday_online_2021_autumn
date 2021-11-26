package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FilterStocksByMultipleParametersService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FilterStocksByMultipleParametersUIAction implements UIAction {

    @Value("${filter.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${filter.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private FilterStocksByMultipleParametersService multipleParametersService;
    @Autowired private UtilityMethods utils;
    private final String[] parameters = {"industry", "market_price", "dividend_yield", "risk_weight", "none"};
    private final String[] operators = {">", ">=", "<", "<=", "="};
    private final String[] industries = new String[]{"Consumer Staples", "Utilities", "Communications",
            "Health Care", "Technology", "Materials", "Energy", "Financials", "Real Estate",
            "Industrials", "Consumer Discretionary"};

    @Override
    public void execute() {
//        try {
            List<String> finalParameterList =
                    setFilterParameters(Arrays.stream(parameters).collect(Collectors.toList()), new ArrayList<>());
            utils.messageDialog("You have chosen parameters:\n - " + printList(finalParameterList) + ";");
            List<CoreRequest> requestList = createRequestList(finalParameterList, operators, industries);
            FilterStocksByMultipleParametersRequest multiFilterRequest = new FilterStocksByMultipleParametersRequest(requestList);
            FilterStocksByMultipleParametersResponse response = multipleParametersService.execute(multiFilterRequest);
            printResponse(response);
            System.out.println("\n");
/*        } catch (NumberFormatException e) {
            utils.messageDialog("Wrong format!");
        }*/
    }

    private List<String> setFilterParameters(List<String> parameters, List<String> finalParameters) {
        String result = utils.inputDialog("Choose parameter:", "FILTER", parameters.toArray(String[]::new));
        if (result.equals("none") || result.isEmpty()) {
            return finalParameters;
        }
        finalParameters.add(result);
        parameters.remove(result);
        return setFilterParameters(parameters, finalParameters);
    }

    private List<CoreRequest> createRequestList(List<String> finalParameterList, String[] operators, String[] industries) {
        List<CoreRequest> requestList = new ArrayList<>();
        if (finalParameterList.isEmpty()) {
            return requestList;
        }
        addDoubleParametersRequests(requestList, finalParameterList, operators);
        addIndustryParameterRequest(requestList, finalParameterList, industries);
        if (orderingEnabled) {
            addOrderingRequest(requestList);
        }
        if (pagingEnabled) {
            addPagingRequest(requestList);
        }
        return requestList;
    }

    private void addDoubleParametersRequests(List<CoreRequest> requestList,
                                             List<String> finalParameterList, String[] operators) {
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .filter(i -> !finalParameterList.get(i).equals("industry"))
                .forEach(i -> requestList.add(new FilterStocksByAnyDoubleParameterRequest(
                        finalParameterList.get(i),
                        utils.inputDialog("Choose operator:", "PARAMETER " + finalParameterList.get(i), operators),
                        utils.inputDialog("Enter target amount for " + finalParameterList.get(i) + ":")
                )));
    }

    private void addIndustryParameterRequest(List<CoreRequest> requestList,
                                             List<String> finalParameterList, String[] industries) {
        IntStream.rangeClosed(0, finalParameterList.size() - 1)
                .filter(i -> finalParameterList.get(i).equals("industry"))
                .forEach(i -> requestList.add(new FilterStocksByIndustryRequest(
                        utils.inputDialog("Choose industry:", "FILTER", industries)
                )));
    }

    private void addOrderingRequest(List<CoreRequest> requestList) {
        String orderBy = utils.inputDialog(
                "Choose parameter:", "ORDERING", new String[]
                        {"name", "industry", "currency", "market_price", "dividend_yield", "risk_weight"});
        String orderDirection = utils.inputDialog(
                "Choose ordering direction:", "ORDERING", new String[]{"ASC", "DESC"}
        );
        if (!orderBy.isEmpty() && !orderDirection.isEmpty()) {
            requestList.add(new OrderingRequest(orderBy, orderDirection));
        }
    }

    private void addPagingRequest(List<CoreRequest> requestList) {
        String pageNumber = utils.inputDialog("Enter page number:");
        String pageSize = utils.inputDialog("Enter page size:");
        requestList.add(new PagingRequest(pageNumber, pageSize));
    }

    private String printList(List<String> list) {
        return String.join(";\n - ", list);
    }

    private void printResponse(FilterStocksByMultipleParametersResponse response) {
        if (response.hasErrors()) {
            utils.messageDialog("FAILED TO FILTER!\n" +
                    utils.printErrorList(response));
        } else {
            System.out.println("LIST STARTS:");
            response.getList().forEach(System.out::println);
            System.out.println("LIST ENDS");
        }
    }

}