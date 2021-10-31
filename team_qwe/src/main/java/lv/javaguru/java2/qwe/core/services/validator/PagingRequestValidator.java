package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.PagingRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class PagingRequestValidator {

    private final Map<Predicate<PagingRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getPageNumber().isEmpty() && !request.getPageSize().isEmpty(),
                    new CoreError("Ordering", "both fields must be empty or filled!")),
            entry(request -> !request.getPageNumber().isEmpty() && request.getPageSize().isEmpty(),
                    new CoreError("Ordering", "both fields must be empty or filled!"))
    );

    public List<CoreError> validate(CoreRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((PagingRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}