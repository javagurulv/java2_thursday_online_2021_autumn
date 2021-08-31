package doctor.console_ui;

import doctor.services.DoctorExistsService;
import doctor.services.FindDoctorByIdService;

public class FindByIDUIAction implements DoctorUIActions {

    private final FindDoctorByIdService findDoctorById;
    private final DoctorExistsService doctorExists;

    public FindByIDUIAction(FindDoctorByIdService findDoctorById, DoctorExistsService doctorExists) {
        this.findDoctorById = findDoctorById;
        this.doctorExists = doctorExists;
    }

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        int id = getUserInput.getUserNumericInput("Please enter doctor's id: ");
        if (doctorExists.execute(id)) {
            findDoctorById.execute(id);
        } else {
            System.out.println("Doctor with such Id is not found.");
        }
    }
}
