package lv.javaguru.java2.hospital.visits.console_ui;

public class ExitVisitUIAction implements PatientVisitUIAction{

    @Override
    public void execute() {
        System.exit(0);
    }
}
