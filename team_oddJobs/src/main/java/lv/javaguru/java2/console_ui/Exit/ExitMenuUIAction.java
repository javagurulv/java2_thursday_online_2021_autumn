package lv.javaguru.java2.console_ui.Exit;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.services.Exit.ExitMenuService;

public class ExitMenuUIAction implements UIAction {

    private ExitMenuService exitUIService;

    public ExitMenuUIAction(ExitMenuService exitUIService) {
        this.exitUIService = exitUIService;
    }

    @Override
    public void execute() {
        System.out.println("See you later, by!");
        exitUIService.execute();
    }
}
