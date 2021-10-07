package lv.javaguru.java2.services.Remove;


import lv.javaguru.java2.core.requests.Remove.RemoveClientRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Remove.RemoveClientResponse;
import lv.javaguru.java2.core.validations.RemoveClientValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class RemoveClientService {
    @DIDependency
    private Database database;
    @DIDependency
    private RemoveClientValidator validator;

    public RemoveClientResponse execute(RemoveClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveClientResponse(errors);
        }
        boolean isClientRemoved = database.removeClient(request.getClientId(), request.getClientName(), request.getClientSurname());
        return new RemoveClientResponse(isClientRemoved);
    }
}
