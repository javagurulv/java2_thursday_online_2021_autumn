package lv.javaguru.java2.hospital.doctor.console_ui;

public class ExitUIAction implements DoctorUIActions{

    @Override
    public void execute() {
        System.exit(0);
    }
}
