package lv.javaguru.java2.hospital.visit.console_ui;

public class ExitVisitUIAction implements VisitUIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
