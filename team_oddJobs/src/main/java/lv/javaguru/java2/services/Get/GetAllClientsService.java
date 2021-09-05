package lv.javaguru.java2.services.Get;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class GetAllClientsService {

    private Database database;

    public GetAllClientsService(Database database) {
        this.database = database;
    }

    public List<Client> execute() {
        return database.getAllClients();
    }
}
