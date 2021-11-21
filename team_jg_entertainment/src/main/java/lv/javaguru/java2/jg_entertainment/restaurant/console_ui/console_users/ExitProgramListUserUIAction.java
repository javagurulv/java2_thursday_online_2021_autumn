package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.ProgramMenu;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramListUserUIAction implements UserUIAction {

    @Override
    public void execute() {
        System.out.println("You return in MAIN menu!");
        System.out.println("Choose action with MENU: ");
        ProgramMenu.action();
    }
}
