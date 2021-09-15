package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.*;

public class ShowUserInvestmentsByEachIndustryValidator {

    private final Map<Predicate<ShowUserInvestmentsByEachIndustryRequest>, CoreError> validator = ofEntries(
            entry(request -> request.getUserName().equals(""),
                    new CoreError("UserName", "must not be empty!"))
    );

    public List<CoreError> validate(ShowUserInvestmentsByEachIndustryRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}