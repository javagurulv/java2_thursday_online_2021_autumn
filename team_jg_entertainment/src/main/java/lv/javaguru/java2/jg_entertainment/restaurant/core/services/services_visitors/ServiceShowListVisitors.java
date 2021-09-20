package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.Visitors;

import java.util.List;

public class ServiceShowListVisitors {

    private DatabaseVisitors database;

    public ServiceShowListVisitors(DatabaseVisitors database) {
        this.database = database;
    }

    public ResponseShowAllVisitors execute(RequestShowAllVisitorsInListRestaurant request) {
        List<Visitors> visitors = database.showAllClientsInList();
        return new ResponseShowAllVisitors(visitors);
    }
}
