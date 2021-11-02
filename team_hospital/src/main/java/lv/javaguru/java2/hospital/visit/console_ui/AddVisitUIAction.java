package lv.javaguru.java2.hospital.visit.console_ui;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddVisitUIAction implements VisitUIAction{

    @Autowired private AddVisitService patientsVisitService;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String patientID = getUserInput.getUserStringInput("Please enter patient ID: ");
        String doctorID = getUserInput.getUserStringInput("Please enter doctor ID: ");
        String visitDate = getUserInput.getUserStringInput("Visits are possible every hour from 9 till 17 on working days only. " +
                "Please enter visit date in format dd-MM-yyyy HH:mm: ");
        String description = getUserInput.getUserStringInput("If needed, add any additional information: ");

        AddVisitRequest request =
                new AddVisitRequest(patientID, doctorID, visitDate, description);
        AddVisitResponse response = patientsVisitService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError
                    -> System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription()));
        } else {
            System.out.println(
                    "Visit registered for the patient: "
                            + response.getPatientVisit().getPatient() + " "
                            + ", to the doctor: "
                            + response.getPatientVisit().getDoctor() + ".");
            System.out.println("Visit date: " + response.getPatientVisit().getVisitDate()
                    + ", visit id: " + response.getPatientVisit().getVisitID() + ".");
        }
    }
}