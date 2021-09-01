package service_visitors;

import database.Database;

public class ServiceFindByIdVisitors {

    private final Database database;


    public ServiceFindByIdVisitors(Database database) {
        this.database = database;
    }

    public boolean execute(Long id) {
        return database.findClientById(id);
    }
}
