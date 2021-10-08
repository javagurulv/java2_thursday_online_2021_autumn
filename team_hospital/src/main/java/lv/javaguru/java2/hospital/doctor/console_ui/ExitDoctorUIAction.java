package lv.javaguru.java2.hospital.doctor.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitDoctorUIAction implements DoctorUIActions{

    @Override
    public void execute() {
        System.exit(0);
    }
}
