package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceShowListVisitors {

   @Autowired
   private DatabaseVisitors database;

    public ResponseShowAllVisitors execute(ShowAllVisitorsRequest request) {
        List<Visitors> visitors = database.showAllClientsInList();
        return new ResponseShowAllVisitors(visitors);
    }
}
