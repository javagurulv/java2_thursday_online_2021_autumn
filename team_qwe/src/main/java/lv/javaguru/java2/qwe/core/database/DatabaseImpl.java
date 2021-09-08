package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.Cash;
import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class DatabaseImpl implements Database {

    private final ArrayList<Security> securityList;

    public DatabaseImpl() {
        this.securityList = new ArrayList<>();
        securityList.add(new Cash());
    }

    @Override
    public ArrayList<Security> getSecurityList() {
        return securityList;
    }

    @Override
    public void addStock(Stock stock) {
        securityList.add(stock);
    }

    @Override
    public void addBond(Bond bond) {
        securityList.add(bond);
    }

    @Override
    public void removeSecurity(String name) {
        boolean isRemoved = securityList.removeIf(security -> security.getName().equals(name));
        if (isRemoved) {
            messageDialog("Security " + name + " has been removed!");
        } else {
            messageDialog("No such security in the list!");
        }
    }

    @Override
    public void showListOfSecurities(List<Security> list) {
        list.forEach(System.out::println);
        System.out.print("\n");
    }

    @Override
    public Optional<Security> findSecurityByName(String name) {
        return securityList.stream()
                .filter(security -> security.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Security> filterStocksByAnyDoubleParameter(String parameter, String operator, double target)
            throws NumberFormatException {
        Map<String, Predicate<Security>> map;
        switch (parameter) {
            case "Market price" -> map = getMarketPricePredicate(target);
            case "Dividend" -> map = getDividendPredicate(target);
            default -> map = getRiskWeightPredicate(target);
        }

        return securityList.stream()
                .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                .map(security -> (Stock) security)
                .filter(map.get(operator))
                .collect(Collectors.toList());
    }

    @Override
    public List<Security> filterStocksByIndustry(String industry) {
        return securityList.stream()
                .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                .filter(security -> security.getIndustry().equals(industry))
                .map(security -> (Stock) security)
                .collect(Collectors.toList());
    }

    private Map<String, Predicate<Security>> getMarketPricePredicate(double target) {
        return Map.ofEntries(
                entry("", security -> true),
                entry(">", security -> security.getMarketPrice() > target),
                entry(">=", security -> security.getMarketPrice() >= target),
                entry("<", security -> security.getMarketPrice() < target),
                entry("<=", security -> security.getMarketPrice() <= target),
                entry("=", security -> security.getMarketPrice() == target)
        );
    }

    private Map<String, Predicate<Security>> getDividendPredicate(double target) {
        return Map.ofEntries(
                entry("", security -> true),
                entry(">", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getDividends() > target)),
                entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getDividends() >= target)),
                entry("<", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getDividends() < target)),
                entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getDividends() <= target)),
                entry("=", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getDividends() == target))
        );
    }

    private Map<String, Predicate<Security>> getRiskWeightPredicate(double target) {
        return Map.ofEntries(
                entry("", security -> true),
                entry(">", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getRiskWeight() > target)),
                entry(">=", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getRiskWeight() >= target)),
                entry("<", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getRiskWeight() < target)),
                entry("<=", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getRiskWeight() <= target)),
                entry("=", security -> Stream.of(security).map(stock -> (Stock) stock)
                        .anyMatch(stock -> stock.getRiskWeight() == target))
        );
    }

}