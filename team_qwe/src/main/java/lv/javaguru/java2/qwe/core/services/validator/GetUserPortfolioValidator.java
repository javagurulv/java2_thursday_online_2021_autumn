package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@DIComponent
public class GetUserPortfolioValidator {

    private final Map<Predicate<GetUserPortfolioRequest>, CoreError> validator = ofEntries(
            entry(request -> request.getUserName().equals(""),
                    new CoreError("UserName", "must not be empty!"))
    );

    public List<CoreError> validate(GetUserPortfolioRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}