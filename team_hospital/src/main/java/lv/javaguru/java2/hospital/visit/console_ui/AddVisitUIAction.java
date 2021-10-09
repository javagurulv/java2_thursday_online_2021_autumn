package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddVisitUIAction {

    @Autowired private AddVisitService patientsVisitService;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String patientsPersonalCode = getUserInput.getUserStringInput("Please enter patient personal code: ");
        String doctorName = getUserInput.getUserStringInput("Please enter doctor name: ");
        String doctorSurname = getUserInput.getUserStringInput("Please enter doctor surname: ");
        String visitDate = getUserInput.getUserStringInput("Please enter visit date in format dd/MM/yyyy HH:mm: ");

        AddVisitRequest request =
                new AddVisitRequest(patientsPersonalCode, doctorName,
                        doctorSurname, visitDate);
        AddVisitResponse response = patientsVisitService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(
                    "Visit registered for the patient: "
                            + response.getPatientVisit().getPatient().getName() + " "
                            + response.getPatientVisit().getPatient().getSurname()
                            + ", to the doctor: "
                            + response.getPatientVisit().getDoctor().getName() + " "
                            + response.getPatientVisit().getDoctor().getSurname() + ".");
            System.out.println("Visit date: " + response.getPatientVisit().getVisitDate()
                    + ", visit id: " + response.getPatientVisit().getVisitID() + ".");
        }
    }
}
