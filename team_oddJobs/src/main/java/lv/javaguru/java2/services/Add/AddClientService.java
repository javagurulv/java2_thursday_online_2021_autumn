package lv.javaguru.java2.services.Add;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.core.requests.Add.AddClientRequest;
import lv.javaguru.java2.core.responce.Add.AddClientResponse;
import lv.javaguru.java2.database.Database;

public class AddClientService {
    private Database database;

    public AddClientService(Database database) {
        this.database = database;
    }

    public AddClientResponse execute(AddClientRequest request) {
        Client client = new Client(request.getName(), request.getSurname());
        database.addClient(client);
        return new AddClientResponse(client);

    }
}
