package lv.javaguru.java2.hospital.prescription.console_ui;

import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.EditPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.EditPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class EditPrescriptionUIAction implements PrescriptionUIAction {

    @Autowired
    private EditPrescriptionService service;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String prescriptionID =
                getUserInput.getUserStringInput("Please enter prescription ID: ");
        String userChoice =
                getUserInput.getUserStringInput("Please enter what do you like to change (PATIENT||MEDICATION_NAME||QUANTITY)?")
                        .toUpperCase(Locale.ROOT);
        String changes = getUserInput.getUserStringInput("Please enter data for changes");

        EditPrescriptionRequest request = new EditPrescriptionRequest(prescriptionID, userChoice, changes);
        EditPrescriptionResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println("Changes are made!");
        }
    }
}
