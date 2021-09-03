package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.services.DeleteDoctorService;
import lv.javaguru.java2.hospital.doctor.services.DoctorExistsService;

public class DeleteDoctorUIAction implements DoctorUIActions {
    private final DeleteDoctorService deleteDoctor;
    private final DoctorExistsService doctorExists;

    public DeleteDoctorUIAction(DeleteDoctorService deleteDoctor, DoctorExistsService doctorExists) {
        this.deleteDoctor = deleteDoctor;
        this.doctorExists = doctorExists;
    }

    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        int id = getUserInput.getUserNumericInput("Please, enter the doctor's id: ");
        if (doctorExists.execute(id)) {
            deleteDoctor.execute(id);
            System.out.println("Doctor with ID = " + id + " was successfully deleted.");
        } else {
            System.out.println("Doctor doesn't exist!");
        }
    }
}
