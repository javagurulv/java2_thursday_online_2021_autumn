package lv.javaguru.java2.hospital.patient.console_ui;

import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditPatientUIAction implements PatientUIActions {

    @Autowired
    private EditPatientService editPatient;

    public void execute() {
        InputNumChecker numChecker = new InputNumChecker();
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please enter patient ID: ");
        menu();
        int userInput = numChecker.execute(1,3);
        EditPatientEnum editEnum;
        if (userInput == 1) {
            editEnum = EditPatientEnum.CHANGE_NAME;
        } else if (userInput == 2) {
            editEnum = EditPatientEnum.CHANGE_SURNAME;
        } else {
            editEnum = EditPatientEnum.CHANGE_PERSONALCODE;
        }
        String changes = getUserInput.getUserStringInput("Please enter information for changes: ");
        EditPatientRequest request = new EditPatientRequest(id, editEnum, changes);
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
        System.out.println("Choose number from 1 to 3:");
    }
}