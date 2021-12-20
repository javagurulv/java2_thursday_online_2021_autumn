package lv.javaguru.java2.hospital;

import lv.javaguru.java2.hospital.progmenu.console_ui.ProgramMenuHelper;
import lv.javaguru.java2.hospital.web_ui.config.HospitalSpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Hospital {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HospitalSpringWebConfiguration.class);
        ProgramMenuHelper helper = context.getBean(ProgramMenuHelper.class);

        while (true) {
            helper.printMainMenu();
            int menuNumber = helper.getMenuNumberFromUser();
            helper.executeSelectedMenuItem(menuNumber, context);
        }
    }
}