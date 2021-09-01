package service_visitors;

import database.Database;

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
