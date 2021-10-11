package lv.javaguru.java2.hospital.progmenu.menus;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ExitMenu implements Menu{

    @Override
    public void execute(ApplicationContext applicationContext) {
        System.exit(0);
    }
}
