package lv.javaguru.java2.hospital.progmenu.menus;

import lv.javaguru.java2.hospital.visit.console_ui.VisitProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class VisitMenu implements Menu{

    @Override
    public void execute(ApplicationContext applicationContext) {
        VisitProgramMenu visitProgramMenu = applicationContext.getBean(VisitProgramMenu.class);
        while (true) {
            visitProgramMenu.printMenuPatientVisit();
            int visitMenuNumber = visitProgramMenu.getVisitMenuNumberFromUser();
            visitProgramMenu.executeSelectedMenuItem(visitMenuNumber);
        }
    }
}
