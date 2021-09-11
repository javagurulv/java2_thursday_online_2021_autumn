package lv.javaguru.java2.jg_entertainment.restaurant.core.services;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;

import java.util.List;

public class ServiceShowListVisitors {

    private DatabaseVisitors database;

    public ServiceShowListVisitors(DatabaseVisitors database) {
        this.database = database;
    }

    public List<Visitors> execute() {
        return database.showAllClientsInList();
    }
}
