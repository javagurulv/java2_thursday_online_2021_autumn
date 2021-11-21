package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.ProgramMenuUser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserMenu implements Menu{
    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramMenuUser userProgramMenu = applicationContext.getBean(ProgramMenuUser.class);
        while (true) {
            userProgramMenu.print();
            int visitorMenuNumber = userProgramMenu.getMenuNumberFromUser();
            userProgramMenu.executeSelectMenuItem(visitorMenuNumber);
        }
    }
}
