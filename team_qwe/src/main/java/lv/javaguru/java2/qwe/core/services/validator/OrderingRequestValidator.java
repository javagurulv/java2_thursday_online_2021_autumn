package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.OrderingRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Component
public class OrderingRequestValidator {

    private final Map<Predicate<OrderingRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getOrderBy().isEmpty() && !request.getOrderDirection().isEmpty(),
                    new CoreError("Ordering", "both fields must be empty or filled!")),
            entry(request -> !request.getOrderBy().isEmpty() && request.getOrderDirection().isEmpty(),
                    new CoreError("Ordering", "both fields must be empty or filled!"))
    );

    public List<CoreError> validate(CoreRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((OrderingRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}