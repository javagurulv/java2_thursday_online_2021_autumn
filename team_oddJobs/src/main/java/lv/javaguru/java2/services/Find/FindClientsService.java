package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindClientsResponse;
import lv.javaguru.java2.core.validations.FindClientsValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class FindClientsService {

    private Database database;
    private FindClientsValidator validator;

    public FindClientsService(Database database, FindClientsValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindClientsResponse execute(FindClientsRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new FindClientsResponse(null, errors);
        }

        List<Client> clients = null;
        if (request.isIdProvided() && !request.isNameProvided() && !request.isSurnameProvide()) {
            clients = database.findClientsById(request.getClientId());
        }
        if (!request.isIdProvided() && request.isNameProvided() && !request.isSurnameProvide()) {
            clients = database.findClientsByName(request.getClientName());
        }
        if (!request.isIdProvided() && !request.isNameProvided() && request.isSurnameProvide()) {
            clients = database.findClientBySurname(request.getClientSurname());
        }

        if (request.isIdProvided() && request.isNameProvided() && request.isSurnameProvide()) {
            clients = database.findClientByIdAndNameAndSurname(request.getClientId(), request.getClientName(), request.getClientSurname());
        }

        return new FindClientsResponse(clients,null);

    }
}
