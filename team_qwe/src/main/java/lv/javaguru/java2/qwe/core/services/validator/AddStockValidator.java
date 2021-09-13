package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.SecurityRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.isNotDouble;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class AddStockValidator extends AddSecurityValidator {

    private Database database;

    public AddStockValidator(Database database) {
        this.database = database;
    }

    private final Map<Predicate<AddStockRequest>, CoreError> validator = Map.ofEntries(
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
            entry(request -> isNotDouble(request.getDividends()),
                    new CoreError("Dividend", "wrong format! Must be double!")),
            entry(request -> !isNotDouble(request.getDividends()) && Double.parseDouble(request.getDividends()) < 0,
                    new CoreError("Dividend", "cannot be negative!")),
            entry(request -> isNotDouble(request.getRiskWeight()),
                    new CoreError("Risk weight", "wrong format! Must be double!")),
            entry(request -> !isNotDouble(request.getRiskWeight()) && Double.parseDouble(request.getRiskWeight()) < 0,
                    new CoreError("Risk weight", "cannot be negative!"))
    );

    @Override
    public List<CoreError> validate(SecurityRequest request) {
        return validator.entrySet().stream()
                .filter(entry -> entry.getKey().test((AddStockRequest) request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}