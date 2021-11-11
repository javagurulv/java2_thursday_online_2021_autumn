package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.programMenu;

import lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_visitors.ProgramMenuVisitor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class VisitorMenu implements Menu{
    @Override
    public void execute(ApplicationContext applicationContext) {
        ProgramMenuVisitor visitorProgramMenu = applicationContext.getBean(ProgramMenuVisitor.class);
        while (true) {
            visitorProgramMenu.print();
            int visitorMenuNumber = visitorProgramMenu.getMenuNumberFromUser();
            visitorProgramMenu.executeSelectMenuItem(visitorMenuNumber);
        }
    }
}
