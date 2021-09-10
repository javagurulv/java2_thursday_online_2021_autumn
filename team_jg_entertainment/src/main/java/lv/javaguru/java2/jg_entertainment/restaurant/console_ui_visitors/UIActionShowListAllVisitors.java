package lv.javaguru.java2.jg_entertainment.restaurant.console_ui_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.ServiceShowListVisitors;

public class UIActionShowListAllVisitors implements RestaurantUIAction {

    private final ServiceShowListVisitors getAllVisitors;

    public UIActionShowListAllVisitors(ServiceShowListVisitors getAllVisitors) {
        this.getAllVisitors = getAllVisitors;
    }

    @Override
    public void execute() {
        System.out.println("Client's list: ");
        getAllVisitors.execute().forEach(System.out::println);
        System.out.println("This all list with visitors !");
    }
}
