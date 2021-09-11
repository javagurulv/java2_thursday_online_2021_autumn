package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;

public class UIActionShowListWithAllVisitors implements RestaurantUIAction {

    private final ServiceShowListVisitors getAllVisitors;

    public UIActionShowListWithAllVisitors(ServiceShowListVisitors getAllVisitors) {
        this.getAllVisitors = getAllVisitors;
    }

    @Override
    public void execute() {
        System.out.println("Client's list: ");
        getAllVisitors.execute().forEach(System.out::println);
        System.out.println("This all list with visitors !");
    }
}
