package lv.javaguru.java2.hospital.progmenu;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.progmenu.console_ui.ProgramMenuHelper;
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
        return new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

}