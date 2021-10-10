package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.InputNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditVisitUIAction implements VisitUIAction {

    @Autowired
    private EditVisitService editPatientVisit;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        InputNumChecker numChecker = new InputNumChecker();
        Long id = getUserInput.getUserLongInput("Please, enter the patient visit's id: ");
        printEditMenu();
        int userInput = numChecker.execute(1, 3);
        EditVisitEnum editEnum;
        if (userInput == 1) {
            editEnum = EditVisitEnum.CHANGE_DOCTOR;
        } else if (userInput == 2) {
            editEnum = EditVisitEnum.CHANGE_PATIENT;
        } else {
            editEnum = EditVisitEnum.CHANGE_DATE;
        }
        String changes = "";
        switch (userInput) {
            case 1 -> changes = getUserInput.getUserStringInput("Enter new doctor id: ");
            case 2 -> changes = getUserInput.getUserStringInput("Enter new patient id: ");
            case 3 -> changes = getUserInput.getUserStringInput("Enter new visit date and time in format dd/MM/yyyy HH:mm: ");
        }
        EditVisitRequest request = new EditVisitRequest(id, editEnum, changes);
        EditVisitResponse response = editPatientVisit.execute(request);
        if (response.isVisitEdited()) {
            System.out.println("The patient visit with id " + id + " was successfully edited.");
        } else {
            System.out.println("The patient visit with id " + id + " was not edited.");
        }
    }

    private void printEditMenu() {
        System.out.println("What information you would like to edit? ");
        System.out.println("1. Doctor");
        System.out.println("2. Patient");
        System.out.println("3. Visit date and time");
        System.out.println("Enter edit menu number: ");
    }
}
