package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetAllClientsService {

    @Autowired
    private Database database;

    public List<Client> execute() {
        return database.getAllClients();
    }
}
