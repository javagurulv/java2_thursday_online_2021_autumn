package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.database.Database;

public class FindClientBySurnameService {

    private Database database;

    public FindClientBySurnameService(Database database) {
        this.database = database;
    }

    public void execute(String surnameToSearch) {
        database.findClientBySurname(surnameToSearch);
    }
}
