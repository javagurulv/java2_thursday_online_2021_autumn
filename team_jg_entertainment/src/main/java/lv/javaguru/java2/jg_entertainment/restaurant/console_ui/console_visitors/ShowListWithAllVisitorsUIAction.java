package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ShowAllVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowListWithAllVisitorsUIAction implements RestaurantUIAction {

    @Autowired
    private ServiceShowListVisitors getAllVisitors;

    @Override
    public void execute() {
        System.out.println("Client's list: ");

        ShowAllVisitorsRequest request = new ShowAllVisitorsRequest();
        ShowAllVisitorsResponse response = getAllVisitors.execute(request);

        response.getNewVisitor().forEach(System.out::println);
        System.out.println("Finished! This all list restaurant visitors !");
    }
}
