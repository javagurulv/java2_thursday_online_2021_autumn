package service_visitors;

import database.Database;

public class ServiceAddAllVisitors {
    private Database database;


    public ServiceAddAllVisitors(Database database) {
        this.database = database;
    }

    public void execute(String nameVisitors, String surnameVisitors, String age){
        Visitors visitor = new Visitors(nameVisitors, surnameVisitors, age);
        database.saveClientOfRestaurant(visitor);
    }
}
