package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Bond;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddBondRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.SecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddBondResponse;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;

import java.util.List;

public class AddBondService {

    private final Database database;
    private final AddBondValidator validator;

    public AddBondService(Database database, AddBondValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddBondResponse execute(SecurityRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Bond bond = addBond((AddBondRequest) request);
            database.addBond(bond);
            return new AddBondResponse(bond);
        } else {
            return new AddBondResponse(errors);
        }
    }

    private Bond addBond(AddBondRequest request) {
        return new Bond(
                request.getName(),
                request.getIndustry(),
                request.getCurrency(),
                Double.parseDouble(request.getMarketPrice()),
                Double.parseDouble(request.getCoupon()),
                request.getRating(),
                Integer.parseInt(request.getNominal()),
                request.getMaturity()
        );
    }

}