package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.EditDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditDoctorUIAction implements DoctorUIActions {

    @Autowired private EditDoctorService editDoctor;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please, enter the doctor's id: ");
        printEditMenu();
        int userInput = getUserInput.getUserNumericInput("Enter edit menu number: ");
        String changes = getUserInput.getUserStringInput("Enter info for change: ");
        EditDoctorRequest request = new EditDoctorRequest(id, userInput, changes);
        EditDoctorResponse response = editDoctor.execute(request);
        if (response.isDoctorEdited()) {
            System.out.println("The doctor with id " + id + " was successfully edited.");
        } else {
            System.out.println("The doctor with id " + id + " was not edited.");
        }
    }

    private void printEditMenu() {
        System.out.println("What information you would like to edit? ");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Speciality");
    }

}
