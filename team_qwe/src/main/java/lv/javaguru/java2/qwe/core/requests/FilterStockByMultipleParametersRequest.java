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

    private final List<SecurityRequest> requestList;
    private final List<Predicate<Security>> list = new ArrayList<>();
    private String industryTarget;
    private double marketPriceTarget;
    private double dividendTarget;
    private double riskWeightTarget;
    private final Map<String, Map<String, Predicate<Security>>> map = Map.ofEntries(
            Map.entry("Industry", Map.ofEntries(Map.entry("Industry", security -> security.getIndustry().equals(industryTarget)))),
            Map.entry("Market price", Map.ofEntries(
                    entry("", security -> true),
                    entry(">", security -> security.getMarketPrice() > marketPriceTarget),
                    entry(">=", security -> security.getMarketPrice() >= marketPriceTarget),
                    entry("<", security -> security.getMarketPrice() < marketPriceTarget),
                    entry("<=", security -> security.getMarketPrice() <= marketPriceTarget),
                    entry("=", security -> security.getMarketPrice() == marketPriceTarget)
            )),
            Map.entry("Dividend", Map.ofEntries(
                    entry("", security -> true),
                    entry(">", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() > dividendTarget)),
                    entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() >= dividendTarget)),
                    entry("<", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() < dividendTarget)),
                    entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() <= dividendTarget)),
                    entry("=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() == dividendTarget))
            )),
            Map.entry("Risk weight", Map.ofEntries(
                    entry("", security -> true),
                    entry(">", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() > riskWeightTarget)),
                    entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() >= riskWeightTarget)),
                    entry("<", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() < riskWeightTarget)),
                    entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() <= riskWeightTarget)),
                    entry("=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() == riskWeightTarget))
            )));


    public FilterStockByMultipleParametersRequest(List<SecurityRequest> requestList) throws NumberFormatException {
        this.requestList = requestList;
        setTargetsForDouble();
        setIndustryTarget();
        IntStream.rangeClosed(0, requestList.size() - 1)
                .filter(i -> requestList.get(i).getClass().getSimpleName().equals("FilterStockByAnyDoubleParameterRequest"))
                .mapToObj(i -> (FilterStockByAnyDoubleParameterRequest) requestList.get(i))
                .filter(request -> request.getParameter() != null)
                .forEach(request -> list.add(findPredicateForDouble(request)));
        IntStream.rangeClosed(0, requestList.size() - 1)
                .filter(i -> requestList.get(i).getClass().getSimpleName().equals("FilterStockByIndustryRequest"))
                .mapToObj(i -> (FilterStockByIndustryRequest) requestList.get(i))
                .filter(request -> request.getIndustry() != null)
                .forEach(request -> list.add(findPredicateForIndustry(request)));
    }

    public List<Predicate<Security>> getList() {
        return list;
    }

    public String getIndustryTarget() {
        return industryTarget;
    }

    public double getMarketPriceTarget() {
        return marketPriceTarget;
    }

    public double getDividendTarget() {
        return dividendTarget;
    }

    public double getRiskWeightTarget() {
        return riskWeightTarget;
    }

    private void setTargetsForDouble() throws NumberFormatException {
        requestList.stream()
                .filter(request -> request.getClass().getSimpleName().equals("FilterStockByAnyDoubleParameterRequest"))
                .map(request -> (FilterStockByAnyDoubleParameterRequest) request)
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

    private void setIndustryTarget() {
        requestList.stream()
                .filter(request -> request.getClass().getSimpleName().equals("FilterStockByIndustryRequest"))
                .map(request -> (FilterStockByIndustryRequest) request)
                .filter(request -> request.getIndustry() != null)
                .forEach(request -> industryTarget = request.getIndustry());
    }

    private Predicate<Security> findPredicateForDouble(FilterStockByAnyDoubleParameterRequest request) {
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

    private Predicate<Security> findPredicateForIndustry(FilterStockByIndustryRequest request) {
        if (request.getIndustry() == null) {
            return null;
        } else {
            return map.entrySet().stream()
                    .filter(entry -> entry.getKey().equals("Industry"))
                    .map(Map.Entry::getValue)
                    .map(entry1 -> entry1.get("Industry"))
                    .findAny().get();
        }
    }

}