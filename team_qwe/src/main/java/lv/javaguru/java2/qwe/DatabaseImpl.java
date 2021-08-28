package lv.javaguru.java2.qwe;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static java.util.Map.entry;

class DatabaseImpl implements Database {

    private final ArrayList<Security> securityList;

    DatabaseImpl() {
        this.securityList = new ArrayList<>();
        securityList.add(new Cash());
    }

    @Override
    public ArrayList<Security> getSecurityList() {
        return securityList;
    }

    @Override
    public void importSecurities(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path)).stream()
                .skip(1)
                .collect(Collectors.toList());
        importSecurities(lines);
    }

    @Override
    public void addSecurity(String type) {
        switch (type) {
            case "Stock" -> addStock();
            case "Bond" -> addBond();
            default -> System.out.println("None!");
        }
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
                .filter(security -> security.getName().startsWith(name))
                .findAny();
    }

    @Override
    public List<Security> filterStocksByAnyDoubleParameter(String parameter, String operator, double target)
            throws NumberFormatException {
        Map<String, Predicate<Security>> map;
        switch (parameter) {
            case "Market price" -> map = marketPricePredicate(target);
            case "Dividend" -> map = dividendPredicate(target);
            default -> map = riskWeightPredicate(target);
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

    private void addStock() {
        try {
            securityList.add(new Stock(
                    inputDialog("Security name"),
                    inputDialog("Industry"),
                    inputDialog("Currency"),
                    Double.parseDouble(inputDialog("Market price")),
                    Double.parseDouble(inputDialog("Dividend")),
                    Double.parseDouble(inputDialog("Risk weight"))));
        } catch (NumberFormatException e) {
            showMessageDialog(null, "Wrong data!");
            e.printStackTrace();
        }
    }

    private void addBond() {
        try {
            securityList.add(new Bond(
                    inputDialog("Security name"),
                    inputDialog("Industry"),
                    inputDialog("Currency"),
                    Double.parseDouble(inputDialog("Market price")),
                    Double.parseDouble(inputDialog("Coupon")),
                    inputDialog("Rating"),
                    Integer.parseInt(inputDialog("Nominal")),
                    inputDialog("Maturity")
            ));
        } catch (NumberFormatException e) {
            showMessageDialog(null, "Wrong data!");
            e.printStackTrace();
        }
    }

    private void importSecurities(List<String> rawData) {
        List<String[]> list = rawData.stream()
                .map(data -> data.split(","))
                .collect(Collectors.toList());
        importStocks(list);
        importBonds(list);
    }

    private void importStocks(List<String[]> list) {
        IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Stock"))
                .forEach(i -> securityList.add(new Stock(
                        list.get(i)[1],
                        list.get(i)[2],
                        list.get(i)[3],
                        Double.parseDouble(list.get(i)[4]),
                        Double.parseDouble(list.get(i)[5]),
                        Double.parseDouble(list.get(i)[6])
                )));
    }

    private void importBonds(List<String[]> list) {
        IntStream.rangeClosed(0, list.size() - 1)
                .filter(i -> list.get(i)[0].equals("Bond"))
                .forEach(i -> securityList.add(new Bond(
                        list.get(i)[1],
                        list.get(i)[2],
                        list.get(i)[3],
                        Double.parseDouble(list.get(i)[4]),
                        Double.parseDouble(list.get(i)[5]),
                        list.get(i)[6],
                        Integer.parseInt(list.get(i)[7]),
                        list.get(i)[8]
                )));
    }

    private Map<String, Predicate<Security>> marketPricePredicate(double target) {
        return Map.ofEntries(
                entry("", security -> true),
                entry(">", security -> security.getMarketPrice() > target),
                entry(">=", security -> security.getMarketPrice() >= target),
                entry("<", security -> security.getMarketPrice() < target),
                entry("<=", security -> security.getMarketPrice() <= target),
                entry("=", security -> security.getMarketPrice() == target)
        );
    }

    private Map<String, Predicate<Security>> dividendPredicate(double target) {
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

    private Map<String, Predicate<Security>> riskWeightPredicate(double target) {
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

    static String inputDialog(String text) {
        return Optional.ofNullable(showInputDialog(null, text)).orElse("");
    }

    static String inputDialog(String request, String title, String[] arr) {
        return Optional.ofNullable((String) showInputDialog(
                null, request,
                title, JOptionPane.QUESTION_MESSAGE, null,
                arr, arr[0])).orElse("");
    }

    static void messageDialog(String text) {
        showMessageDialog(null, text);
    }

}