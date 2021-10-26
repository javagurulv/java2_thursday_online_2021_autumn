package lv.javaguru.java2.hospital.progmenu.menus;

import lv.javaguru.java2.hospital.prescription.console_ui.PrescriptionProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMenu implements Menu{

    @Override
    public void execute(ApplicationContext applicationContext) {
        PrescriptionProgramMenu prescriptionProgramMenu = applicationContext.getBean(PrescriptionProgramMenu.class);
        while (true) {
            prescriptionProgramMenu.printPrescriptionMenu();
            int prescriptionMenuNumber = prescriptionProgramMenu.getPrescriptionMenuNumberFromUser();
            prescriptionProgramMenu.executeSelectedMenuItem(prescriptionMenuNumber);
        }
    }
}
