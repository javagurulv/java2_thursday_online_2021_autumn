package lv.javaguru.java2.qwe.services.data_services;

import lv.javaguru.java2.qwe.database.Database;

public class AddSecurityService {

    private final Database database;

    public AddSecurityService(Database database) {
        this.database = database;
    }

    public void execute(String type) {
        database.addSecurity(type);
    }


}