package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.PagingRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.utils.UtilityMethods;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

//@Component
public class PagingRequestValidator {

    private final UtilityMethods utils = new UtilityMethods();

    private final Map<Predicate<PagingRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getPageNumber().isEmpty() && !request.getPageSize().isEmpty(),
                    new CoreError("Paging", "both fields must be empty or filled!")),
            entry(request -> !request.getPageNumber().isEmpty() && request.getPageSize().isEmpty(),
                    new CoreError("Paging", "both fields must be empty or filled!")),
            entry(request -> !utils.isNotInteger(request.getPageNumber()) && Integer.parseInt(request.getPageNumber()) < 0,
                    new CoreError("Paging", "cannot be negative!")),
            entry(request -> !utils.isNotInteger(request.getPageSize()) && Integer.parseInt(request.getPageSize()) < 0,
                    new CoreError("Paging", "cannot be negative!")),
            entry(request -> !request.getPageNumber().isEmpty() && utils.isNotInteger(request.getPageNumber()),
                    new CoreError("Paging", "wrong format!")),
            entry(request -> !request.getPageSize().isEmpty() && utils.isNotInteger(request.getPageSize()),
                    new CoreError("Paging", "wrong format!"))
    );

    public List<CoreError> validate(CoreRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((PagingRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}