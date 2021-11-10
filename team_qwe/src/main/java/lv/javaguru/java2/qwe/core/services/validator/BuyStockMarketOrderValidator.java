package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.BuyStockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Component
public class BuyStockMarketOrderValidator {

    @Autowired private Database database;
    @Autowired private UserData userData;
    @Autowired private UtilityMethods utils;

    private final Map<Predicate<BuyStockMarketOrderRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getSecurity().getTicker().length() < 1 || request.getSecurity().getTicker().length() > 10,
                    new CoreError("Ticker", "1 to 10 symbols required!")),
            entry(request -> request.getSecurity() == null,
                    new CoreError("Security", "no security with such id in the database!")),
            entry(request -> request.getUser() == null,
                    new CoreError("User", "no user with such name exists in the database!")),
            entry(request -> utils.isNotDouble(request.getQuantity()),
                    new CoreError("Quantity", "must be double!")),
            entry(request -> request.getUser() != null && (Double.parseDouble(request.getQuantity()) * request.getRealTimePrice()) > request.getUser().getCash(),
                    new CoreError("Cash", "no enough money to execute this trade!"))
    );

    public List<CoreError> validate(BuyStockMarketOrderRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}