package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.services.ShowAllPatientsService;

public class ShowAllPatientsUIAction implements PatientUIActions {
    private final ShowAllPatientsService showAllPatients;

    public ShowAllPatientsUIAction(ShowAllPatientsService showAllPatients) {
        this.showAllPatients = showAllPatients;
    }

    public void execute() {
        System.out.println("Patients list: ");
        ShowAllPatientsRequest request = new ShowAllPatientsRequest();
        ShowAllPatientsResponse response = showAllPatients.execute(request);
        response.getPatients().forEach(System.out::println);
        System.out.println("Patients list end.");
    }
}
