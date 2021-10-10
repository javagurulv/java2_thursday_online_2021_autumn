package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindSecurityByNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindSecurityByNameService {

    @Autowired private Database database;
    @Autowired private FindSecurityByNameValidator validator;

    public FindSecurityByNameResponse execute(FindSecurityByNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<Security> result = database.findSecurityByName(request.getName());
        return (errors.isEmpty() && result.isPresent()) ?
                new FindSecurityByNameResponse(result.get()) : new FindSecurityByNameResponse(errors);
    }

}