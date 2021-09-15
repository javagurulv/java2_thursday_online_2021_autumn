package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.user_requests.ShowUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class ShowUserPortfolioSummaryValidator {

    private final Map<Predicate<ShowUserPortfolioSummaryRequest>, CoreError> validator = ofEntries(
            entry(request -> request.getUserName().equals(""),
                    new CoreError("UserName", "must not be empty!"))
    );

    public List<CoreError> validate(ShowUserPortfolioSummaryRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}