package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.RestaurantListApplication;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramTableListUIAction implements UIAction {

    @Override
    public void execute() {
//        RestaurantListApplication list = new RestaurantListApplication();
        System.out.println("You return in main menu!");
        System.out.println("Choose action with MENU: ");
        RestaurantListApplication.run();
    }
}
