package lv.javaguru.java2.qwe.core.requests;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class FilterStockByMultipleParametersRequest {

    private final List<FilterStockByAnyDoubleParameterRequest> requestList;
    private final List<Predicate<Security>> list = new ArrayList<>();
    private String industryTarget;
    private double marketPriceTarget;
    private double dividendTarget;
    private double riskWeightTarget;
    private final Map<String, Map<String, Predicate<Security>>> map = Map.ofEntries(
            Map.entry("Industry", Map.ofEntries(Map.entry("Industry", security -> security.getIndustry().equals(industryTarget)))),
            Map.entry("Market price", Map.ofEntries(
                    entry(">", security -> security.getMarketPrice() > marketPriceTarget),
                    entry(">=", security -> security.getMarketPrice() >= marketPriceTarget),
                    entry("<", security -> security.getMarketPrice() < marketPriceTarget),
                    entry("<=", security -> security.getMarketPrice() <= marketPriceTarget),
                    entry("=", security -> security.getMarketPrice() == marketPriceTarget)
            )),
            Map.entry("Dividend", Map.ofEntries(
                    entry(">", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() > dividendTarget)),
                    entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() >= dividendTarget)),
                    entry("<", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() < dividendTarget)),
                    entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() <= dividendTarget)),
                    entry("=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() == dividendTarget))
            )),
            Map.entry("Risk weight", Map.ofEntries(
                    entry(">", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() > riskWeightTarget)),
                    entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() >= riskWeightTarget)),
                    entry("<", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() < riskWeightTarget)),
                    entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() <= riskWeightTarget)),
                    entry("=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() == riskWeightTarget))
            )));


    public FilterStockByMultipleParametersRequest(List<FilterStockByAnyDoubleParameterRequest> requestList) {
        this.requestList = requestList;
        setTargets();
        IntStream.rangeClosed(0, requestList.size() - 1)
                .filter(i -> requestList.get(i).getParameter() != null)
                .forEach(i -> list.add(findPredicate(requestList.get(i))));
    }

    public List<Predicate<Security>> getList() {
        return list;
    }

    private void setTargets() {
        requestList.stream()
                .filter(request -> request.getParameter() != null)
                .forEach(request -> {
                    double targetValue = Double.parseDouble(request.getTargetAmount());
                    switch (request.getParameter()) {
                        case "Market price" -> marketPriceTarget = targetValue;
                        case "Dividend" -> dividendTarget = targetValue;
                        case "Risk weight" -> riskWeightTarget = targetValue;
                    }
                });
    }

    private Predicate<Security> findPredicate(FilterStockByAnyDoubleParameterRequest request) {
        if (request.getParameter() == null) {
            return null;
        } else {
            return map.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(request.getParameter()))
                    .map(Map.Entry::getValue)
                    .flatMap(entry1 -> entry1.entrySet().stream()
                            .filter(entry2 -> entry2.getKey().equals(request.getOperator()))
                            .map(Map.Entry::getValue))
                    .findAny().get();
        }
    }

    public static void main(String[] args) {

        List<FilterStockByAnyDoubleParameterRequest> requestList = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "100"),
                new FilterStockByAnyDoubleParameterRequest(null, null, null),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "0.8")
        );
        FilterStockByMultipleParametersRequest multiFilterRequest = new FilterStockByMultipleParametersRequest(requestList);
        System.out.println("RESULT: " + multiFilterRequest.list.size());
        System.out.println("MARKET PRICE TARGET: " + multiFilterRequest.marketPriceTarget);
        System.out.println("DIVIDEND TARGET: " + multiFilterRequest.dividendTarget);
        System.out.println("RISK WEIGHT TARGET: " + multiFilterRequest.riskWeightTarget);

    }


}