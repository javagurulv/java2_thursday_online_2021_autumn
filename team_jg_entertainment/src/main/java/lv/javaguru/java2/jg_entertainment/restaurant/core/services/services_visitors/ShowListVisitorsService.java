package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ShowAllVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowListVisitorsService {

   @Autowired private VisitorsRepository database;

    public ShowAllVisitorsResponse execute(ShowAllVisitorsRequest request) {
        List<Visitors> visitors = database.showAllClientsInList();
        return new ShowAllVisitorsResponse(visitors);
    }
}
