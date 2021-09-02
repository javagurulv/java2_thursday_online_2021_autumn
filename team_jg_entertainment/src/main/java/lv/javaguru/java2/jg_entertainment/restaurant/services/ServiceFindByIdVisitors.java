package lv.javaguru.java2.jg_entertainment.restaurant.services;

import lv.javaguru.java2.jg_entertainment.restaurant.database.Database;

public class ServiceFindByIdVisitors {

    private final Database database;


    public ServiceFindByIdVisitors(Database database) {
        this.database = database;
    }

    public boolean execute(Long id) {
        return database.findClientById(id);
    }
}
