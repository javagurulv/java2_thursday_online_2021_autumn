package lv.javaguru.java2.qwe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static lv.javaguru.java2.qwe.Type.*;

class UserDataImpl implements UserData {

    private final List<User> userList;
    private final Database database;

    public UserDataImpl(Database database) {
        this.userList = new ArrayList<>();
        this.database = database;
        userList.add(new User("Alexander", 40, SUPER_RICH, 1_000_000));
        userList.add(new User("Tatyana", 32, UPPER_MIDDLE, 125_000));
        userList.add(new User("Vladimir", 78, LOWER_MIDDLE, 30_000));
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public void generatePortfolio(User user) {

        Map<String, Double> investmentPolicy = calculateInvestmentPolicy(user);

        Map<String, Double> investmentPerIndustry = investmentPolicy.entrySet().stream()
                .collect(toMap(Map.Entry::getKey,
                        doubles -> user.getInitialInvestment() * (doubles.getValue() / 100)));

        Map<String, List<Security>> listPerIndustry = investmentPerIndustry.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, doubles ->
                        database.getSecurityList().stream()
                                .filter(security -> security.getIndustry().equals(doubles.getKey()))
                                .limit(5)
                                .collect(Collectors.toList())
                ));

        List<Position> userPortfolio = listPerIndustry.entrySet().stream()
                .map(entry -> IntStream.rangeClosed(0, entry.getValue().size() - 1)
                        .mapToObj(i -> new Position(
                                entry.getValue().get(i),
                                (investmentPerIndustry.get(entry.getKey()) / entry.getValue().size()) /
                                        entry.getValue().get(i).getMarketPrice(),
                                entry.getValue().get(i).getMarketPrice()
                        ))
                        .collect(toList()))
                .flatMap(List::stream)
                .collect(toList());

        double portfolioTotalValue = userPortfolio.stream()
                .map(position -> position.getAmount() * position.getPurchasePrice())
                .reduce(Double::sum).orElse(0.);

        userPortfolio.add(new Position(
                new Cash(),
                user.getInitialInvestment() - portfolioTotalValue,
                1
        ));

        user.setPortfolio(userPortfolio);

    }

    @Override
    public void showListOfUsers(List<User> list) {
        list.forEach(System.out::println);
        System.out.print("\n");
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        return userList.stream()
                .filter(user -> user.getName().equals(userName))
                .findAny();
    }

    @Override
    public void showUserPortfolio(User user) {
        user.getPortfolio().forEach(System.out::println);
        System.out.println("\n");
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
        map.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("\n");
    }

    private Map<String, Double> calculateInvestmentPolicy(User user) {
        return user.getDistribution().entrySet().stream()
                .collect(toMap(Map.Entry::getKey,
                        doubles -> doubles.getValue()[user.getRiskTolerance() - 1]));
    }

}