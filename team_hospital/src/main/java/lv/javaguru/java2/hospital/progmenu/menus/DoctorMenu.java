package lv.javaguru.java2.hospital.progmenu.menus;

import lv.javaguru.java2.hospital.doctor.console_ui.DoctorProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DoctorMenu implements Menu {

    @Override
    public void execute(ApplicationContext applicationContext) {
        DoctorProgramMenu doctorProgramMenu = applicationContext.getBean(DoctorProgramMenu.class);
        while (true) {
            doctorProgramMenu.printDoctorMenu();
            int doctorMenuNumber = doctorProgramMenu.getDoctorMenuNumberFromUser();
            doctorProgramMenu.executeSelectedMenuItem(doctorMenuNumber);
        }
    }
}
