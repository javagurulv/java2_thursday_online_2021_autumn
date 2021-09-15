package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.*;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GenerateUserPortfolioValidator;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.*;

public class GeneratePortfolioService {

    private final UserData userData;
    private final GenerateUserPortfolioValidator validator;

    public GeneratePortfolioService(UserData userData, GenerateUserPortfolioValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public GenerateUserPortfolioResponse execute(GenerateUserPortfolioRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (user.isPresent() && user.get().getPortfolio().size() > 1) {
            errors.add(new CoreError("", "portfolio has been already generated for this user!"));
            List<Position> positions = new ArrayList<>();
            return new GenerateUserPortfolioResponse(errors, positions);
        }
        if (user.isPresent() && user.get().getPortfolio().size() == 1) {
            User user1 = user.get();
            Map<String, Double> investmentPolicy = calculateInvestmentPolicy(user1);
            Map<String, Double> investmentPerIndustry = calculateInvestmentPerIndustry(user1, investmentPolicy);
            Map<String, List<Security>> listPerIndustry = calculateListOfSecuritiesPerIndustry(user1, investmentPerIndustry);
            List<Position> userPortfolio = generateUserPortfolio(listPerIndustry, investmentPerIndustry);
            double portfolioTotalValue = calculatePortfolioTotalValue(userPortfolio);
            addCashResidual(user1, userPortfolio, portfolioTotalValue);
            user1.setPortfolio(userPortfolio);
            return new GenerateUserPortfolioResponse(userPortfolio);
//            messageDialog("Portfolio has been generated for " + user1.getName());
        } else {
            List<Position> positions = new ArrayList<>();
            return new GenerateUserPortfolioResponse(errors, positions);
//            messageDialog("FAILED to generate!\nPortfolio was already generated for this client!");
        }
    }

    private Map<String, Double> calculateInvestmentPolicy(User user) {
        return user.getDistribution().entrySet().stream()
                .collect(toMap(Map.Entry::getKey,
                        doubles -> doubles.getValue()[user.getRiskTolerance() - 1]));
    }

    private Map<String, Double> calculateInvestmentPerIndustry(User user, Map<String, Double> investmentPolicy) {
        return investmentPolicy.entrySet().stream()
                .collect(toMap(Map.Entry::getKey,
                        doubles -> user.getInitialInvestment() * (doubles.getValue() / 100)));
    }

    private Map<String, List<Security>> calculateListOfSecuritiesPerIndustry(User user, Map<String, Double> investmentPerIndustry) {
        return investmentPerIndustry.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, doubles ->
                        securitiesForRiskGroups().get(user.getRiskTolerance()).stream()
                                .filter(security -> security.getIndustry().equals(doubles.getKey()))
                                .limit(2) // количество бумаг от каждой индустрии в портфеле клиента
                                .collect(toList())
                ));
    }

    private List<Position> generateUserPortfolio(Map<String, List<Security>> listOfSecuritiesPerIndustry,
                                                 Map<String, Double> investmentPerIndustry) {
        return listOfSecuritiesPerIndustry.entrySet().stream()
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
    }

    private double calculatePortfolioTotalValue(List<Position> userPortfolio) {
        return userPortfolio.stream()
                .map(position -> position.getAmount() * position.getPurchasePrice())
                .reduce(Double::sum).orElse(0.);
    }

    private void addCashResidual(User user, List<Position> userPortfolio, double portfolioTotalValue) {
        userPortfolio.add(new Position(
                new Cash(),
                round(user.getInitialInvestment() - portfolioTotalValue),
                1
        ));
    }

    private Map<Integer, List<Security>> securitiesForRiskGroups() {
        return Map.ofEntries(
                Map.entry(1, userData.getDatabase().getSecurityList().stream()
                        .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                        .map(security -> (Stock) security)
                        .filter(stock -> stock.getDividends() > 3)
                        .filter(stock -> stock.getRiskWeight() < 1.2)
                        .sorted(Comparator.comparingDouble(Stock::getRiskWeight))
                        .collect(toList())),
                Map.entry(2, userData.getDatabase().getSecurityList().stream()
                        .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                        .map(security -> (Stock) security)
                        .filter(stock -> stock.getDividends() > 2)
                        .filter(stock -> stock.getRiskWeight() < 1.2)
                        .sorted(Comparator.comparingDouble(Stock::getRiskWeight).reversed())
                        .collect(toList())),
                Map.entry(3, userData.getDatabase().getSecurityList().stream()
                        .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                        .map(security -> (Stock) security)
                        .filter(stock -> stock.getDividends() > 1)
                        .filter(stock -> stock.getRiskWeight() < 1)
                        .collect(toList())),
                Map.entry(4, userData.getDatabase().getSecurityList().stream()
                        .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                        .map(security -> (Stock) security)
                        .filter(stock -> stock.getDividends() < 1.5)
                        .filter(stock -> stock.getRiskWeight() > 1.1)
                        .sorted(Comparator.comparingDouble(Stock::getRiskWeight).reversed())
                        .collect(toList())),
                Map.entry(5, userData.getDatabase().getSecurityList().stream()
                        .filter(security -> security.getClass().getSimpleName().equals("Stock"))
                        .map(security -> (Stock) security)
                        .sorted(Comparator.comparingDouble(Stock::getRiskWeight).reversed())
                        .collect(toList()))
        );
    }

}