package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.EditDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class EditDoctorUIAction implements DoctorUIAction {

    @Autowired
    private EditDoctorService editDoctor;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String id = getUserInput.getUserStringInput("Please, enter the doctor's id: ");
        String userInputString = getUserInput
                .getUserStringInput("What you would like to change (NAME||SURNAME||SPECIALITY)? ")
                .toUpperCase(Locale.ROOT);
        String changes = getUserInput.getUserStringInput("Enter info for change: ");
        EditDoctorRequest request = new EditDoctorRequest(id, userInputString, changes);
        EditDoctorResponse response = editDoctor.execute(request);
        if (response.isDoctorEdited()) {
            System.out.println("The doctor with id " + id + " was successfully edited.");
        } else {
            System.out.println("The doctor with id " + id + " was not edited.");
        }
    }
}
