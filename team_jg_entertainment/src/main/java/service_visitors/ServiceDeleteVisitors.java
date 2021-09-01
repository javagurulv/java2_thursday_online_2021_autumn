package service_visitors;


import database.Database;

public class ServiceDeleteVisitors {
    private Database database;

    public ServiceDeleteVisitors(Database database) {
        this.database = database;
    }

    public void execute(Long idVisitor) {
        database.deleteClientWithId(idVisitor);
    }
}
