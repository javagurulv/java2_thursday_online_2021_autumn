package lv.javaguru.java2.oddJobs.console_ui.exit;

import lv.javaguru.java2.oddJobs.console_ui.UIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitMenuUIAction implements UIAction {


    @Override
    public void execute() {
        System.out.println("See you later, by!");
        System.exit(0);
    }
}
