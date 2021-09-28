package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddBondRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotDouble;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotInteger;

@DIComponent
public class AddBondValidator extends AddSecurityValidator {

    @DIDependency private Database database;

    private final Map<Predicate<AddBondRequest>, CoreError> validator = Map.ofEntries(
            entry(request -> request.getName().length() < 3 || request.getName().length() > 100,
                    new CoreError("Name", "3 to 100 symbols required!")),
            entry(request -> database.findSecurityByName(request.getName()).isPresent(),
                    new CoreError("Name", "security with such name already exists in the database!")),
            entry(request -> request.getIndustry().isEmpty(),
                    new CoreError("Industry", "is empty!")),
            entry(request -> request.getCurrency().isEmpty(),
                    new CoreError("Currency", "is empty!")),
            entry(request -> isNotDouble(request.getMarketPrice()),
                    new CoreError("Market price", "wrong format! Must be double!")),
            entry(request -> !isNotDouble(request.getMarketPrice()) && Double.parseDouble(request.getMarketPrice()) < 0,
                    new CoreError("Market price", "cannot be negative!")),
            entry(request -> isNotDouble(request.getCoupon()),
                    new CoreError("Coupon", "wrong format! Must be double!")),
            entry(request -> !isNotDouble(request.getCoupon()) && Double.parseDouble(request.getCoupon()) < 0,
                    new CoreError("Coupon", "cannot be negative!")),
            entry(request -> request.getRating().length() < 1 || request.getRating().length() > 4,
                    new CoreError("Rating", "1 to 4 symbols are required!")),
            entry(request -> isNotInteger(request.getNominal()),
                    new CoreError("Nominal", "wrong format! Must be integer!")),
            entry(request -> !isNotInteger(request.getNominal()) && Integer.parseInt(request.getNominal()) < 0,
                    new CoreError("Nominal", "cannot be negative!")),
            entry(request -> request.getMaturity().length() != 10,
                    new CoreError("Maturity", "10 symbols are required!"))
    );

    @Override
    public List<CoreError> validate(CoreRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((AddBondRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}