package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.DeletePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientUIAction implements PatientUIActions {

    @Autowired private DeletePatientService deletePatient;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        DeletePatientRequest request = new DeletePatientRequest(getUserInput.getUserStringInput("Please, enter patient ID: "));
        DeletePatientResponse response = deletePatient.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
                System.out.println("Patient with ID " + request.getIdRequest() + " was successfully deleted.");
        }
    }
}
