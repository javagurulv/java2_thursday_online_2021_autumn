package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.database.Database;

public class FindClientByNameService {

    private Database database;

    public FindClientByNameService(Database database) {
        this.database = database;
    }


    public void execute(String nameToSearch) {
        database.findClientByName(nameToSearch);
    }
}
