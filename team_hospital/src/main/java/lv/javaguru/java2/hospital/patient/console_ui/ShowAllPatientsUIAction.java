package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.services.ShowAllPatientsService;

public class ShowAllPatientsUIAction implements PatientUIActions {
    private final ShowAllPatientsService showAllPatients;

    public ShowAllPatientsUIAction(ShowAllPatientsService showAllPatients) {
        this.showAllPatients = showAllPatients;
    }

    public void execute() {
        System.out.println(showAllPatients.execute());
    }
}
