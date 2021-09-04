package lv.javaguru.java2.jg_entertainment.restaurant.services;


import lv.javaguru.java2.jg_entertainment.restaurant.database.Database;

public class ServiceDeleteVisitors {
    private Database database;

    public ServiceDeleteVisitors(Database database) {
        this.database = database;
    }

    public void execute(Long idVisitor) {
        database.deleteClientWithId(idVisitor);
    }
}
