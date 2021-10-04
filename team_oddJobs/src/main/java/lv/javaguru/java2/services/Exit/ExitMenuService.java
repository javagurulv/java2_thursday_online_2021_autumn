package lv.javaguru.java2.services.Exit;

import lv.javaguru.java2.dependency_injection.DIComponent;

@DIComponent
public class ExitMenuService {

    public void execute() {

        System.out.println("See you later, by!");
        System.exit(0);
    }
}
