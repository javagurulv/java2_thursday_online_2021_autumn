package lv.javaguru.java2.services.Add;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.core.requests.Add.AddClientRequest;
import lv.javaguru.java2.core.responce.Add.AddClientResponse;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.validations.AddClientValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class AddClientService {
    @DIDependency
    private Database database;
    @DIDependency
    private AddClientValidator validator;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }
        Client client = new Client(request.getName(), request.getSurname());
        database.addClient(client);
        return new AddClientResponse(client);

    }
}
