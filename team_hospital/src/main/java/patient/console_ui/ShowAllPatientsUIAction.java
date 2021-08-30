package patient.console_ui;

import patient.services.ShowAllPatientsService;

public class ShowAllPatientsUIAction implements PatientUIActions {
    ShowAllPatientsService showAllPatients;

    public ShowAllPatientsUIAction(ShowAllPatientsService showAllPatients) {
        this.showAllPatients = showAllPatients;
    }

    public void execute() {
        System.out.println(showAllPatients.execute());
    }
}
