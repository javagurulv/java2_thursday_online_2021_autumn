package lv.javaguru.java2.oddJobs.services;

import lv.javaguru.java2.oddJobs.database.Database;


public class DeleteClientService {
    private Database database;

    public DeleteClientService(Database database) {
        this.database = database;
    }

    public void execute(Long clientId) {
        database.deleteClientById(clientId);
    }
}
