package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.FilterStockByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotDouble;

public class FilterStockByAnyDoubleParameterValidator {

    private final Map<Predicate<FilterStockByAnyDoubleParameterRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getParameter().isEmpty(),
                    new CoreError("Parameter", "is empty!")),
            entry(request -> request.getOperator().isEmpty(),
                    new CoreError("Operator", "is empty!")),
            entry(request -> isNotDouble(request.getTargetAmount()),
                    new CoreError("Market price", "wrong format! Must be double!"))
    );

    public List<CoreError> validate(FilterStockByAnyDoubleParameterRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}