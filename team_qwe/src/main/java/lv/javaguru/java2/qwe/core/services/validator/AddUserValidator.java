package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
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
public class AddUserValidator {

    @Autowired private UserData userData;
    @Autowired private UtilityMethods utils;

    private final Map<Predicate<AddUserRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getName().length() < 3 || request.getName().length() > 100,
                    new CoreError("Name", "3 to 100 symbols required!")),
            entry(request -> userData.findUserByName(request.getName()).isPresent(),
                    new CoreError("Name", "user with such name already exists in database!")),
            entry(request -> utils.isNotInteger(request.getAge()),
                    new CoreError("Age", "wrong format! Must be integer!")),
            entry(request -> !utils.isNotInteger(request.getAge()) && Integer.parseInt(request.getAge()) < 16,
                    new CoreError("Age", "user of minimum 16 years old is allowed!")),
            entry(request -> !utils.isNotInteger(request.getAge()) && Integer.parseInt(request.getAge()) > 130,
                    new CoreError("Age", "user of maximum 130 years old is allowed!")),
            entry(request -> request.getType().isEmpty(),
                    new CoreError("Type", "is empty")),
            entry(request -> utils.isNotDouble(request.getInitialInvestment()),
                    new CoreError("Initial investment", "wrong format! Must be double!")),
            entry(request -> !utils.isNotDouble(request.getInitialInvestment()) && Double.parseDouble(request.getInitialInvestment()) < 10_000,
                    new CoreError("Initial investment", "minimum investment is 10,000.00 USD")),
            entry(request -> !utils.isNotDouble(request.getInitialInvestment()) && Double.parseDouble(request.getInitialInvestment()) > 100_000_000,
                    new CoreError("Initial investment", "maximum investment is 100,000,000.00 USD"))
    );

    public List<CoreError> validate(AddUserRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}