package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.database.Database;

public class FindClientByIdService {

    private Database database;

    public FindClientByIdService(Database database) {
        this.database = database;
    }

    public void execute(Long idToSearch) {
        database.findClientById(idToSearch);
    }
}
