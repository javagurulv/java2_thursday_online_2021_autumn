package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.core.database.Database;

import java.util.Optional;

public class FindSecurityByNameService {

    private final Database database;

    public FindSecurityByNameService(Database database) {
        this.database = database;
    }

    public Optional<Security> execute(String name) {
        return database.findSecurityByName(name);
    }

}