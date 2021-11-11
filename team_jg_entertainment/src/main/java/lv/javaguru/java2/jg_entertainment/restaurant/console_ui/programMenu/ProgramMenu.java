package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProgramMenu {

    public static void action() {
        ApplicationContext applicationContext = createApplicationContext();
        ProgramMenuHelper helper = applicationContext.getBean(ProgramMenuHelper.class);
        while (true) {
            helper.printMainMenu();
            int menuNumber = helper.getMenuNumberFromUser();
            helper.executeSelectedMenuItem(menuNumber, applicationContext);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
    }

}