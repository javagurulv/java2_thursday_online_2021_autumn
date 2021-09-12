package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.FilterStockByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class FilterStockByMultipleParametersValidator {

    private final Map<Predicate<FilterStockByMultipleParametersRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getList().isEmpty(),
                    new CoreError("Choose parameter", "at least one parameter is required!")),
            entry(request -> request.getMarketPriceTarget() < 0,
                    new CoreError("Market price target", "cannot be negative!")),
            entry(request -> request.getDividendTarget() < 0,
                    new CoreError("Dividend target", "cannot be negative!")),
            entry(request -> request.getRiskWeightTarget() < 0,
                    new CoreError("Risk weight target", "cannot be negative!")),
            entry(request -> request.getIndustryTarget() != null && request.getIndustryTarget().isEmpty(),
                    new CoreError("Industry target", "must not be empty!"))
    );

    public List<CoreError> validate(FilterStockByMultipleParametersRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}