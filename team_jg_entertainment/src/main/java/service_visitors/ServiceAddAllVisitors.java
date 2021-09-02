package service_visitors;

import database.Database;

public class ServiceAddAllVisitors {
    private Database database;


    public ServiceAddAllVisitors(Database database) {
        this.database = database;
    }

    public void execute(String nameVisitors, String surnameVisitors, String age, long telephone){
        Visitors visitor = new Visitors(nameVisitors, surnameVisitors, age, telephone);
        database.saveClientOfRestaurant(visitor);
    }
}
