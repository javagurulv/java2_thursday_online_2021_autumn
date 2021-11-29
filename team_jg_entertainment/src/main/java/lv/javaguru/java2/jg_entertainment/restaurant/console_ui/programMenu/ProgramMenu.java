package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.web_ui.config.RestaurantSpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ProgramMenu {

    public static void action() {
//        ApplicationContext applicationContext = createApplicationContext();

        ConfigurableApplicationContext context = SpringApplication.run(RestaurantSpringWebConfiguration.class);
        ProgramMenuHelper helper = context.getBean(ProgramMenuHelper.class);
        while (true) {
            helper.printMainMenu();
            int menuNumber = helper.getMenuNumberFromUser();
            helper.executeSelectedMenuItem(menuNumber, context);
        }
    }
//
//    private static ApplicationContext createApplicationContext() {
//        return new AnnotationConfigApplicationContext(RestaurantCoreConfiguration.class);
//    }
}