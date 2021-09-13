package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindSecurityByNameValidator;

import java.util.List;
import java.util.Optional;

public class FindSecurityByNameService {

    private final Database database;
    private final FindSecurityByNameValidator validator;

    public FindSecurityByNameService(Database database, FindSecurityByNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindSecurityByNameResponse execute(FindSecurityByNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<Security> result = database.findSecurityByName(request.getName());
        if (errors.isEmpty() && result.isPresent()) {
            return new FindSecurityByNameResponse(result.get());
        }
        return new FindSecurityByNameResponse(errors);
    }

}