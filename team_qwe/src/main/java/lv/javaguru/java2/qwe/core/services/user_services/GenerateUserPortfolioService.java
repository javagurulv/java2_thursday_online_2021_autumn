package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.*;
import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GenerateUserPortfolioValidator;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.Map.*;

@Component
public class GenerateUserPortfolioService {

    @Autowired private UserData userData;
    @Autowired private Database database;
    @Autowired private GenerateUserPortfolioValidator validator;
    @Autowired private UtilityMethods utils;

    public UserData getUserData() {
        return userData;
    }

    public GenerateUserPortfolioResponse execute(GenerateUserPortfolioRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<User> user = userData.findUserByIdOrName(request.getUserName());
        if (user.isPresent() && user.get().getPortfolio().size() > 0) {
            errors.add(new CoreError("", "portfolio has been already generated for this user!"));
            return new GenerateUserPortfolioResponse(errors, user.get());
        }
        if (user.isPresent() && user.get().getPortfolio().size() == 0) {
            User user1 = user.get();
            Map<String, Double> investmentPolicy = calculateInvestmentPolicy(user1);
            Map<String, Double> investmentPerIndustry = calculateInvestmentPerIndustry(user1, investmentPolicy);
            Map<String, List<Security>> listPerIndustry = calculateListOfSecuritiesPerIndustry(user1, investmentPerIndustry);
            List<Position> userPortfolio = generateUserPortfolio(listPerIndustry, investmentPerIndustry);
            addPortfolioToDatabaseSQL(userPortfolio, user1); // сохраняет сгенерированный портфель в базу данных
            user1.setPortfolio(userPortfolio);
            user1.setCash(userData.getUserCash(user1.getId()).get());
            user1.setPortfolioGenerationDate(userData.getCurrentDate());
            return new GenerateUserPortfolioResponse(user1);
        } else {
            return new GenerateUserPortfolioResponse(errors, user.get());
        }
    }

    private Map<String, Double> calculateInvestmentPolicy(User user) {
        return user.getDistribution().entrySet().stream()
                .collect(toMap(Entry::getKey,
                        doubles -> doubles.getValue()[user.getRiskTolerance() - 1]));
    }

    private Map<String, Double> calculateInvestmentPerIndustry(User user, Map<String, Double> investmentPolicy) {
        return investmentPolicy.entrySet().stream()
                .collect(toMap(Entry::getKey,
                        doubles -> user.getInitialInvestment() * (doubles.getValue() / 100)));
    }

    private Map<String, List<Security>> calculateListOfSecuritiesPerIndustry(User user, Map<String, Double> investmentPerIndustry) {
        return investmentPerIndustry.entrySet().stream()
                .collect(toMap(Entry::getKey, doubles ->
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
                                utils.convertToInt(utils.round((investmentPerIndustry.get(entry.getKey()) / entry.getValue().size()) /
                                        entry.getValue().get(i).getMarketPrice())),
                                entry.getValue().get(i).getMarketPrice()
                        ))
                        .collect(toList()))
                .flatMap(List::stream)
                .collect(toList());
    }

    private Map<Integer, List<Security>> securitiesForRiskGroups() {
        return ofEntries(
                entry(1, database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                        "  WHERE dividend_yield > 3\n" +
                        "    AND risk_weight < 1.2\n" + "  ORDER BY risk_weight")),
                entry(2, database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                        "  WHERE dividend_yield > 2\n" +
                        "    AND risk_weight < 1.2\n" + "  ORDER BY risk_weight DESC")),
                entry(3, database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                        "  WHERE dividend_yield > 1\n" +
                        "    AND risk_weight < 1")),
                entry(4, database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                        "  WHERE dividend_yield < 1.5\n" +
                        "    AND risk_weight > 1.1\n" + "  ORDER BY risk_weight DESC")),
                entry(5, database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                        "  ORDER BY risk_weight DESC"))
        );
    }

    private void addPortfolioToDatabaseSQL(List<Position> portfolio, User user) {
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .forEach(i -> userData.savePosition(portfolio.get(i), user.getId()));
    }

}