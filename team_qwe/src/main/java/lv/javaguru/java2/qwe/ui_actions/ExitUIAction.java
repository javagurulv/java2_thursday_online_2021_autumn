package lv.javaguru.java2.qwe.ui_actions;

import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.exit(0);
    }

}