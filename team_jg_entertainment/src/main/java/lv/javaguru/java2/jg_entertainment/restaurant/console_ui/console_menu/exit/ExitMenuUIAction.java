package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_menu.UIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitMenuUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }
}
