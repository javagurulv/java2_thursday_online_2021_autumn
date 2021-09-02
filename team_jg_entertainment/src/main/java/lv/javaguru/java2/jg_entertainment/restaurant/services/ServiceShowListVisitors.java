package lv.javaguru.java2.jg_entertainment.restaurant.services;

import lv.javaguru.java2.jg_entertainment.restaurant.database.Database;

import java.util.List;

public class ServiceShowListVisitors {

    private Database database;

    public ServiceShowListVisitors(Database database) {
        this.database = database;
    }

    public List<Visitors> execute() {
        return database.showAllClientsInList();
    }
}
