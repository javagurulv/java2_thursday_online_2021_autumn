package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu.ProgramMenu;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramListVisitorUIAction implements VisitorUIAction {

    @Override
    public void execute() {
        System.out.println("You return in MAIN menu!");
        System.out.println("Choose action with MENU: ");
        ProgramMenu.action();
    }
}
