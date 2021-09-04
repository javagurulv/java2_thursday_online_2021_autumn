package lv.javaguru.java2.jg_entertainment.restaurant.core.services;


import lv.javaguru.java2.jg_entertainment.restaurant.core.database.Database;

public class ServiceDeleteVisitors {
    private Database database;

    public ServiceDeleteVisitors(Database database) {
        this.database = database;
    }

    public void execute(Long idVisitor) {
        database.deleteClientWithId(idVisitor);
    }
}
