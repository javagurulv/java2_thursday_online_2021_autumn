package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Component
public class FilterStockByIndustryValidator {

    private final Map<Predicate<FilterStocksByIndustryRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getIndustry() != null && request.getIndustry().isEmpty(),
                    new CoreError("Industry target", "must not be empty!"))
    );

    public List<CoreError> validate(CoreRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((FilterStocksByIndustryRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}