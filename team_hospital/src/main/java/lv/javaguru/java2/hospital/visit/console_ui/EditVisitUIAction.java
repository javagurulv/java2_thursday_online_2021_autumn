package lv.javaguru.java2.hospital.visit.console_ui;

import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class EditVisitUIAction implements VisitUIAction {

    @Autowired
    private EditVisitService editPatientVisit;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please, enter the patient visit's id: ");
        String userInput = getUserInput.getUserStringInput("What information you would like to edit " +
                "(DOCTOR||PATIENT||DATE||DESCRIPTION)? ").toUpperCase(Locale.ROOT);
        String changes = "";
        switch (userInput) {
            case "DOCTOR" -> changes = getUserInput.getUserStringInput("Enter new doctor id: ");
            case "PATIENT" -> changes = getUserInput.getUserStringInput("Enter new patient id: ");
            case "DATE" -> changes = getUserInput.getUserStringInput("Enter new visit date and time in format dd/MM/yyyy HH:mm: ");
            case "DESCRIPTION" -> changes = getUserInput.getUserStringInput("Enter new visit description: ");
        }
        EditVisitRequest request = new EditVisitRequest(id, userInput, changes);
        EditVisitResponse response = editPatientVisit.execute(request);
        if (response.isVisitEdited()) {
            System.out.println("The patient visit with id " + id + " was successfully edited.");
        } else {
            System.out.println("The patient visit with id " + id + " was not edited.");
        }
    }
}