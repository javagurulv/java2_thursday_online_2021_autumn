package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.DeleteDoctorService;

@DIComponent
public class DeleteDoctorUIAction implements DoctorUIActions {
    @DIDependency private DeleteDoctorService deleteDoctor;

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please, enter the doctor's id: ");
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
