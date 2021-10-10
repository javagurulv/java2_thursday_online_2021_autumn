package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.*;

@Component
public class GetUserInvestmentsByEachIndustryValidator {

    private final Map<Predicate<GetUserInvestmentsByEachIndustryRequest>, CoreError> validator = ofEntries(
            entry(request -> request.getUserName().equals(""),
                    new CoreError("UserName", "must not be empty!"))
    );

    public List<CoreError> validate(GetUserInvestmentsByEachIndustryRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}