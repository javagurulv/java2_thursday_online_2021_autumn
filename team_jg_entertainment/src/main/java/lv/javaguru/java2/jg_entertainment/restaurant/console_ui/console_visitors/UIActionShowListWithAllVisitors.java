package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

@DIComponent
public class UIActionShowListWithAllVisitors implements RestaurantUIAction {

    @DIDependency private ServiceShowListVisitors getAllVisitors;

    @Override
    public void execute() {
        System.out.println("Client's list: ");

        RequestShowAllVisitorsInListRestaurant request = new RequestShowAllVisitorsInListRestaurant();
        ResponseShowAllVisitors response = getAllVisitors.execute(request);

        response.getNewVisitor().forEach(System.out::println);
        System.out.println("Finished! This all list restaurant visitors !");
    }
}
