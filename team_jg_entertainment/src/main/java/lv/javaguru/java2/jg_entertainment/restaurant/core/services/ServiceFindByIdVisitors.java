package lv.javaguru.java2.jg_entertainment.restaurant.core.services;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.Database;

public class ServiceFindByIdVisitors {

    private final Database database;


    public ServiceFindByIdVisitors(Database database) {
        this.database = database;
    }

    public boolean execute(Long id) {
        return database.findClientById(id);
    }
}
