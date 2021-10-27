package lv.javaguru.java2.hospital.prescription.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitPrescriptionUIAction implements PrescriptionUIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
