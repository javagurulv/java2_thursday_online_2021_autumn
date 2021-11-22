package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.user;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AccessLevelForUser implements Menu {

    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramUserHelper userProgramMenu = applicationContext.getBean(ProgramUserHelper.class);
        while (true) {
            userProgramMenu.printMainMenu();
            int personal = userProgramMenu.getMenuNumberFromUser();
            userProgramMenu.executeSelectedMenuItem(personal,applicationContext);
        }
    }
}
