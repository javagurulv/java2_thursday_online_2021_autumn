package lv.javaguru.java2.hospital.doctor.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitDoctorUIAction implements DoctorUIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
