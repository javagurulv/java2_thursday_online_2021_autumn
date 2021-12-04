package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.UserUIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.ProgramMenu;

//@Component
public class ExitProgramListUserUIAction implements UserUIAction {

    @Override
    public void execute() {
        System.out.println("You return in MAIN menu!");
        System.out.println("Choose action with MENU: ");
        ProgramMenu.action();
    }
}
