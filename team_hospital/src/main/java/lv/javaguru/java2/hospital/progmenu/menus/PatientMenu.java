package lv.javaguru.java2.hospital.progmenu.menus;

import lv.javaguru.java2.hospital.patient.console_ui.PatientProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PatientMenu implements Menu{
    @Override
    public void execute(ApplicationContext applicationContext) {
        PatientProgramMenu patientProgramMenu = applicationContext.getBean(PatientProgramMenu.class);
        while (true) {
            patientProgramMenu.printPatientMenu();
            int patientMenuNumber = patientProgramMenu.getPatientMenuNumberFromUser();
            patientProgramMenu.executeSelectedMenuItem(patientMenuNumber);
        }
    }
}
