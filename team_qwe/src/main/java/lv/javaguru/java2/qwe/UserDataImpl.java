package lv.javaguru.java2.qwe;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static javax.swing.JOptionPane.showMessageDialog;
import static lv.javaguru.java2.qwe.Type.*;
import static lv.javaguru.java2.qwe.DatabaseImpl.inputDialog;
import static lv.javaguru.java2.qwe.DatabaseImpl.messageDialog;

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
    public void addUser() {
        try {
            userList.add(new User(
                    inputDialog("User name:"),
                    Integer.parseInt(inputDialog("User age:")),
                    valueOf(inputDialog("User type:", "TYPE", new String[]{
                            String.valueOf(LOWER_MIDDLE),
                            String.valueOf(MIDDLE),
                            String.valueOf(UPPER_MIDDLE),
                            String.valueOf(WEALTHY),
                            String.valueOf(SUPER_RICH)
                    })),
                    Double.parseDouble(inputDialog("Initial investment:"))
            ));
        } catch (NumberFormatException e) {
            showMessageDialog(null, "Wrong data!");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(String name) {
        boolean isRemoved = userList.removeIf(user -> user.getName().equals(name));
        if (isRemoved) {
            messageDialog("User " + name + " has been removed!");
        } else {
            messageDialog("No such user in the list!");
        }
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
                                .limit(2) // количество бумаг от каждой индустрии в портфеле клиента
                                .collect(Collectors.toList())
                ));

        List<Position> userPortfolio = listPerIndustry.entrySet().stream()
                .map(entry -> IntStream.rangeClosed(0, entry.getValue().size() - 1)
                        .mapToObj(i -> new Position(
                                entry.getValue().get(i),
                                convertToInt((investmentPerIndustry.get(entry.getKey()) / entry.getValue().size()) /
                                        entry.getValue().get(i).getMarketPrice()),
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
                round(user.getInitialInvestment() - portfolioTotalValue),
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
        map.forEach((key, value) -> System.out.println(key + ": " + round(value)));
        System.out.println("\n");
    }

    @Override
    public void showPortfolioSummary(User user) {
        String userName = user.getName();

        double portfolioValue = user.getPortfolio().stream()
                .map(position -> position.getAmount() * position.getSecurity().getMarketPrice())
                .reduce(Double::sum).orElse(0.);

        int amountOfPositions = user.getPortfolio().stream()
                .map(position -> 1)
                .reduce(Integer::sum).orElse(0);

        Map<String, Double> portfolioAllocation = user.getPortfolio().stream()
                .collect(groupingBy(position -> position.getSecurity().getIndustry(),
                        summingDouble(position ->
                                (position.getAmount() * position.getSecurity().getMarketPrice()) / portfolioValue
                        )));

        Double portfolioAverageWeightedDividendYield = user.getPortfolio().stream()
                .filter(position -> !position.getSecurity().getClass().getSimpleName().equals("Cash"))
                .map(position -> ((position.getAmount() * position.getSecurity().getMarketPrice()) / portfolioValue) *
                        Stream.of(position)
                                .map(position1 -> (Stock) position1.getSecurity())
                                .map(Stock::getDividends)
                                .findAny().orElse(0.))
                .reduce(Double::sum).orElse(0.);

        System.out.println("======================================================");
        System.out.println("<PORTFOLIO SUMMARY>");
        System.out.println("USER NAME: " + userName);
        System.out.println("AMOUNT OF POSITIONS: " + amountOfPositions);
        System.out.println("PORTFOLIO ALLOCATION:");
        portfolioAllocation.forEach((key, value) -> System.out.println(key + ": " + round(value * 100) + "%"));
        System.out.println("AVERAGE WEIGHTED DIVIDEND YIELD: " + round(portfolioAverageWeightedDividendYield) + "%");
        System.out.println("======================================================");
    }

    private Map<String, Double> calculateInvestmentPolicy(User user) {
        return user.getDistribution().entrySet().stream()
                .collect(toMap(Map.Entry::getKey,
                        doubles -> doubles.getValue()[user.getRiskTolerance() - 1]));
    }

    private int convertToInt(double amount) {
        return (int) Math.round(amount);
    }

    private double round(double amount) {
        return Math.round(amount * 100.) / 100.;
    }

}