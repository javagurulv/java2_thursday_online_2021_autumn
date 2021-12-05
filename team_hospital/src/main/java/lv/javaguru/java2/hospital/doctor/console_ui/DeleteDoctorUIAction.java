package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.DeleteDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDoctorUIAction implements DoctorUIAction {

    @Autowired private DeleteDoctorService deleteDoctor;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String id = getUserInput.getUserStringInput("Please, enter the doctor's id: ");
        DeleteDoctorRequest request = new DeleteDoctorRequest(id);
        DeleteDoctorResponse response = deleteDoctor.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isDoctorDeleted()) {
                System.out.println("The doctor with id " + id + " was successfully deleted.");
            } else {
                System.out.println("The doctor with id " + id + " was not deleted.");
            }
        }
    }
}
