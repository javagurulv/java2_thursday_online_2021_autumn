package lv.javaguru.java2.services.Get;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class GetAllClientsService {

    @DIDependency
    private Database database;

    public List<Client> execute() {
        return database.getAllClients();
    }
}
