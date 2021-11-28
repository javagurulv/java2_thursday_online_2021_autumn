package lv.javaguru.java2.oddJobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import lv.javaguru.java2.oddJobs.console_ui.ProgramMenu;
import lv.javaguru.java2.oddJobs.web_ui.config.SpringWebConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebCallBoardApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringWebConfiguration.class);

        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
        while (true) {
            programMenu.printProgramMenu();
            int menuNumber = programMenu.getUserChoice();
            programMenu.executeSelectedMenuItem(menuNumber);
        }
    }
}



