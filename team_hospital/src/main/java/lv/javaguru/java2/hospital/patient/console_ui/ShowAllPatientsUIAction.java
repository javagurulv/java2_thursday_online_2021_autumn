package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAllPatientsUIAction implements PatientUIActions {

    @Autowired private ShowAllPatientsService showAllPatients;

    public void execute() {
        System.out.println("Patients list: ");
        ShowAllPatientsRequest request = new ShowAllPatientsRequest();
        ShowAllPatientsResponse response = showAllPatients.execute(request);
        response.getPatients().forEach(System.out::println);
        System.out.println("Patients list end.");
    }
}
