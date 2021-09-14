package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class FindSecurityByNameValidator {

    private final Map<Predicate<FindSecurityByNameRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getName().length() < 3,
                    new CoreError("Name", "minimum 3 symbols required!"))
    );

    public List<CoreError> validate(FindSecurityByNameRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}