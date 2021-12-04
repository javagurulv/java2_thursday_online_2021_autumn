package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_tables.UIAction;
import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.ProgramMenu;

//@Component
public class ExitProgramTableListUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("You return in main menu!");
        System.out.println("Choose action with MENU: ");
        ProgramMenu.action();
    }
}
