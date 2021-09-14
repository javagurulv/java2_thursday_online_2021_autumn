package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.DeleteDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.DoctorExistsService;

public class DeleteDoctorUIAction implements DoctorUIActions {
    private final DeleteDoctorService deleteDoctor;
    private final DoctorExistsService doctorExists;

    public DeleteDoctorUIAction(DeleteDoctorService deleteDoctor, DoctorExistsService doctorExists) {
        this.deleteDoctor = deleteDoctor;
        this.doctorExists = doctorExists;
    }

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

        /*if (doctorExists.execute(id)) {
            deleteDoctor.execute(id);
            System.out.println("Doctor with ID = " + id + " was successfully deleted.");
        } else {
            System.out.println("Doctor doesn't exist!");
        }*/
    }
}
