package lv.javaguru.java2.qwe.core.requests.data_requests;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Map.*;

public class FilterStocksByMultipleParametersRequest {

    private final List<CoreRequest> requestList;
    private final List<Predicate<Security>> list = new ArrayList<>();
    private String orderBy = "";
    private String orderDirection = "";
    private int pageNumber;
    private int pageSize;
    private String industryTarget;
    private double marketPriceTarget;
    private double dividendTarget;
    private double riskWeightTarget;
    private final Map<String, Map<String, Predicate<Security>>> predicateMap;

    public FilterStocksByMultipleParametersRequest(List<CoreRequest> requestList) throws NumberFormatException {
        this.requestList = requestList;
        this.predicateMap = getPredicateMap();
        setTargetsForDoubleParameters();
        setTargetForIndustryParameter();
        setPredicatesForDoubleParameters();
        setPredicateForIndustryParameter();
        setOrdering();
        setPaging();
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

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    private void setTargetsForDoubleParameters() throws NumberFormatException {
        requestList.stream()
                .filter(request -> request.getClass().getSimpleName().equals("FilterStocksByAnyDoubleParameterRequest"))
                .map(request -> (FilterStocksByAnyDoubleParameterRequest) request)
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

    private void setTargetForIndustryParameter() {
        requestList.stream()
                .filter(request -> request.getClass().getSimpleName().equals("FilterStocksByIndustryRequest"))
                .map(request -> (FilterStocksByIndustryRequest) request)
                .filter(request -> request.getIndustry() != null)
                .forEach(request -> industryTarget = request.getIndustry());
    }

    private void setPredicatesForDoubleParameters() {
        IntStream.rangeClosed(0, requestList.size() - 1)
                .filter(i -> requestList.get(i).getClass().getSimpleName().equals("FilterStocksByAnyDoubleParameterRequest"))
                .mapToObj(i -> (FilterStocksByAnyDoubleParameterRequest) requestList.get(i))
                .filter(request -> request.getParameter() != null)
                .forEach(request -> list.add(findPredicateForDouble(request)));
    }

    private void setPredicateForIndustryParameter() {
        IntStream.rangeClosed(0, requestList.size() - 1)
                .filter(i -> requestList.get(i).getClass().getSimpleName().equals("FilterStocksByIndustryRequest"))
                .mapToObj(i -> (FilterStocksByIndustryRequest) requestList.get(i))
                .filter(request -> request.getIndustry() != null)
                .forEach(request -> list.add(findPredicateForIndustry(request)));
    }

    private void setOrdering() {
        requestList.stream()
                .filter(request -> request.getClass().getSimpleName().equals("OrderingRequest"))
                .map(request -> (OrderingRequest) request)
                .filter(request -> request.getOrderBy() != null && request.getOrderDirection() != null)
                .forEach(request -> {
                    orderBy = request.getOrderBy();
                    orderDirection = request.getOrderDirection();
                });
    }

    private void setPaging() throws NumberFormatException {
        requestList.stream()
                .filter(request -> request.getClass().getSimpleName().equals("PagingRequest"))
                .map(request -> (PagingRequest) request)
                .forEach(request -> {
                    if (!request.getPageNumber().isEmpty()) {
                        pageNumber = Integer.parseInt(request.getPageNumber());
                    }
                    if (!request.getPageSize().isEmpty()) {
                        pageSize = Integer.parseInt(request.getPageSize());
                    }
                });
    }

    private Predicate<Security> findPredicateForDouble(FilterStocksByAnyDoubleParameterRequest request) {
        if (request.getParameter() == null) {
            return null;
        } else {
            return predicateMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(request.getParameter()))
                    .map(Entry::getValue)
                    .flatMap(entry1 -> entry1.entrySet().stream()
                            .filter(entry2 -> entry2.getKey().equals(request.getOperator()))
                            .map(Entry::getValue))
                    .findAny().get();
        }
    }

    private Predicate<Security> findPredicateForIndustry(FilterStocksByIndustryRequest request) {
        if (request.getIndustry() == null) {
            return null;
        } else {
            return predicateMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equals("Industry"))
                    .map(Entry::getValue)
                    .map(entry1 -> entry1.get("Industry"))
                    .findAny().get();
        }
    }

    private Map<String, Map<String, Predicate<Security>>> getPredicateMap() {
        return ofEntries(
                entry("Industry", ofEntries(entry("Industry", security -> security.getIndustry().equals(industryTarget)))),
                entry("Market price", ofEntries(
                        entry("", security -> true),
                        entry(">", security -> security.getMarketPrice() > marketPriceTarget),
                        entry(">=", security -> security.getMarketPrice() >= marketPriceTarget),
                        entry("<", security -> security.getMarketPrice() < marketPriceTarget),
                        entry("<=", security -> security.getMarketPrice() <= marketPriceTarget),
                        entry("=", security -> security.getMarketPrice() == marketPriceTarget)
                )),
                entry("Dividend", ofEntries(
                        entry("", security -> true),
                        entry(">", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() > dividendTarget)),
                        entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() >= dividendTarget)),
                        entry("<", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() < dividendTarget)),
                        entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() <= dividendTarget)),
                        entry("=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getDividends() == dividendTarget))
                )),
                entry("Risk weight", ofEntries(
                        entry("", security -> true),
                        entry(">", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() > riskWeightTarget)),
                        entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() >= riskWeightTarget)),
                        entry("<", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() < riskWeightTarget)),
                        entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() <= riskWeightTarget)),
                        entry("=", security -> Stream.of(security).map(stock -> (Stock) stock).anyMatch(stock -> stock.getRiskWeight() == riskWeightTarget))
                ))
        );
    }

}