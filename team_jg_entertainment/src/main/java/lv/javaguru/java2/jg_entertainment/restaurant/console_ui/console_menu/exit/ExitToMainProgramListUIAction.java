package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.UIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.ProgramMenu;

//@Component
public class ExitToMainProgramListUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("You return in main menu!");
        System.out.println("Choose action with MENU: ");
        ProgramMenu.action();
    }
}
