package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.services.DoctorExistsService;
import lv.javaguru.java2.hospital.doctor.services.FindDoctorByIdService;

public class FindDoctorByIDUIAction implements DoctorUIActions {

    private final FindDoctorByIdService findDoctorById;
    private final DoctorExistsService doctorExists;

    public FindDoctorByIDUIAction(FindDoctorByIdService findDoctorById, DoctorExistsService doctorExists) {
        this.findDoctorById = findDoctorById;
        this.doctorExists = doctorExists;
    }

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        int id = getUserInput.getUserNumericInput("Please enter lv.javaguru.java2.hospital.doctor's id: ");
        if (doctorExists.execute(id)) {
            findDoctorById.execute(id);
        } else {
            System.out.println("Doctor with such Id is not found.");
        }
    }
}
