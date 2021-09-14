package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.*;

public class FindUserByNameValidator {

    private final Map<Predicate<FindUserByNameRequest>, CoreError> validator = ofEntries(
            entry(request -> request.getUserName().length() < 3,
                    new CoreError("Name", "minimum 3 symbols required!"))
    );

    public List<CoreError> validate(FindUserByNameRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}