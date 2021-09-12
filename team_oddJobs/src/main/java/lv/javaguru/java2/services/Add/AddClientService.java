package lv.javaguru.java2.services.Add;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.core.requests.Add.AddClientRequest;
import lv.javaguru.java2.core.responce.Add.AddClientResponse;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.validations.AddClientValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class AddClientService {
    private Database database;
    private AddClientValidator validator;

    public AddClientService(Database database, AddClientValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }
        Client client = new Client(request.getName(),request.getSurname());
        database.addClient(client);
        return new AddClientResponse(client);

    }
}
