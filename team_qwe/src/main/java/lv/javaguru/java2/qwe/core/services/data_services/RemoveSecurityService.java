package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;

public class RemoveSecurityService {

    private final Database database;

    public RemoveSecurityService(Database database) {
        this.database = database;
    }

    public void execute(String name) {
        database.removeSecurity(name);
    }

}