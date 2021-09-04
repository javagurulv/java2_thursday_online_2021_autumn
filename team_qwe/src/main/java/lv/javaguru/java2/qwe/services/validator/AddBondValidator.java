package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.request.AddBondRequest;
import lv.javaguru.java2.qwe.request.SecurityRequest;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotDouble;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotInteger;

public class AddBondValidator extends AddSecurityValidator {

    private Database database;

    public AddBondValidator(Database database) {
        this.database = database;
    }

    private final Map<Predicate<AddBondRequest>, String> validator = Map.ofEntries(
            entry(request -> request.getName().length() < 3 || request.getName().length() > 100,
                    "Name: 3 to 100 symbols required!"),
            entry(request -> database.findSecurityByName(request.getName()).isPresent(),
                    "Name: security with such name already exists in the database!"),
            entry(request -> request.getIndustry().isEmpty(),
                    "Industry: is empty!"),
            entry(request -> request.getCurrency().isEmpty(),
                    "Currency: is empty!"),
            entry(request -> isNotDouble(request.getMarketPrice()),
                    "Market price: wrong format! Must be double!"),
            entry(request -> !isNotDouble(request.getMarketPrice()) && Double.parseDouble(request.getMarketPrice()) < 0,
                    "Market price: cannot be negative!"),
            entry(request -> isNotDouble(request.getCoupon()),
                    "Coupon: wrong format! Must be double!"),
            entry(request -> !isNotDouble(request.getCoupon()) && Double.parseDouble(request.getCoupon()) < 0,
                    "Coupon: cannot be negative!"),
            entry(request -> request.getRating().length() < 1 || request.getRating().length() > 4,
                    "Rating: 1 to 4 symbols are required!"),
            entry(request -> isNotInteger(request.getNominal()),
                    "Nominal: wrong format! Must be integer!"),
            entry(request -> !isNotInteger(request.getNominal()) && Integer.parseInt(request.getNominal()) < 0,
                    "Nominal: cannot be negative!"),
            entry(request -> request.getMaturity().length() != 10,
                    "Maturity: 10 symbols are required!")
    );

    @Override
    public List<String> validate(SecurityRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((AddBondRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}