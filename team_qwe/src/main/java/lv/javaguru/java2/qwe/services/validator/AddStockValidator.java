package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.request.AddStockRequest;
import lv.javaguru.java2.qwe.request.SecurityRequest;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotDouble;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class AddStockValidator extends AddSecurityValidator {

    private final Map<Predicate<AddStockRequest>, String> validator = Map.ofEntries(
            entry(request -> request.getName() == null,
                    "Name: is empty!"),
            entry(request -> request.getName().length() < 3 || request.getName().length() > 100,
                    "Name: 3 to 100 symbols required!"),
            entry(request -> request.getIndustry().isEmpty(),
                    "Industry: is empty!"),
            entry(request -> request.getCurrency().isEmpty(),
                    "Currency: is empty!"),
            entry(request -> isNotDouble(request.getMarketPrice()),
                    "Market price: wrong format! Must be double!"),
            entry(request -> !isNotDouble(request.getMarketPrice()) && Double.parseDouble(request.getMarketPrice()) < 0,
                    "Market price: cannot be negative!"),
            entry(request -> isNotDouble(request.getDividends()),
                    "Dividend: wrong format! Must be double!"),
            entry(request -> !isNotDouble(request.getDividends()) && Double.parseDouble(request.getDividends()) < 0,
                    "Dividend: cannot be negative!"),
            entry(request -> isNotDouble(request.getRiskWeight()),
                    "Risk weight: wrong format! Must be double!"),
            entry(request -> !isNotDouble(request.getRiskWeight()) && Double.parseDouble(request.getRiskWeight()) < 0,
                    "Risk weight: cannot be negative!")
    );

    @Override
    public List<String> validate(SecurityRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((AddStockRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}