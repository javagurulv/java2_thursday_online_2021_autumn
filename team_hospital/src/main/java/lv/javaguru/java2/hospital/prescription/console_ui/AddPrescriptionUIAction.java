package lv.javaguru.java2.hospital.prescription.console_ui;

import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.AddPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.AddPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPrescriptionUIAction implements PrescriptionUIAction{

    @Autowired private AddPrescriptionService service;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long doctorId = getUserInput.getUserLongInput("Please enter doctor id: ");
        Long patientId = getUserInput.getUserLongInput("Please enter patient id: ");
        String medicationName = getUserInput.getUserStringInput("Please enter medication name: ");
        int quantity = getUserInput.getUserNumericInput("Please enter medication quantity: ");

        AddPrescriptionRequest request =
                new AddPrescriptionRequest(doctorId, patientId, medicationName, quantity);
        AddPrescriptionResponse response = service.execute(request);

        System.out.println("Medication " + medicationName + " with amount of "+ quantity +
                " was successfully prescribed to the patient "
                + response.getPrescription().getPatient().getName()
                + response.getPrescription().getPatient().getSurname()
                + " by doctor " + response.getPrescription().getDoctor().getName()
                + response.getPrescription().getDoctor().getSurname()
                + " on " + response.getPrescription().getDate()
                + ".");

    }
}
