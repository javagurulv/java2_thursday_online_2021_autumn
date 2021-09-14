package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.ShowUserPortfolioSummaryResponse;
import lv.javaguru.java2.qwe.core.services.validator.ShowUserPortfolioSummaryValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class ShowPortfolioSummaryService {

    private final UserData userData;
    private final ShowUserPortfolioSummaryValidator validator;

    public ShowPortfolioSummaryService(UserData userData, ShowUserPortfolioSummaryValidator validator) {
        this.userData = userData;
        this.validator = validator;
    }

    public UserData getUserData() {
        return userData;
    }

    public ShowUserPortfolioSummaryResponse execute(ShowUserPortfolioSummaryRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByName(request.getUserName());
        if (errors.isEmpty() && user.isPresent()) {
            double portfolioValue = calculatePortfolioValue(user.get());
            int amountOfPositions = calculateAmountOfPosition(user.get());
            Map<String, Double> portfolioAllocation = calculatePortfolioAllocation(user.get(), portfolioValue);
            double avgWgtDividendYield = calculateAvgWgtDividendYield(user.get(), portfolioValue);
            double avgWgtRiskWeight = calculateAvgWgtRiskWeight(user.get(), portfolioValue);
            return new ShowUserPortfolioSummaryResponse(user.get().getRiskTolerance(),
                    user.get().getInitialInvestment(), portfolioValue, amountOfPositions,
                    portfolioAllocation, avgWgtDividendYield, avgWgtRiskWeight);
        }
        return new ShowUserPortfolioSummaryResponse(errors);
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