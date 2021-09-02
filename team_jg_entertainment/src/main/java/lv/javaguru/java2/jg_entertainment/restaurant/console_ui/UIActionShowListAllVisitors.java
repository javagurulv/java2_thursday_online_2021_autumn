package lv.javaguru.java2.jg_entertainment.restaurant.console_ui;

import lv.javaguru.java2.jg_entertainment.restaurant.services.ServiceShowListVisitors;

public class UIActionShowListAllVisitors implements RestaurantUIAction {

    private final ServiceShowListVisitors getAllVisitors;

    public UIActionShowListAllVisitors(ServiceShowListVisitors getAllVisitors) {
        this.getAllVisitors = getAllVisitors;
    }

    @Override
    public void execute() {
        System.out.println("All clients in list: ");
        getAllVisitors.execute().forEach(System.out::println);
        System.out.println("This all list with visitors !");
    }
}
