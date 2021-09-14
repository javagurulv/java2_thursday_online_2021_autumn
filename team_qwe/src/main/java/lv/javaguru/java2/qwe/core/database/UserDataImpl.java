package lv.javaguru.java2.qwe.core.database;

import lv.javaguru.java2.qwe.*;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static lv.javaguru.java2.qwe.Type.*;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.round;

public class UserDataImpl implements UserData {

    private final List<User> userList;
    private final Database database;

    public UserDataImpl(Database database) {
        this.userList = new ArrayList<>();
        this.database = database;
        userList.add(new User("Alexander", 25, SUPER_RICH, 1_000_000));
        userList.add(new User("Tatyana", 32, UPPER_MIDDLE, 125_000));
        userList.add(new User("Vladimir", 78, LOWER_MIDDLE, 30_000));
        userList.add(new User("John", 55, MIDDLE, 50_000));
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public boolean removeUser(String name) {
        return userList.removeIf(user -> user.getName().equals(name));
    }

    @Override
    public List<User> showUserList() {
        return getUserList();
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        return userList.stream()
                .filter(user -> user.getName().equals(userName))
                .findAny();
    }

    @Override
    public void showUserPortfolioGroupedByIndustry(User user) {
        Map<String, List<String>> map = user.getPortfolio().stream()
                .map(Position::getSecurity)
                .collect(groupingBy(Security::getIndustry, mapping(Security::getName, toList())));
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("\n");
    }

    @Override
    public void showUserInvestmentsByEachIndustry(User user) {
        Map<String, Double> map = user.getPortfolio().stream()
                .collect(groupingBy(position -> position.getSecurity().getIndustry(),
                        summingDouble(security -> security.getAmount() * security.getPurchasePrice())));
        map.forEach((key, value) -> System.out.println(key + ": " + round(value)));
        System.out.println("\n");
    }

    @Override
    public void showPortfolioSummary(User user) {
        double portfolioValue = calculatePortfolioValue(user);
        Map<String, Double> portfolioAllocation = calculatePortfolioAllocation(user, portfolioValue);
        double portfolioAverageWeightedDividendYield = calculateAvgWgtDividendYield(user, portfolioValue);
        double portfolioAverageWeightedRiskWeight = calculateAvgWgtRiskWeight(user, portfolioValue);

        System.out.println("===============PORTFOLIO SUMMARY======================");
        System.out.println("USER NAME: " + user.getName());
        System.out.println("USER RISK TOLERANCE LEVEL: " + user.getRiskTolerance());
        System.out.println("USER INITIAL INVESTMENT: " + user.getInitialInvestment());
        System.out.println("RETURN SINCE INCEPTION: " + round((portfolioValue / user.getInitialInvestment() - 1) * 100) + "%");
        System.out.println("PORTFOLIO VALUE: " + round(portfolioValue));
        System.out.println("AMOUNT OF POSITIONS: " + calculateAmountOfPosition(user));
        System.out.println("PORTFOLIO ALLOCATION:");
        portfolioAllocation.forEach((key, value) -> System.out.println(key + ": " + round(value * 100) + "%"));
        System.out.println("AVERAGE WEIGHTED DIVIDEND YIELD: " + round(portfolioAverageWeightedDividendYield) + "%");
        System.out.println("AVERAGE WEIGHTED RISK WEIGHT: " + round(portfolioAverageWeightedRiskWeight));
        System.out.println("======================================================");
    }

    private double calculatePortfolioValue(User user) {
        return user.getPortfolio().stream()
                .map(position -> position.getAmount() * position.getSecurity().getMarketPrice())
                .reduce(Double::sum).orElse(0.);
    }

    private int calculateAmountOfPosition(User user) {
        return user.getPortfolio().stream()
                .map(position -> 1)
                .reduce(Integer::sum).orElse(0);
    }

    private Map<String, Double> calculatePortfolioAllocation(User user, double portfolioValue) {
        return user.getPortfolio().stream()
                .collect(groupingBy(position -> position.getSecurity().getIndustry(),
                        summingDouble(position ->
                                (position.getAmount() * position.getSecurity().getMarketPrice()) / portfolioValue
                        )));
    }

    private double calculateAvgWgtDividendYield(User user, double portfolioValue) {
        return user.getPortfolio().stream()
                .filter(position -> position.getSecurity().getClass().getSimpleName().equals("Stock"))
                .map(position -> ((position.getAmount() * position.getSecurity().getMarketPrice()) / portfolioValue) *
                        Stream.of(position)
                                .map(position1 -> (Stock) position1.getSecurity())
                                .map(Stock::getDividends)
                                .findAny().orElse(0.))
                .reduce(Double::sum).orElse(0.);
    }

    private double calculateAvgWgtRiskWeight(User user, double portfolioValue) {
        return user.getPortfolio().stream()
                .filter(position -> position.getSecurity().getClass().getSimpleName().equals("Stock"))
                .map(position -> ((position.getAmount() * position.getSecurity().getMarketPrice()) / portfolioValue) *
                        Stream.of(position)
                                .map(position1 -> (Stock) position1.getSecurity())
                                .map(Stock::getRiskWeight)
                                .findAny().orElse(0.))
                .reduce(Double::sum).orElse(0.);
    }

}