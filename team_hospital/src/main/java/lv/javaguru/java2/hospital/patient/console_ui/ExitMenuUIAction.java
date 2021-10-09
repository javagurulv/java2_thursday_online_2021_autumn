package lv.javaguru.java2.hospital.patient.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitMenuUIAction implements PatientUIActions {

    @Override
    public void execute() {
        System.exit(0);
    }
}
