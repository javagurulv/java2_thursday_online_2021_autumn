package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.database.UserData;
import lv.javaguru.java2.qwe.request.AddUserRequest;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotDouble;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotInteger;

public class AddUserValidator {

    private UserData userData;

    public AddUserValidator(UserData userData) {
        this.userData = userData;
    }

    private final Map<Predicate<AddUserRequest>, String> validator = Map.ofEntries(
            entry(request -> request.getName().length() < 3 || request.getName().length() > 100,
                    "Name: 3 to 100 symbols required!"),
            entry(request -> userData.findUserByName(request.getName()).isPresent(),
                    "Name: user with such name already exists in database!"),
            entry(request -> isNotInteger(request.getAge()),
                    "Age: wrong format! Must be integer!"),
            entry(request -> !isNotInteger(request.getAge()) && Integer.parseInt(request.getAge()) < 16,
                    "Age: user of minimum 16 years old is allowed!"),
            entry(request -> !isNotInteger(request.getAge()) && Integer.parseInt(request.getAge()) > 130,
                    "Age: user of maximum 130 years old is allowed!"),
            entry(request -> request.getType().isEmpty(),
                    "Type: is empty"),
            entry(request -> isNotDouble(request.getInitialInvestment()),
                    "Initial investment: wrong format! Must be double!"),
            entry(request -> !isNotDouble(request.getInitialInvestment()) && Double.parseDouble(request.getInitialInvestment()) < 10_000,
                    "Initial investment: minimum investment is 10,000.00 USD"),
            entry(request -> !isNotDouble(request.getInitialInvestment()) && Double.parseDouble(request.getInitialInvestment()) > 100_000_000,
                    "Initial investment: maximum investment is 100,000,000.00 USD")
    );

    public List<String> validate(AddUserRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}