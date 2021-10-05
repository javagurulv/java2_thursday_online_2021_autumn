package lv.javaguru.java2.console_ui.Exit;

import lv.javaguru.java2.console_ui.UIAction;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;
import lv.javaguru.java2.services.Exit.ExitMenuService;

@DIComponent
public class ExitMenuUIAction implements UIAction {


    @Override
    public void execute() {
        System.out.println("See you later, by!");
        System.exit(0);
    }
}
