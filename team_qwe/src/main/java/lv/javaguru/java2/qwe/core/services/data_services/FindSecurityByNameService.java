package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindSecurityByNameValidator;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class FindSecurityByNameService {

    @DIDependency private Database database;
    @DIDependency private FindSecurityByNameValidator validator;

    public FindSecurityByNameResponse execute(FindSecurityByNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<Security> result = database.findSecurityByName(request.getName());
        return (errors.isEmpty() && result.isPresent()) ?
                new FindSecurityByNameResponse(result.get()) : new FindSecurityByNameResponse(errors);
    }

}