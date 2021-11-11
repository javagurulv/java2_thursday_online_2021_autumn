package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.ProgramMenuRestaurant;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MenuMenu implements Menu{
    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramMenuRestaurant menuProgramMenu = applicationContext.getBean(ProgramMenuRestaurant.class);
        while (true) {
            menuProgramMenu.printMenuInRestaurant();
            int menuMenuNumber = menuProgramMenu.getMenuNumberFromUser();
            menuProgramMenu.executeSelectMenuItem(menuMenuNumber);
        }
    }
}
