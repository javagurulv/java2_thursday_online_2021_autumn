package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.FindDoctorByIdRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.FindDoctorByIdResponse;
import lv.javaguru.java2.hospital.doctor.core.services.DoctorExistsService;
import lv.javaguru.java2.hospital.doctor.core.services.FindDoctorByIdService;

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
        long id = getUserInput.getUserNumericInput("Please enter doctor's id: ");
        FindDoctorByIdRequest request = new FindDoctorByIdRequest(id);
        FindDoctorByIdResponse response = findDoctorById.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isDoctorFound()) {
                System.out.println("The doctor with id " + id + " was successfully found.");
            } else {
                System.out.println("The doctor with id " + id + " was not found.");
            }
        }
        /*if (doctorExists.execute(id)) {
            findDoctorById.execute(id);
        } else {
            System.out.println("Doctor with such Id is not found.");
        }*/
    }
}
