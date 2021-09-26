package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.DeletePatientService;

import java.util.Scanner;

public class DeletePatientUIAction implements PatientUIActions {
    private final DeletePatientService deletePatient;

    public DeletePatientUIAction(DeletePatientService deletePatient) {
        this.deletePatient = deletePatient;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter patient ID: ");
        DeletePatientRequest request = new DeletePatientRequest(scanner.nextLine());
        DeletePatientResponse response = deletePatient.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
            if (response.isPatientDeleted()) {
                System.out.println("Patient with ID " + request.getIdRequest() + " was successfully deleted.");
            } else {
                System.out.println("User with ID:" + response.getIdResponse() + " not found!");
            }

        }
    }
}
