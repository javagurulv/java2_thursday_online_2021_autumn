package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;

@DIComponent
public class ExitDoctorUIAction implements DoctorUIActions{

    @Override
    public void execute() {
        System.exit(0);
    }
}
