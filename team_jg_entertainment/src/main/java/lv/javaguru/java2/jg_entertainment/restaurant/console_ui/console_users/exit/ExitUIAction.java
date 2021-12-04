package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.exit;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_users.UserUIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UserUIAction {

    @Override
    public void execute() {
        System.out.println("You finished! Thank a lot, have a good day !");
        System.exit(0);
    }
}
