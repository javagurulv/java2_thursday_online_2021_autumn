package lv.javaguru.java2.services.Remove;


import lv.javaguru.java2.core.requests.Remove.RemoveClientRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Remove.RemoveClientResponse;
import lv.javaguru.java2.core.validations.RemoveClientValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class RemoveClientService {
    private Database database;
    private RemoveClientValidator validator;

    public RemoveClientService(Database database, RemoveClientValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RemoveClientResponse execute(RemoveClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveClientResponse(errors);
        }
        boolean isClientRemoved = database.removeClient(request.getClientId(), request.getClientName(), request.getClientSurname());
        return new RemoveClientResponse(isClientRemoved);
    }
}
