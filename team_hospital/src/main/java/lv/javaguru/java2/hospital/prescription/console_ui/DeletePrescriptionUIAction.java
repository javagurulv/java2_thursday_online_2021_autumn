package lv.javaguru.java2.hospital.prescription.console_ui;

import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.DeletePrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.DeletePrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePrescriptionUIAction {

    @Autowired
    private DeletePrescriptionService service;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please enter prescription id: ");
        DeletePrescriptionRequest request = new DeletePrescriptionRequest(id);
        DeletePrescriptionResponse response = service.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isPrescriptionDeleted()) {
                System.out.println("The prescription with id " + id + " was successfully deleted.");
            } else {
                System.out.println("The prescription with id " + id + " was not deleted.");
            }
        }
    }
}
