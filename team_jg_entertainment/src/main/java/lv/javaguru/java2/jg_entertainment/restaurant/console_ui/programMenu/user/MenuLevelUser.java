package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.user;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MenuLevelUser implements Menu {
    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramMenuLevelUser userProgramMenu = applicationContext.getBean(ProgramMenuLevelUser.class);
        while (true) {
            userProgramMenu.print();
            int visitorMenuNumber = userProgramMenu.getMenuNumberFromUser();
            userProgramMenu.executeSelectMenuItem(visitorMenuNumber);
        }

    }
}
