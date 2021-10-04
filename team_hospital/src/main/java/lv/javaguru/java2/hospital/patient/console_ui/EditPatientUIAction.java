package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;

@DIComponent
public class EditPatientUIAction implements PatientUIActions {

    @DIDependency private EditPatientService editPatient;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please enter patient ID: ");
        menu();
        Integer userInput = getUserInput.getUserNumericInput("Please enter edit menu number: ");
        String changes = getUserInput.getUserStringInput("Please enter information for changes: ");
        EditPatientRequest request = new EditPatientRequest(id, userInput, changes);
        EditPatientResponse response = editPatient.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getDescription())
            );
        } else {
            if (response.isTrueOrNot()) {
                System.out.println("Changes are made!");
            } else {
                System.out.println("Patient not found!");
            }
        }
    }

    private void menu() {
        System.out.println("What information would you like to edit? ");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Personal code");
    }
}