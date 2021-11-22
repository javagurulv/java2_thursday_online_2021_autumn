package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AccessLevelForPersonal implements Menu {

    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramMenuHelper userProgramMenu = applicationContext.getBean(ProgramMenuHelper.class);
        while (true) {
            userProgramMenu.printMainMenu();
            int personal = userProgramMenu.getMenuNumberFromUser();
            userProgramMenu.executeSelectedMenuItem(personal,applicationContext);
        }
    }
}
