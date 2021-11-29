package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.utils.UtilityMethods;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Component
public class FilterStocksByAnyDoubleParameterValidator {

    private final UtilityMethods utils = new UtilityMethods();

    private final Map<Predicate<FilterStocksByAnyDoubleParameterRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> utils.isNotDouble(request.getTargetAmount()),
                    new CoreError("parameter target", "wrong format!")),
            entry(request -> !utils.isNotDouble(request.getTargetAmount()) && Double.parseDouble(request.getTargetAmount()) < 0,
                    new CoreError("parameter target", "cannot be negative!"))
    );

    public List<CoreError> validate(CoreRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((FilterStocksByAnyDoubleParameterRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}