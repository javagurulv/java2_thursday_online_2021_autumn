package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class VisitorListApplication {

    public static void execute (ApplicationContext applicationContext){
        ProgramVisitorList programList = applicationContext.getBean(ProgramVisitorList.class);
        while (true) {
            programList.print();
            int numberFromConsole = programList.getMenuNumberFromUser();
            programList.executeSelectMenuItem(numberFromConsole);
        }
    }
//    private static ApplicationContext createApplicationContext() {
//        return new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
//    }
}
