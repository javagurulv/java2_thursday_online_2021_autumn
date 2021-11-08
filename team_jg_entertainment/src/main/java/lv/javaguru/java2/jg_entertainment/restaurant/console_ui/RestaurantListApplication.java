package lv.javaguru.java2.jg_entertainment.restaurant.console_ui;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestaurantListApplication {

    public static void run() {
        ApplicationContext applicationContext = createApplicationContext();
        RestaurantProgramList assistant = applicationContext.getBean(RestaurantProgramList.class);
        while (true) {
            assistant.printProgramMenu();
            int numberOfMenu = assistant.checkNumberOfMenu();
            assistant.executeSelectMenuItem(numberOfMenu, applicationContext);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
    }
}
