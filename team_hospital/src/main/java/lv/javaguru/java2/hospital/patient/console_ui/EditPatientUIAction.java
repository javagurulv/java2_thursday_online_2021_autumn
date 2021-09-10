package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.patient.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.services.EditPatientService;
import java.util.Scanner;

public class EditPatientUIAction implements PatientUIActions {
    private final EditPatientService editPatient;

    public EditPatientUIAction(EditPatientService editPatient) {
        this.editPatient = editPatient;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter patient's ID: ");
        String id = scanner.nextLine();
        System.out.println("What information would you like to edit? ");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Personal code");
        String userInput = scanner.nextLine();
        System.out.println("Please enter information for changes: ");
        String changes = scanner.nextLine();
        EditPatientRequest request = new EditPatientRequest(id, userInput, changes);
        EditPatientResponse response = editPatient.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
                System.out.println("Changes are made!");
        }
    }
}