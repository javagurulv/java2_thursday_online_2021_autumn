package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;
@DIComponent
public class ServiceShowListVisitors {

   @DIDependency private DatabaseVisitors database;

    public ResponseShowAllVisitors execute(RequestShowAllVisitorsInListRestaurant request) {
        List<Visitors> visitors = database.showAllClientsInList();
        return new ResponseShowAllVisitors(visitors);
    }
}
