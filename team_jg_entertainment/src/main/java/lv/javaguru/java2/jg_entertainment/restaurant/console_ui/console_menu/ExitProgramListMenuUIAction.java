package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.RestaurantProgramList;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramListMenuUIAction implements UIAction {

    @Override
    public void execute() {
        RestaurantProgramList list = new RestaurantProgramList();
        System.out.println("You return in main menu!");
        System.out.println("Choose action with MENU: ");
        list.mainAction();
    }
}
